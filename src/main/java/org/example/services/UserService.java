package org.example.services;


import org.example.exceptions.CabBookingException;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public void addUser(String name, String gender, int age) throws CabBookingException {
        if (users.containsKey(name)) {
            throw new CabBookingException("User with name " + name + " already exists.");
        }
        User user = new User(name, age,gender);
        users.put(name, user);
    }
}
