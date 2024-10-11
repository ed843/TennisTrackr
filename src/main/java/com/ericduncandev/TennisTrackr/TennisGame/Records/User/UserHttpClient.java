package com.ericduncandev.TennisTrackr.TennisGame.Records.User;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface UserHttpClient {
    @GetExchange("/users")
    List<User> findAll();

    @GetExchange("/users/{id}")
    User findById(int id);
}
