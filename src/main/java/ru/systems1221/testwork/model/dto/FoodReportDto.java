package ru.systems1221.testwork.model.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.systems1221.testwork.model.EatingType;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class FoodReportDto {

    private final EatingType eatingType;

    private final String name;

    private final Integer kkal;

    private final Integer proteins;

    private final Integer fats;

    private final Integer carbohydrates;

    private final LocalDate date;

}
