package br.com.klab.poc_current_token_manager.tokenmanagement.entity;

import br.com.klab.poc_current_token_manager.tokenmanagement.values.ColorsLevel;

public class ExpiresFactorEntity {

    private static final int MIN_PERCENTAGE = 0;
    private static final int MAX_PERCENTAGE = 100;

    private final Integer expiresFactor;

    public ExpiresFactorEntity(Integer value) {
        validateValue(value);
        this.expiresFactor = value;
    }

    public static ExpiresFactorEntity init(Integer value) {
        return new ExpiresFactorEntity(value);
    }

    public Integer getExpiresFactor() {
        return expiresFactor;
    }

    public Integer getMin() {
        return MIN_PERCENTAGE;
    }

    public Integer getMax() {
        return MAX_PERCENTAGE;
    }

    public Integer getIntermediateLevelValue() {
        return expiresFactor + Math.round((float) (MAX_PERCENTAGE - expiresFactor) / 2);
    }

    public ColorsLevel statusColor(Long percentage) {
        if (percentage >= getMin() && percentage < expiresFactor) {
            return ColorsLevel.GREEN;
        }
        if (percentage >= expiresFactor && percentage < getIntermediateLevelValue()) {
            return ColorsLevel.YELLOW;
        }
        if (percentage >= getIntermediateLevelValue() && percentage < getMax()) {
            return ColorsLevel.ORANGE;
        }
        return ColorsLevel.RED;
    }

    private void validateValue(Integer value) {
        if (value == null || value < MIN_PERCENTAGE || value > MAX_PERCENTAGE) {
            throw new IllegalArgumentException("Value must be between 0 and 100.");
        }
    }
}