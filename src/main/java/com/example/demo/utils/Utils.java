package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class Utils {
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern PATTERN_PASSWORD = Pattern.compile(PASSWORD_REGEX);

    public boolean validateEmail(String email) {
        Matcher matcher = PATTERN_EMAIL.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String email) {
        Matcher matcher = PATTERN_PASSWORD.matcher(email);
        return matcher.matches();
    }

    public String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String resultado = String.valueOf(uuid);
        return resultado;
    }
}
