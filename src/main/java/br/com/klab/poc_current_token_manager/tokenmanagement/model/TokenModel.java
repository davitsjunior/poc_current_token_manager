package br.com.klab.poc_current_token_manager.tokenmanagement.model;

import java.util.Objects;

public class TokenModel {

    private String accessToken;

    private String expiresIn;

    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        if (Objects.nonNull(this.expiresIn)) {
            return Long.valueOf(this.expiresIn);
        }
        return Long.valueOf(0);
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
        return "TokenObject{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }

}
