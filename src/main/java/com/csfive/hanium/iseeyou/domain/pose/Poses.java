package com.csfive.hanium.iseeyou.domain.pose;

import com.csfive.hanium.iseeyou.domain.attitude.Attitude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Poses {
    private static final int VALID_COUNT_SIZE = 5;

    @OneToMany(mappedBy = "attitude", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pose> poses;

    private Poses(final List<Pose> poses) {
        validateCorrectCount(poses.size());
        this.poses = poses;
    }

    public static Poses createInitPoses() {
        List<Pose> poses = new ArrayList<>();
        poses.add(new Pose(0));
        poses.add(new Pose(0));
        poses.add(new Pose(0));
        poses.add(new Pose(0));
        poses.add(new Pose(0));

        return new Poses(poses);
    }

    public static Poses createPoses(final List<Integer> counts) {
        final List<Pose> pose = counts.stream()
                .map(Pose::new)
                .collect(Collectors.toList());

        return new Poses(pose);
    }

    public void plusCountOfPose(final Pose pose) {
        final Pose findPose = poses.stream()
                .filter(p -> p.equals(pose))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("pose : %s, 존재하지 않는 pose입니다.", pose.toString())));

        findPose.plusCount();
    }

    public void changeAttitude(final Attitude attitude) {
        poses.forEach(p -> p.changeAttitude(attitude));
    }

    private static void validateCorrectCount(final int size) {
        if (size != VALID_COUNT_SIZE) {
            throw new IllegalArgumentException(String.format("poses size : %d, 올바른 poses size는 %d입니다.", size, VALID_COUNT_SIZE));
        }
    }
}
