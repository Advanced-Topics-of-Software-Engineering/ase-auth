package edu.tum.ase.ase23.payload.response;

public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String id;
    private String username;
    private String email;
    private String userType;

    public JwtResponse(String accessToken, String id, String username, String email, String userType) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.userType = userType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
