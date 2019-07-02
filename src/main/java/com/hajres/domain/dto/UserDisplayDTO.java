package com.hajres.domain.dto;

import com.hajres.domain.entity.User;

public class UserDisplayDTO {
    private String username;
    private String email;
    private String roles;
    private String category;
    private String country;

    public UserDisplayDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static UserDisplayDTO map(User user) {
        UserDisplayDTO dto = new UserDisplayDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCategory(user.getCategoryPreference() == null ? "None": user.getCategoryPreference().getName());
        dto.setCountry(user.getCountryPreference() == null ? "None": user.getCountryPreference().getInternationalName());
        StringBuilder sb = new StringBuilder();
        user.getRoles().forEach(role -> {
            sb.append(role.getDisplayName()).append(", ");
        });
        String roles = sb.toString();
        roles = roles.substring(0, roles.length()-2);
        dto.setRoles(roles);
        return dto;
    }
}
