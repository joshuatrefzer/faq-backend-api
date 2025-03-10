package com.example.faq_backend_api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.faq_backend_api.api.model.User;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();
        User user1 = new User(1, "Josh", 0, "exaple@mail");
        User user2 = new User(2, "Josh", 0, "exaple@mail");
        User user3 = new User(3, "Josh", 0, "exaple@mail");
        User user4 = new User(4, "Josh", 0, "exaple@mail");
        User user5 = new User(5, "Josh", 0, "exaple@mail");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
    
    @SuppressWarnings("unchecked")
    public Optional<User> getUser(Integer id){
        @SuppressWarnings("rawtypes")
        Optional optional = Optional.empty();
        for (User user: userList) {
            if (id == user.getId()) {
                optional = Optional.of(user);
                return optional;
            }

        }
        return optional;
    }
}