package edu.univ.erp.ui.admin.popup;

import java.util.Random;

public class SectionIdGenerator {

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    public static String generateSectionId() {
        StringBuilder id = new StringBuilder("SEC-");
        for (int i = 0; i < 3; i++) {
            id.append(CHARSET.charAt(RANDOM.nextInt(CHARSET.length())));
        }
        return id.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateSectionId());
    }
}
