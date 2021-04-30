package com.poc6.dto;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.poc6.model.User;


public class ResponseDto {

    private final List<User> users;
    private final Map<String, Integer> page;

    public ResponseDto(final List<User> users, final Map<String, Integer> page) {
        this.users = new ArrayList<>(users);
        this.page = new HashMap<>(page);
    }

    public static ResponseDto create(final List<User> users, final Map<String, Integer> page) {
        return new ResponseDto(users, page);
    }

    public List<User> getResidents() {
        return users;
    }

    public Map<String, Integer> getPage() {
        return page;
    }
}
