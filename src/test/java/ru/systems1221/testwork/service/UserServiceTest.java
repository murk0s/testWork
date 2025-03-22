package ru.systems1221.testwork.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import ru.systems1221.testwork.exception.UserCreateException;
import ru.systems1221.testwork.model.User;
import ru.systems1221.testwork.model.dto.request.UserDto;
import ru.systems1221.testwork.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserService userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(
                "test@example.com",
                "TestName",
                25,
                110,
                180,
                "LOST",
                false);

        user = new User();
        user.setEmail("test@example.com");
        user.setAge(25);
        user.setHeight(180);
        user.setWeight(70);
        user.setGender(false);
    }

    @Test
    void testSaveUserSuccessfully() {
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(objectMapper.convertValue(userDto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(userDto);

        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testSaveUserWithExistingEmail() {
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(user));

        assertThrows(UserCreateException.class, () -> userService.save(userDto));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testSaveUserWithInvalidData() {
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(objectMapper.convertValue(userDto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> userService.save(userDto));
    }
}