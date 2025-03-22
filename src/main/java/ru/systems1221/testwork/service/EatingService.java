package ru.systems1221.testwork.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.systems1221.testwork.model.Composition;
import ru.systems1221.testwork.model.Eating;
import ru.systems1221.testwork.model.Food;
import ru.systems1221.testwork.model.dto.request.EatingDto;
import ru.systems1221.testwork.repository.EatingRepository;
import ru.systems1221.testwork.repository.FoodRepository;
import ru.systems1221.testwork.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EatingService {
    private final EatingRepository eatingRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Transactional
    public Eating save(@Valid EatingDto eatingDto) {
        Eating newEating = convertToEating(eatingDto);
        eatingRepository.save(newEating);
        return newEating;
    }

    private Eating convertToEating(EatingDto eatingDto) {
        Eating newEating = objectMapper.convertValue(eatingDto, Eating.class);
        newEating.setUser(userRepository
                .findById(eatingDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден, код -" + eatingDto.getUserId())));
        List<Food> foodList = eatingDto.getFoods().stream()
                .map(id -> foodRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Несуществующее блюдо, код - " + id)))
                .toList();
        List<Composition> compositionList = foodList.stream()
                .map(food -> Composition
                        .builder().
                        food(food).
                        eating(newEating)
                        .build())
                .toList();
        newEating.setCompositions(compositionList);
        return newEating;
    }
}
