package ru.systems1221.testwork.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.systems1221.testwork.model.EatingType;
import ru.systems1221.testwork.model.Goal;
import ru.systems1221.testwork.model.dto.DailyReportDto;
import ru.systems1221.testwork.model.dto.FoodReportDto;
import ru.systems1221.testwork.model.dto.HistoryResponseDto;
import ru.systems1221.testwork.repository.CompositionRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReportServiceTest {

    @Mock
    private CompositionRepository compositionRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDailyReport() {
        int userId = 1;
        LocalDate date = LocalDate.now();
        DailyReportDto mockDailyReportDto = new DailyReportDto(date, //LocalDate.of(2025,03,21),
                1,
                2000L,
                3L,
                1800,
                Goal.ADD);

        when(compositionRepository.calorieReportByUserIdAndDate(userId, date)).thenReturn(mockDailyReportDto);

        DailyReportDto result = reportService.getDailyReport(userId, date);

        assertEquals(Goal.ADD, result.getGoal());
        assertEquals(1800, result.getDailyCalorieIntake());
        assertEquals(2000L, result.getTotalCalories());
        verify(compositionRepository, times(1)).calorieReportByUserIdAndDate(userId, date);
    }

    @Test
    public void testGetHistoryReport() {
        int userId = 1;
        LocalDate dateBegin = LocalDate.now().minusDays(7);
        LocalDate dateEnd = LocalDate.now();

        FoodReportDto food1 = new FoodReportDto(
                EatingType.BREAKFAST,
                "food1",
                200,
                10,
                5,
                15,
                LocalDate.now().minusDays(1));

        FoodReportDto food2 = new FoodReportDto(
                EatingType.LANCH,
                "food2",
                400,
                10,
                50,
                25,
                LocalDate.now().minusDays(2));

        FoodReportDto food3 = new FoodReportDto(
                EatingType.LANCH,
                "food2",
                600,
                10,
                50,
                25,
                LocalDate.now().minusDays(3));
        List<FoodReportDto> foods = Arrays.asList(food1, food2, food3);

        when(compositionRepository.calorieReportByUserIdAndDateBeginAndDateEnd(userId, dateBegin, dateEnd)).thenReturn(foods);
        HistoryResponseDto result = reportService.getHistoryReport(userId, dateBegin, dateEnd);

        assertEquals(1200, result.getTotalCalories());
        assertEquals(3, result.getListDaileHistoryDto().size());
        verify(compositionRepository, times(1)).calorieReportByUserIdAndDateBeginAndDateEnd(userId, dateBegin, dateEnd);
    }
}