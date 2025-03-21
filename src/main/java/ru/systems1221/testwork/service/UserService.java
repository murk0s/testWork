package ru.systems1221.testwork.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.systems1221.testwork.exception.UserCeateException;
import ru.systems1221.testwork.model.User;
import ru.systems1221.testwork.model.dto.UserDto;
import ru.systems1221.testwork.repository.UserRepository;
import ru.systems1221.testwork.util.Utils;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Transactional
    public User save(@Valid UserDto userDto) {
        if (!checkEmail(userDto.getEmail()))
            throw new UserCeateException("Пользователь с email " + userDto.getEmail() + " уже существует");
        User newUser = userRepository.save(objectMapper.convertValue(userDto, User.class));
        newUser.setDailyCalorieIntake(Utils.calculateBMR(newUser.getAge(), newUser.getHeight(), newUser.getHeight(), newUser.getGender()));
        return newUser;
    }

    private boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден."));
    }

}
