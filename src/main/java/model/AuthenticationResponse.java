package model;

/**
 * @author mahesh gadupudi
 * @project adaptris-regulatory-compliance
 */
public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
