package com.example.bookstore.model.dto;

import jakarta.validation.constraints.*;

public class UserRegisterDto {
    private String username;
    private String fullName;
    private String email;
    private Integer age;
    private String password;
    private String confirmPassword;

    public UserRegisterDto() {
    }

    @NotNull(message = "Required username")
    @Size(min = 2, max = 25, message = "Username must be between 2 and 25 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "Required full name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @NotBlank(message = "Required email")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Required age")
    @Positive()
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @NotNull(message = "Required password")
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "Required password")
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
