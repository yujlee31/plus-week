package com.example.demo.repository;

import com.example.demo.entity.QItem;
import com.example.demo.entity.QReservation;
import com.example.demo.entity.QUser;
import com.example.demo.entity.Reservation;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor //의존성주입
public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom { //QueryDSL

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Reservation> searchReservations(Long userId, Long itemId) {
        QReservation reservation = QReservation.reservation;
        QUser user = QUser.user;
        QItem item = QItem.item;

        return queryFactory
                .selectFrom(reservation)
                .join(reservation.user, user).fetchJoin() //N+1문제 해결
                .join(reservation.item, item).fetchJoin() //N+1문제 해결
                .where(
                        eqUserId(userId),
                        eqItemId(itemId)
                )
                .fetch();
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? QReservation.reservation.user.id.eq(userId) : null; //null여부에 따라 동적으로 검색 수행
    }

    private BooleanExpression eqItemId(Long itemId) {
        return itemId != null ? QReservation.reservation.item.id.eq(itemId) : null; //null여부에 따라 동적으로 검색 수행
    }
}
