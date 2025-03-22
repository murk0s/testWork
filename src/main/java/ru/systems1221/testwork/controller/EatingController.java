package ru.systems1221.testwork.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.systems1221.testwork.model.Eating;
import ru.systems1221.testwork.model.dto.request.EatingDto;
import ru.systems1221.testwork.service.EatingService;

@RestController
@RequestMapping("${controller.eating-controller}")
@RequiredArgsConstructor
public class EatingController {

    private final EatingService eatingService;

    @PostMapping()
    public ResponseEntity<String> createEating (@RequestBody @Valid EatingDto eatingDto) {
        Eating newEating = eatingService.save(eatingDto);
        return new ResponseEntity<>("Приём пищи успешно добавлен", HttpStatus.CREATED);
    }
}
