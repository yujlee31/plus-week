package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO: 4. find or save 예제 개선
    @Transactional
    public void reportUsers(List<Long> userIds) {
//        for (Long userId : userIds) {
//            User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다."));
//
//            user.updateStatusToBlocked();
//
//            userRepository.save(user);
//        }
        List<User> users = userRepository.findAllById(userIds); //List를 파라미터로 받는 메서드로 변경

        if(users.isEmpty()) {
            throw new IllegalArgumentException("해당 아이디에 대한 유저 권한인 사용자가 존재하지 않습니다.");
        }

        userRepository.updatePendingStatus(userIds);
    }
}
