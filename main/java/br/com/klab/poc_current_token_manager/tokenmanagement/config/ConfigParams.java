package br.com.klab.poc_current_token_manager.tokenmanagement.config;

import br.com.klab.poc_current_token_manager.tokenmanagement.entity.AsyncCallStatusEntity;
import br.com.klab.poc_current_token_manager.tokenmanagement.entity.ExpiresFactorEntity;
import br.com.klab.poc_current_token_manager.tokenmanagement.values.AsyncCallStatus;
import br.com.klab.poc_current_token_manager.tokenmanagement.values.ColorsLevel;

import java.util.HashMap;
import java.util.Map;

public class ConfigParams {

    public static Map<String, ConfigParams> configParams;

    private ConfigParams() {
        this.setExpiresFactorEntity(ExpiresFactorEntity.init(50));
        this.setAsyncCallStatus(AsyncCallStatus.STAND_BY);
    }

    public static ConfigParams instance(String tokenName) {
        if (configParams == null) {
            configParams = new HashMap<>();
        }
        return configParams.computeIfAbsent(tokenName, k -> new ConfigParams());
    }

    private Long startTime;

    private ExpiresFactorEntity expiresFactorEntity;

    private AsyncCallStatusEntity asyncCallStatusEntity;

    private ExpiresFactorEntity getExpiresFactorEntity() {
        return this.expiresFactorEntity;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public ColorsLevel statusColor(Long percentage) {
        return this.getExpiresFactorEntity().statusColor(percentage);
    }

    public void setExpiresFactorEntity(ExpiresFactorEntity expiresFactorEntity) {
        this.expiresFactorEntity = expiresFactorEntity;
    }

    public AsyncCallStatusEntity getAsyncCallStatusEntity() {
        return this.asyncCallStatusEntity;
    }

    public void setAsyncCallStatus(AsyncCallStatus asyncCallStatus) {
        this.asyncCallStatusEntity = AsyncCallStatusEntity.start(asyncCallStatus);
    }
}
