package com.sanchitp.dev.task.management.system.admin.dto;

import com.sanchitp.dev.task.management.system.common.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

    @NotBlank
    @Size(min = 2,max = 50)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6 , max = 20)
    private String password;

    @NotNull
    private Role role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
