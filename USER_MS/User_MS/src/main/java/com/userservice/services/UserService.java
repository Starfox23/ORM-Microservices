package com.userservice.services;

import com.userservice.dataaccess.UserDataAccessObject;
import com.userservice.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserService {
    private final UserDataAccessObject userDataAccessObject;

    public UserService() {
        userDataAccessObject = new UserDataAccessObject();
    }

    public ArrayList<User> getAllUsers() {
        return userDataAccessObject.getAllUsers();
    }

    private boolean isUsernameUnique(String username) {
        ArrayList<User> users = userDataAccessObject.getAllUsers();
        ArrayList<String> usernames = (ArrayList<String>) users.stream().map(x->x.getUsername()).collect(Collectors.toList());
        return !usernames.contains(username);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }
        return userDataAccessObject.getUserByUsernameAndPassword(username, password);
    }

    public boolean addUser(String response) {
        if (response.isEmpty()) {
            return false;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = mapper.readValue(response, new TypeReference<User>() { });
            if (isUsernameUnique(user.getUsername())) {
                userDataAccessObject.addUser(user);
                return true;
            }
            return false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
