package com.hajres.domain.dto;

import com.hajres.domain.entity.User;
import com.hajres.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class EditUserDto {
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String username;
    @NotNull
    @Size(min = 1, message = "is required")
    private String firstName;
    @NotNull
    @Size(min = 1, message = "is required")
    private String lastName;
    @NotNull
    @Size(min = 1, message = "is required")
    @ValidEmail
    private String email;
    @NotNull
    private String country;

    private String category;

    private List<String> roles;

    public EditUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static EditUserDto map(User user) {
        EditUserDto editUser = new EditUserDto();
        editUser.username = user.getUsername();
        editUser.firstName = user.getFirstName();
        editUser.lastName = user.getLastName();
        editUser.email = user.getEmail();
        editUser.country = user.getCountryIdOrNull();
        editUser.category = user.getCategoryIdOrNull();
        editUser.roles = new ArrayList<>();
        user.getRoles().forEach(r -> {
            editUser.roles.add(r.getName());
        });
        return editUser;
    }
}
