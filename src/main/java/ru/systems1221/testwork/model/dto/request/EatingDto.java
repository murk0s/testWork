package ru.systems1221.testwork.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class EatingDto {

    @NotNull(message = "Необходимо указать id пользователя")
    private final int userId;

    @NotNull(message = "Необходимо указать дату")
    private final LocalDate date;

    @NotEmpty(message = "Необходимо указать тип приёма пищи")
    private final String type;

    @NotEmpty(message = "Необходимо заполнить список блюд")
    private final List<Integer> foods;

}
