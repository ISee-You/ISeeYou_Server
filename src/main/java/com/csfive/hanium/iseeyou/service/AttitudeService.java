package com.csfive.hanium.iseeyou.service;

import com.csfive.hanium.iseeyou.domain.attitude.Attitude;
import com.csfive.hanium.iseeyou.domain.attitude.AttitudeRepository;
import com.csfive.hanium.iseeyou.domain.pose.Pose;
import com.csfive.hanium.iseeyou.domain.pose.PoseRepository;
import com.csfive.hanium.iseeyou.domain.pose.Poses;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.domain.student.StudentRepository;
import com.csfive.hanium.iseeyou.dto.attitude.AttitudeResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AttitudeService {

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
    public Long saveWithStudentAndPoses(final Long studentId, final AttitudeResult attitudeResult) {
        checkPositiveNumber(attitudeResult.getTotalSecond());

        Student student = findStudentById(studentId);
        Attitude attitude = Attitude.builder()
                .student(student)
                .totalSecond(attitudeResult.getTotalSecond())
                .poses(Poses.createPoses(attitudeResult.getCounts()))
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
