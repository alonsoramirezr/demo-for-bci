package com.example.demo.request;

import com.example.demo.model.Phone;
import lombok.Data;
import java.util.List;

@Data
public class UserUpdateRequest {
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private boolean isActive;
}
