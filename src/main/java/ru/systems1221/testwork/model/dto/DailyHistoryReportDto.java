package ru.systems1221.testwork.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class DailyHistoryReportDto {
    private final LocalDate date;
    private final long totalCalories;
    private final List<FoodReportDto> foods;
}
