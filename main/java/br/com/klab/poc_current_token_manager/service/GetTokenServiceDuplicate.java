package br.com.klab.poc_current_token_manager.service;

import br.com.klab.poc_current_token_manager.dto.TokenResponse;
import br.com.klab.poc_current_token_manager.enums.TokenNameEnum;
import br.com.klab.poc_current_token_manager.service.token.CurrentTokenManagerService;
import br.com.klab.poc_current_token_manager.tokenmanagement.exception.GenerateTokenException;
import br.com.klab.poc_current_token_manager.tokenmanagement.tokenControl.CurrentToken;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetTokenServiceDuplicate {

    private final CurrentTokenManagerService currentTokenManagerService;

    public GetTokenServiceDuplicate(CurrentTokenManagerService currentTokenManagerService) {
        this.currentTokenManagerService = currentTokenManagerService;
    }

    public Map<String, TokenResponse> execute() throws GenerateTokenException {

        Map<String, TokenResponse> responseMap = new HashMap<>();
        Arrays.stream(TokenNameEnum.values()).forEach(tokenType -> {
            CurrentToken token = currentTokenManagerService.init(tokenType);
            TokenResponse tokenResponse = null;
            try {
                tokenResponse = (TokenResponse) token.getCurrentToken();
            } catch (GenerateTokenException e) {
                throw new RuntimeException(e);
            }
            tokenResponse.setStatusEntity(token.currentStatus());

            responseMap.put(
                    tokenType.name() + " (Hashs: " + System.identityHashCode(token) + ")",
                    tokenResponse
            );
        });
        return responseMap;
    }
}
