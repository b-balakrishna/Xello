package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.entities.User;

public class InmemoryUserRepository implements UserRepository{

    private Map<Long, User> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public User save(User entity) {
        User user = new User(idCounter.getAndIncrement(), entity);
        storage.putIfAbsent(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }

    @Override
    public Boolean exists(String email) {
        return storage.values().stream().anyMatch(user -> user.getEmail().equals(email));
    }   
}
