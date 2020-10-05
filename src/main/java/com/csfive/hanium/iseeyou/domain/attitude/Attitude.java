package com.csfive.hanium.iseeyou.domain.attitude;

import com.csfive.hanium.iseeyou.domain.BaseTimeEntity;
import com.csfive.hanium.iseeyou.domain.pose.Pose;
import com.csfive.hanium.iseeyou.domain.pose.Poses;
import com.csfive.hanium.iseeyou.domain.student.Student;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attitude extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTITUDE_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @Embedded
    private Poses poses;

    private long totalSecond;

    public void plusCountOfPose(final Pose pose) {
        poses.plusCountOfPose(pose);
    }

    @Builder
    public Attitude(final Long id, final Student student, final Poses poses, final long totalSecond) {
        this.id = id;
        this.student = student;
        this.poses = poses;
        this.totalSecond = totalSecond;

        poses.changeAttitude(this);
    }

    public void plusCountTo(final List<Integer> poseCounts) {
        poses.plusCountTo(poseCounts);
    }
}
