package org.example.helpers;

import com.github.javafaker.Faker;

public class Randomizer {
    private static final Faker faker = new Faker();

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static int getRandomNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static void main(String[] args) {
        System.out.println("Random Name: " + getRandomFirstName() + " " + getRandomLastName());
        System.out.println("Random Number: " + getRandomNumber(1000, 9999));
    }
}