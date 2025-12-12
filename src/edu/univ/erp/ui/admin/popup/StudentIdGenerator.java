package edu.univ.erp.ui.admin.popup;

import java.util.Random;

public class StudentIdGenerator {

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    public static String generateStudentId() {
        StringBuilder id = new StringBuilder("STU-");
        for (int i = 0; i < 3; i++) {
            id.append(CHARSET.charAt(RANDOM.nextInt(CHARSET.length())));
        }
        return id.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateStudentId());
    }
}
