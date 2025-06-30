package br.com.klab.poc_current_token_manager.tokenmanagement.tokenControl;

import br.com.klab.poc_current_token_manager.tokenmanagement.config.ConfigParams;
import br.com.klab.poc_current_token_manager.tokenmanagement.entity.ExpiresFactorEntity;
import br.com.klab.poc_current_token_manager.tokenmanagement.entity.StatusEntity;
import br.com.klab.poc_current_token_manager.tokenmanagement.model.TokenModel;
import br.com.klab.poc_current_token_manager.tokenmanagement.repository.MemoryTokenRepository;
import br.com.klab.poc_current_token_manager.tokenmanagement.repository.TokenRepository;
import br.com.klab.poc_current_token_manager.tokenmanagement.values.AsyncCallStatus;
import br.com.klab.poc_current_token_manager.tokenmanagement.values.ColorsLevel;

import java.util.Objects;

public class TokenManagement {

    private TokenRepository tokenRepository;
    private final String tokenName;

    private TokenRepository getTokenRepository() {
        return this.tokenRepository;
    }

    public void setTokenRepository(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public TokenManagement(String tokenName) {
        this.tokenName = tokenName;
        this.setTokenRepository(MemoryTokenRepository.instance(tokenName));
    }

    public String getTokenName() {
        return tokenName;
    }

    private ConfigParams getConfigParams() {
        return ConfigParams.instance(getTokenName());
    }

    public void setAsyncCallStatusRunning() {
        getConfigParams().setAsyncCallStatus(AsyncCallStatus.RUNNING);
    }

    public void setAsyncCallStatusStandBy() {
        getConfigParams().setAsyncCallStatus(AsyncCallStatus.STAND_BY);
    }

    public void setAsyncCallStatusError() {
        getConfigParams().setAsyncCallStatus(AsyncCallStatus.ERROR);
    }

    public void configExpiresFactor(ExpiresFactorEntity entity) {
        getConfigParams().setExpiresFactorEntity(entity);
    }

    public void update(TokenModel tokenModel) {
        this.getTokenRepository().setModel(tokenModel, getTokenName());
        getConfigParams().setStartTime();
    }

    private ColorsLevel statusColor() {
        return getConfigParams().statusColor(this.expiresElapsedPercentage());
    }

    public TokenModel tokenModel() {
        return this.getTokenRepository().getModel(tokenName);
    }

    public Boolean isNullTokenModel() {
        return Objects.isNull(this.tokenModel());
    }

    private Long getExpiresInMilli() {
        return this.tokenModel().getExpiresIn() * 1000;
    }

    private Long getElapsedTimeMilli() {
        return System.currentTimeMillis() - getConfigParams().getStartTime();
    }

    private Long expiresElapsedPercentage() {
        if (this.isNullTokenModel()) {
            return 0L;
        }
        return (this.getElapsedTimeMilli() * 100) / this.getExpiresInMilli();
    }


    private Boolean statusColorIsRed() {
        return this.statusColor().equals(ColorsLevel.RED);
    }

    public StatusEntity currentStatus() {
        StatusEntity statusEntity = StatusEntity.init();
        statusEntity.setCurrentValid();
        if (this.isNullTokenModel()) {
            statusEntity.setCurrentWithoutToken();
        } else if (this.statusColorIsRed()) {
            statusEntity.setCurrentExpired();
        }
        if (this.isNullTokenModel()){
            statusEntity.setExpiresIn(0);
        }else{
            statusEntity.setExpiresIn(this.tokenModel().getExpiresIn());
        }
        statusEntity.setPercentage(this.expiresElapsedPercentage());
        statusEntity.setLevel(this.statusColor());
        statusEntity.setAsyncCallStatusEntity(getConfigParams().getAsyncCallStatusEntity());
        return statusEntity;
    }
}