package com.csfive.hanium.iseeyou.domain.pose;

import com.csfive.hanium.iseeyou.domain.attitude.Attitude;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id", "name"})
public class Pose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSE_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ATTITUDE_ID")
    private Attitude attitude;

    private int count;

    private String name;

    public Pose(final int count, final String name) {
        this.count = count;
        this.name = name;
    }

    public Pose(final int count) {
        this.count = count;
    }

    public void plusCount() {
        this.count += 1;
    }

    public void changeAttitude(final Attitude attitude) {
        this.attitude = attitude;
    }
}
