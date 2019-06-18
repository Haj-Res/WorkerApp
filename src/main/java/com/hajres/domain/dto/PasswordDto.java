package com.hajres.domain.dto;

import com.hajres.validation.FieldMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({@FieldMatch(first = "newPassword", second = "newMatchingPassword", message = "Passwords must match.")})
public class PasswordDto {
    @NotNull
    @Size(min = 1, message = "is required")
    private String oldPassword;
    @NotNull
    @Size(min = 1, message = "is required")
    private String newPassword;
    @NotNull
    @Size(min = 1, message = "is required")
    private String newMatchingPassword;

    public PasswordDto() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewMatchingPassword() {
        return newMatchingPassword;
    }

    public void setNewMatchingPassword(String newMatchingPassword) {
        this.newMatchingPassword = newMatchingPassword;
    }
}
