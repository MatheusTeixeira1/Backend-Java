package com.pickpayclone.dtos;

import java.math.BigDecimal;

import com.pickpayclone.domain.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {

}
