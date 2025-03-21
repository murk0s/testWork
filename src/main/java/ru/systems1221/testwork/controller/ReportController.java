package ru.systems1221.testwork.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.systems1221.testwork.model.dto.DailyReportDto;
import ru.systems1221.testwork.model.dto.DailyHistoryReportDto;
import ru.systems1221.testwork.model.dto.HistoryResponseDto;
import ru.systems1221.testwork.service.ReportService;

import java.time.LocalDate;

@RestController
@RequestMapping("${controller.report-controller}")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService service;
    private final ObjectMapper objectMapper;

    @GetMapping("/{userId}/daily/{date}")
    public ResponseEntity<DailyReportDto> getDailyReport(@PathVariable(name = "userId") int id, @PathVariable(name = "date") LocalDate date) {
        DailyReportDto dailyReport = service.getDailyReport(id, date);
        return ResponseEntity.ok(dailyReport);
    }

    @GetMapping("/{userId}/daily/{dateBegin}/{dateEnd}")
    public ResponseEntity<HistoryResponseDto> getHistoryReport(@PathVariable(name = "userId") int id,
                                                                  @PathVariable(name = "dateBegin") LocalDate dateBegin,
                                                                  @PathVariable(name = "dateEnd") LocalDate dateEnd) {
        HistoryResponseDto historyReport = service.getHistoryReport(id, dateBegin, dateEnd);
        return ResponseEntity.ok(historyReport);
    }

}
