package com.currencyrates.service;

import com.currencyrates.dto.UserInput;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class UserService {
    private UserInput userInput;
}
