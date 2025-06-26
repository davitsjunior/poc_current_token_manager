package br.com.klab.poc_current_token_manager.tokenmanagement.entity;

import br.com.klab.poc_current_token_manager.tokenmanagement.values.ColorsLevel;
import br.com.klab.poc_current_token_manager.tokenmanagement.values.TokenStatus;
import com.google.gson.Gson;


public class StatusEntity {

    private TokenStatus current;

    private Long percentage;

    private ColorsLevel level;

    private AsyncCallStatusEntity asyncCallStatusEntity;

    private long expiresIn;

    private StatusEntity() {
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public static StatusEntity init() {
        return new StatusEntity();
    }

    public TokenStatus getCurrent() {
        return this.current;
    }

    public void setCurrent(TokenStatus current) {
        this.current = current;
    }

    public void setCurrentValid() {
        this.setCurrent(TokenStatus.VALID);
    }

    public void setCurrentWithoutToken() {
        this.setCurrent(TokenStatus.WITHOUT_TOKEN);
    }

    public void setCurrentExpired() {
        this.setCurrent(TokenStatus.EXPIRED);
    }

    public Long getPercentage() {
        return this.percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public ColorsLevel getLevel() {
        return this.level;
    }

    public void setLevel(ColorsLevel level) {
        this.level = level;
    }

    public AsyncCallStatusEntity getAsyncCallStatusEntity() {
        return this.asyncCallStatusEntity;
    }

    public void setAsyncCallStatusEntity(AsyncCallStatusEntity asyncCallStatusEntity) {
        this.asyncCallStatusEntity = asyncCallStatusEntity;
    }

    public Boolean isNotRunning() {
        return !this.getAsyncCallStatusEntity().isRunning();
    }


    public Boolean withoutToken() {
        return this.getCurrent().equals(TokenStatus.WITHOUT_TOKEN);
    }

    public Boolean expired() {
        return this.getCurrent().equals(TokenStatus.EXPIRED);
    }

    public Boolean valid() {
        return this.getCurrent().equals(TokenStatus.VALID);
    }

    public Boolean green() {
        return this.getLevel().equals(ColorsLevel.GREEN);
    }

    public Boolean yellow() {
        return this.getLevel().equals(ColorsLevel.YELLOW);
    }

    public Boolean orange() {
        return this.getLevel().equals(ColorsLevel.ORANGE);
    }

    public Boolean red() {
        return this.getLevel().equals(ColorsLevel.RED);
    }

    public Boolean isNotGreen() {
        return this.yellow() || this.orange() || this.red();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
