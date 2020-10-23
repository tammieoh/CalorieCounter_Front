package com.example.login;

public class CalorieCalc {
    String sex, activity_level;
    int age, weight, height ;

    public CalorieCalc(String sex, String activity_level, int age, int weight, int height) {
        this.sex = sex;
        this.activity_level = activity_level;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }
    public static double calculateBMR(String sex, int age, int weight, int height) {
        double bmr = 0;
        if(sex.toLowerCase().equals("men")) {
            bmr = (10* weight) +  (6.25 * height) - (5 * age) + 5;
            return bmr;
        }
        else {
            bmr = (10* weight) +  (6.25 * height) - (5 * age) - 161;
        }
        return bmr;
    }
    public static double recommendedCalories(double bmr, String activity_level) {
        double calories = 0;
        return calories;
    }
}
