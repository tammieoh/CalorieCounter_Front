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
        double kg = weight * 0.45359237;
        double cm = height * 2.54;
        if(sex.toLowerCase().equals("men")) {
            bmr = (10 * kg) +  (6.25 * cm) - (5 * age) + 5;
            return bmr;
        }
        else {
            bmr = (10 * kg) +  (6.25 * cm) - (5 * age) - 161;
        }
        return bmr;
    }
    public static double recommendedCalories(double bmr, String activity_level) {
        int calories = 0;
        if(activity_level.toLowerCase().equals("sedentary")) {
            calories = (int)(bmr * 1.2) - 500;
        }
        else if(activity_level.toLowerCase().equals("light")) {
            calories = (int)(bmr * 1.375) - 500;
        }
        else if(activity_level.toLowerCase().equals("moderate")) {
            calories = (int)(bmr * 1.55) - 500;
        }
        else if(activity_level.toLowerCase().equals("active")) {
            calories = (int)(bmr * 1.725) - 500;
        }
        else if(activity_level.toLowerCase().equals("extra active")) {
            calories = (int)(bmr * 1.9) - 500;
        }
        else {
            calories = (int)(bmr * 1.2) - 500;
        }
        return calories;
    }
}
