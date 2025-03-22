package ru.systems1221.testwork.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.systems1221.testwork.model.User;
import ru.systems1221.testwork.model.dto.request.UserDto;
import ru.systems1221.testwork.service.UserService;

@RestController
@RequestMapping("${controller.user-controller}")
@RequiredArgsConstructor
public class UserControler {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> createUser (@RequestBody @Valid UserDto userDto) {
        User newUser = userService.save(userDto);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getEmployeeInfo(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
