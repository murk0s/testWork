package ru.systems1221.testwork.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.systems1221.testwork.model.Goal;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class DailyReportDto {
    private final LocalDate date;
    private final Integer userId;
    private final Long totalCalories;
    private final Long countEating;
    private final int dailyCalorieIntake;
    private final Goal goal;
    String recommendation;
}
