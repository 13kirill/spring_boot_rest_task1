package ru.netology.spring_boot_rest_task1.repository;

import org.springframework.stereotype.Repository;
import ru.netology.spring_boot_rest_task1.model.Authorities;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    final Map<String, String> users = new ConcurrentHashMap<>(Map.of("Vasya", "Vasya123", "Petya", "Petya123"));

    final Map<Map<String, String>, List<Authorities>> userAuthorities =
            new ConcurrentHashMap<>(Map.of(users,
                    List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE)));

    public List<Authorities> getUserAuthorities(String user, String password) {
        users.put(user, password);
        return userAuthorities.get(users);
    }
}