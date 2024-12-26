package com.example.demo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordEncoderTests {

    @Test
    void testEncodePassword() {
        String rawPassword = "test123!@1";
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        assertNotNull(encodedPassword, "암호화된 비밀번호는 null이 될 수 없습니다.");
        assertNotEquals(rawPassword, encodedPassword, "암호화된 비밀번호는 평문 비밀번호와 같을 수 없습니다.");
    }

    @Test
    void testEncodedPasswordMatchesRawPassword() {
        String rawPassword = "test123!@q2351";
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        assertTrue(PasswordEncoder.matches(rawPassword, encodedPassword),
                "평문 비밀번호는 암호화된 비밀번호와 matches로 비교했을 때 true를 반환해야 합니다.");
    }

    @Test
    void testEncodedPasswordsAreDifferent() {
        String rawPassword = "test2elwajfekajsedl@$!";
        String encodedPassword1 = PasswordEncoder.encode(rawPassword);
        String encodedPassword2 = PasswordEncoder.encode(rawPassword);

        assertNotEquals(encodedPassword1, encodedPassword2, "암호화된 비밀번호는 서로 달라야 합니다."); //같은 평문을 암호화 할 때마다 달라야 함
    }

    @Test
    void testPasswordDoesNotMatch() {
        String rawPassword = "test2elwajfekajsedl@$!";
        String wrongPassword = "test123!@q2351";

        String encodedPassword = PasswordEncoder.encode(rawPassword);

        assertFalse(PasswordEncoder.matches(wrongPassword, encodedPassword),
                "wrongPassword는 encodedPassword와 matches로 비교했을 때 False를 리턴해야 합니다.");
    }
}
