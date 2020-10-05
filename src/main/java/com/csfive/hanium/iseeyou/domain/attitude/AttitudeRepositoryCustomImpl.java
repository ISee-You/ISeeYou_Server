package com.csfive.hanium.iseeyou.domain.attitude;

import com.csfive.hanium.iseeyou.domain.pose.QPose;
import com.csfive.hanium.iseeyou.domain.pose.QPoses;
import com.csfive.hanium.iseeyou.domain.student.Student;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.csfive.hanium.iseeyou.domain.attitude.QAttitude.*;

@Repository
@RequiredArgsConstructor
public class AttitudeRepositoryCustomImpl implements AttitudeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Attitude> findAllByStudentAndBetweenLocalDateTime(final Student student, final LocalDateTime startDate, final LocalDateTime endDate) {
        return queryFactory
                .selectFrom(attitude)
                .where(studentEq(student),
                        createdDateBetween(startDate, endDate))
                .fetch();
    }

    private BooleanExpression studentEq(final Student student) {
        return attitude.student.eq(student);
    }

    private BooleanExpression createdDateBetween(final LocalDateTime startDate, final LocalDateTime endDate) {
        return attitude.createdDate.between(startDate, endDate);
    }
}
