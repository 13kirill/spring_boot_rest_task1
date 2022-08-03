package ru.netology.spring_boot_rest_task1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.netology.spring_boot_rest_task1.exception.InvalidCredentials;
import ru.netology.spring_boot_rest_task1.exception.UnauthorizedUser;
import ru.netology.spring_boot_rest_task1.model.Authorities;
import ru.netology.spring_boot_rest_task1.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCredentials.class)
    String resolveInvalidCredentials() {
        return "User name or password is empty";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnauthorizedUser.class)
    String resolveUnauthorizedUser() {
        return "Unknown user";
    }
}