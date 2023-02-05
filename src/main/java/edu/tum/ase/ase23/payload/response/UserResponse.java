package edu.tum.ase.ase23.payload.response;

public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String RFIDToken;
    private String role;

    public UserResponse(String id, String username, String email, String RFIDToken, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.RFIDToken = RFIDToken;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRFIDToken() {
        return RFIDToken;
    }

    public void setRFIDToken(String RFIDToken) {
        this.RFIDToken = RFIDToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
