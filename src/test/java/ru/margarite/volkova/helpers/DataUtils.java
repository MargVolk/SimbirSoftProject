package ru.margarite.volkova.helpers;

import java.security.SecureRandom;
import java.util.*;

public class DataUtils {
    private static final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private DataUtils() {
    }

    public static String generatePostCode() {
        StringBuilder postCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            postCode.append(random.nextInt(10));
        }

        return postCode.toString();
    }

    public static String generateFirstName(String postCode) {
        StringBuilder firstName = new StringBuilder();

        List<Integer> numbers = Arrays.stream(postCode.split("(?<=\\G.{2})"))
                .map(Integer::parseInt)
                .toList();

        for (Integer number : numbers) {
            firstName.append(parseNumberToAlphabet(number));
        }

        return firstName.toString();
    }

    private static Character parseNumberToAlphabet(Integer number) {
        int index = number < 26 ? number
                : number - (number / LOWERCASE_ALPHABET.length()) * LOWERCASE_ALPHABET.length();
        return LOWERCASE_ALPHABET.charAt(index);
    }

    public static String averageLengthName(List<String> names) {
        OptionalDouble averageOpt = names.stream()
                .mapToInt(String::length)
                .average();

        if (averageOpt.isPresent()) {
            double average = averageOpt.getAsDouble();

            return names.stream()
                    .min(Comparator.comparingDouble(name -> Math.abs(name.length() - average)))
                    .orElse("");
        }

        return "";
    }

    public static String getLastNameFromList() {
        List<String> list = Arrays.asList("Adrian", "Jacobson", "Hoggarth", "Andrews", "MacAdam", "Black", "Macey", "Bootman",
                "Mansfield", "Marlow", "Carey", "Morrison", "Clapton");
        Random random = new SecureRandom();
        return list.get(random.nextInt(list.size()));
    }

    public static List<Map<String, String>> generateCustomerData(int count) {
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String postCode = generatePostCode();
            Map<String, String> customer = Map.of(
                    "firstName", generateFirstName(postCode),
                    "lastName", getLastNameFromList(),
                    "postCode", postCode
            );
            data.add(customer);
        }

        return data;
    }
}
