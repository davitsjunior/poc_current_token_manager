package br.com.klab.poc_current_token_manager.dto;

import br.com.klab.poc_current_token_manager.tokenmanagement.entity.StatusEntity;
import br.com.klab.poc_current_token_manager.tokenmanagement.model.TokenModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse extends TokenModel {
        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("expires_in")
        private Long expiresIn;
        @JsonProperty("token_type")
        private String tokenType;

         private StatusEntity statusEntity;

    public TokenResponse(String accessToken, Long expiresIn, String tokenType, StatusEntity statusEntity) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;
        this.statusEntity = statusEntity;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String getTokenType() {
        return tokenType;
    }

    @Override
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }
}
