package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger count3 = new AtomicInteger();
    public static AtomicInteger count4 = new AtomicInteger();
    public static AtomicInteger count5 = new AtomicInteger();

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        new Thread(() -> {
            for (String str : texts) {
                if (palindromeCheck(str)) {
                    switch (str.length()) {
                        case (3) -> count3.getAndIncrement();
                        case (4) -> count4.getAndIncrement();
                        case (5) -> count5.getAndIncrement();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (String str : texts) {
                if (oneLetCheck(str)) {
                    switch (str.length()) {
                        case (3) -> count3.getAndIncrement();
                        case (4) -> count4.getAndIncrement();
                        case (5) -> count5.getAndIncrement();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (String str : texts) {
                if (alBetCheck(str)) {
                    switch (str.length()) {
                        case (3) -> count3.getAndIncrement();
                        case (4) -> count4.getAndIncrement();
                        case (5) -> count5.getAndIncrement();
                    }
                }
            }
        }).start();

        System.out.println("Красивых слов с длиной 3: " + count3 + " шт.");
        System.out.println("Красивых слов с длиной 4: " + count4 + " шт.");
        System.out.println("Красивых слов с длиной 5: " + count5 + " шт.");
    }

    public static boolean palindromeCheck (String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }

    public static boolean oneLetCheck (String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) != str.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean alBetCheck (String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) > str.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}