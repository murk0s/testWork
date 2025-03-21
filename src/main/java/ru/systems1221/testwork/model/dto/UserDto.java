package ru.systems1221.testwork.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    @Email(message = "Некорректный email")
    @NotBlank(message = "Email не должен быть пустым")
    private final String email;

    @NotBlank(message = "Имя не может быть пустым")
    private final String name;

    @Min(value = 14, message = "Формула Харриса-Бенедикта применима для людей старше 14 лет")
    @Max(value = 110, message = "Проверьте возраст, слишком большое значение")
    private final Integer age;

    @Min(value = 30, message = "Формула Харриса-Бенедикта применима для людей с массой более 30 кг")
    @Max(value = 300, message = "Проверьте вес, слишком большое значение")
    private final Integer weight;

    @Min(value = 100, message = "Формула Харриса-Бенедикта применима для людей выше 100 см")
    @Max(value = 251, message = "Проверьте рост, слишком большое значение")
    private final Integer height;

    @NotBlank(message = "Укажите цель")
    private final String goal;

    private final boolean gender;

}
