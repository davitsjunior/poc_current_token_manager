package br.com.klab.poc_current_token_manager.service.token;

import br.com.klab.poc_current_token_manager.enums.TokenNameEnum;
import br.com.klab.poc_current_token_manager.service.TokenServiceAPI;
import br.com.klab.poc_current_token_manager.tokenmanagement.entity.ExpiresFactorEntity;
import br.com.klab.poc_current_token_manager.tokenmanagement.tokenControl.CurrentToken;
import br.com.klab.poc_current_token_manager.tokenmanagement.tokenControl.CurrentTokenManager;
import org.springframework.stereotype.Service;

@Service
public class CurrentTokenManagerService {

    private final TokenServiceAPI serviceAPI;

    public CurrentTokenManagerService(TokenServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    public CurrentToken init(TokenNameEnum tokenNameEnum){
        return CurrentTokenManager.getOrCreate(tokenNameEnum.name(), serviceAPI, ExpiresFactorEntity.init(10));
    }
}
