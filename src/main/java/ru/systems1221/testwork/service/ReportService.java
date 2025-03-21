package ru.systems1221.testwork.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.systems1221.testwork.model.dto.DailyReportDto;
import ru.systems1221.testwork.model.dto.DailyHistoryReportDto;
import ru.systems1221.testwork.model.dto.FoodReportDto;
import ru.systems1221.testwork.model.dto.HistoryResponseDto;
import ru.systems1221.testwork.repository.CompositionRepository;
import ru.systems1221.testwork.util.Utils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final CompositionRepository compositionRepository;

    public DailyReportDto getDailyReport(int id, LocalDate date) {
        DailyReportDto dailyReportDto = compositionRepository.calorieReportByUserIdAndDate(id, date);
        dailyReportDto.setRecommendation(Utils.generateRecommendation(
                dailyReportDto.getGoal(),
                dailyReportDto.getDailyCalorieIntake(),
                dailyReportDto.getTotalCalories()
        ));
        return dailyReportDto;
    }

    public HistoryResponseDto getHistoryReport(int id, LocalDate dateBegin,LocalDate dateEnd ) {

        List<FoodReportDto> foods = compositionRepository.calorieReportByUserIdAndDateBeginAndDateEnd(id, dateBegin, dateEnd);;
        List<DailyHistoryReportDto> listDailyHistoryReportDto = mapToDailyHistoryReportDto(foods);

        return mapToHistoryResponseDto(listDailyHistoryReportDto);
    }

    private List<DailyHistoryReportDto> mapToDailyHistoryReportDto(List<FoodReportDto> foods) {
        Map<LocalDate, List<FoodReportDto>> groupedByDate = foods.stream()
                .collect(Collectors.groupingBy(FoodReportDto::getDate));

        return groupedByDate.entrySet().stream()
                .map(entry -> {
                    LocalDate date1 = entry.getKey();
                    List<FoodReportDto> foodList = entry.getValue();
                    long totalCalories1 = foodList.stream().mapToLong(FoodReportDto::getKkal).sum();
                    return new DailyHistoryReportDto(date1, totalCalories1, foodList);
                })
                .collect(Collectors.toList());
    }

    private HistoryResponseDto mapToHistoryResponseDto(List<DailyHistoryReportDto> listDailyHistoryReportDto) {

        return new HistoryResponseDto(listDailyHistoryReportDto
                .stream()
                .mapToLong(DailyHistoryReportDto::getTotalCalories)
                .sum(),
                listDailyHistoryReportDto);
    }
}
