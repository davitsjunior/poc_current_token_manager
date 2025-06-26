package br.com.klab.poc_current_token_manager.tokenmanagement.entity.adapter;

import br.com.klab.poc_current_token_manager.tokenmanagement.entity.StatusEntity;

import java.util.HashMap;
import java.util.Map;

public class StatusEntityAdapter {

    public static StatusEntityAdapter inicializa() {
        return new StatusEntityAdapter();
    }

    public Map<String, Object> converte(StatusEntity statusEntity, String tokenName) {
        Map<String, Object> statusDto = new HashMap<>();
        statusDto.put("current", statusEntity.getCurrent());
        statusDto.put("percentage", statusEntity.getPercentage());
        statusDto.put("level", statusEntity.getLevel());
        statusDto.put("expiresIn", statusEntity.getExpiresIn());
        statusDto.put("tokenName", tokenName);
        return statusDto;
    }
}
