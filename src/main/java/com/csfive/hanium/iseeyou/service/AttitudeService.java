package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.attitude.Attitude;
import com.csfive.hanium.iseeyou.domain.attitude.AttitudeRepository;
import com.csfive.hanium.iseeyou.domain.pose.Pose;
import com.csfive.hanium.iseeyou.domain.pose.PoseRepository;
import com.csfive.hanium.iseeyou.domain.pose.Poses;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.attitude.AttitudeResultRequest;
import com.csfive.hanium.iseeyou.dto.pose.PoseResults;
import com.csfive.hanium.iseeyou.dto.pose.PosesTimeRequest;
import com.csfive.hanium.iseeyou.enums.DateStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttitudeService {

    private static final int PICTURE_GAP = 3;

    private final AttitudeRepository attitudeRepository;
    private final StudentRepository studentRepository;
    private final PoseRepository poseRepository;

    @Transactional
    public Long initSaveWithStudent(Long studentId) {
        Student student = findStudentById(studentId);
        Attitude attitude = Attitude.builder()
                .student(student)
                .poses(Poses.createInitPoses())
                .build();

        attitudeRepository.save(attitude);

        return attitude.getId();
    }

    @Transactional
    public Long saveWithStudentAndPoses(final Long studentId, final AttitudeResultRequest attitudeResultRequest) {
        checkPositiveNumber(attitudeResultRequest.getTotalSecond());

        Student student = findStudentById(studentId);
        Attitude attitude = Attitude.builder()
                .student(student)
                .totalSecond(attitudeResultRequest.getTotalSecond())
                .poses(Poses.createPoses(attitudeResultRequest.getCounts()))
                .build();

        attitudeRepository.save(attitude);

        return attitude.getId();
    }

    @Transactional(readOnly = true)
    public Attitude findOneByStudent(final Long attitudeId, final Long studentId) {
        Attitude attitude = findAttitudeByEntity(findAttitudeById(attitudeId));
        Student student = findStudentById(studentId);

        return attitudeRepository.findOneByStudent(attitude, student);
    }

    @Transactional
    public void plusCountOfPose(final Long attitudeId, final Long poseId) {
        final Attitude attitude = findAttitudeByEntity(findAttitudeById(attitudeId));
        final Pose pose = findPoseById(poseId);

        attitude.plusCountOfPose(pose);
    }

    @Transactional
    public PoseResults findPoseResultsByStudentAndDate(final Long studentId, final PosesTimeRequest posesTimeRequest) {
        final Student student = findStudentById(studentId);

        final DateStatus dateStatus = DateStatus.findByType(posesTimeRequest.getDateType());
        final LocalDateTime endDate = getEndDate(posesTimeRequest.getStartDate(), dateStatus.days);
        final LocalDateTime startDate = convertLocalDateTime(posesTimeRequest.getStartDate());

        final List<Attitude> attitudes = attitudeRepository.findAllByStudentAndBetweenLocalDateTime(student, startDate, endDate);

        return createPoseResults(attitudes);
    }

    private LocalDateTime getEndDate(final LocalDate startDate, final int days) {
        if (days == 0) {
            return LocalDateTime.of(LocalDate.MIN, LocalTime.MAX);
        }
        return LocalDateTime.of(startDate.plusDays(days), LocalTime.MAX);
    }

    private LocalDateTime convertLocalDateTime(final LocalDate startDate) {
        return startDate.atStartOfDay();
    }

    private PoseResults createPoseResults(final List<Attitude> attitudes) {
        List<Integer> poseCounts = new ArrayList<>();
        changePoseCounts(attitudes, poseCounts);

        int totalCount = getTotalCount(poseCounts);
        List<Double> percentages = createPercentages(poseCounts, totalCount);

        return new PoseResults(percentages, totalCount * PICTURE_GAP);
    }

    private void changePoseCounts(final List<Attitude> attitudes, final List<Integer> poseCounts) {
        attitudes.forEach(attitude -> attitude.plusCountTo(poseCounts));
    }

    private int getTotalCount(final List<Integer> poseCounts) {
        return poseCounts.stream()
                .mapToInt(poseCount -> poseCount)
                .sum();
    }

    private List<Double> createPercentages(final List<Integer> poseCounts, final double totalCount) {
        return poseCounts.stream()
                .map(poseCount -> (Math.floor(poseCount / totalCount * 100)))
                .collect(Collectors.toList());
    }

    private Attitude findAttitudeByEntity(final Attitude attitude) {
        return attitudeRepository.findJoinFetchByEntity(attitude)
                .orElseThrow(() -> new NoSuchElementException(String.format("attitudeId: %d, 존재하지 않는 아이디 입니다.", attitude.getId())));
    }

    private Attitude findAttitudeById(final Long attitudeId) {
        return attitudeRepository.findById(attitudeId)
                .orElseThrow(() -> new NoSuchElementException(String.format("attitudeId: %d, 존재하지 않는 아이디 입니다.", attitudeId)));
    }

    private Student findStudentById(final Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException(String.format("studentId: %d, 존재하지 않는 아이디 입니다.", studentId)));
    }

    private Pose findPoseById(final Long poseId) {
        return poseRepository.findById(poseId)
                .orElseThrow(() -> new NoSuchElementException(String.format("poseId : %d, 존재하지 않는 아이디 입니다.", poseId)));
    }

    private void checkPositiveNumber(final long totalSecond) {
        if (totalSecond < 0) {
            throw new IllegalArgumentException(String.format("totalSecond : %s, totalSecond는 양수여야 합니다", totalSecond));
        }
    }
}
