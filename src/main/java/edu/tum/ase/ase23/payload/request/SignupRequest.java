package edu.tum.ase.ase23.payload.request;


import javax.validation.constraints.*;

public class SignupRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    private String userType;

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private String RFIDToken;

    public SignupRequest(String username, String email, String password, String userType, String RFIDToken) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.RFIDToken = RFIDToken;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRFIDToken() {
        return this.RFIDToken;
    }

    public void setRFIDToken(String RFIDToken) {
        this.RFIDToken = RFIDToken;
    }
}
