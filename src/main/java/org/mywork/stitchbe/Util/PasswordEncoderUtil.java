package org.mywork.stitchbe.Util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 비밀번호 인코딩
    public static String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    // 비밀번호 일치 확인
    public static boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        String rawPassword = "1111";
        String encodedPassword = encodePassword(rawPassword);

        System.out.println("Encoded Password: " + encodedPassword);
        System.out.println("Matches: " + matches(rawPassword, encodedPassword));
    }
}
