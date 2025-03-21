package ru.systems1221.testwork.util;

import ru.systems1221.testwork.model.Goal;

public class Utils {
    public static int calculateBMR(int age, int height, int weight, boolean isFemale) {
        if (isFemale)
            return (int) Math.round(447.593 + (9.247*weight) + (3.098*height) - (4.330*age));
        else
            return (int) Math.round(88.362 + (13.397*weight) + (4.799*height) - (5.677*age));
    }

    public static String generateRecommendation(Goal goal, int dailyCalorieIntake, long totalCalories) {
        return switch (goal) {
            case ADD ->  (dailyCalorieIntake > totalCalories) ? "Ван необходимо больше калорий" : "Отлично, дневное потребение калорий способствует набору веса";
            case LOST -> (dailyCalorieIntake <= totalCalories) ? "Ван необходимо сократить потребление калорий" : "Отлично, дневное потребление калорий способствует потере веса";
            case SAVE -> (dailyCalorieIntake != totalCalories) ? ((dailyCalorieIntake > totalCalories) ? "Дневное потребение калорий способствует потере веса" : "Дневное потребение калорий способствует набору веса") : "Отлично, дневное потребение калорий способствует сохранению веса";
        };
    }
}
