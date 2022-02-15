package com.tester.tester.Entity;


import javax.validation.constraints.NotNull;

public class LoginEntity {
    @NotNull(message = "用户名不允许为空")
    private String username;
    @NotNull(message = "用户名不允许为空")
    private String password;
    private String phone;
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
