package edu.tum.ase.ase23.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangeEmailRequest {
    @NotBlank
    @Size(min = 6, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String oldEmail;

    @NotBlank
    @Size(max = 50)
    @Email
    private String newEmail;

    @NotBlank
    @Size(max = 50)
    @Email
    private String newEmailRepeat;

    public ChangeEmailRequest(String username, String password, String oldEmail, String newEmail, String newEmailRepeat) {
        this.username = username;
        this.password = password;
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
        this.newEmailRepeat = newEmailRepeat;
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

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewEmailRepeat() {
        return newEmailRepeat;
    }

    public void setNewEmailRepeat(String newEmailRepeat) {
        this.newEmailRepeat = newEmailRepeat;
    }
}
