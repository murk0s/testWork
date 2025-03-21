package ru.systems1221.testwork.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class HistoryResponseDto {

    private final long totalCalories;

    private final List<DailyHistoryReportDto> listDaileHistoryDto;

}
