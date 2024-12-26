package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Modifying //데이터 변경이 필요한 쿼리(INSERT, UPDATE, DELETE)를 실행할 때 사용
    @Transactional
    @Query("UPDATE User u SET u.status = 'PENDING' WHERE u.status = 'APPROVED' AND u.role = 'USER' AND u.id IN :userIds")
    void updatePendingStatus(List<Long> userIds);
}
