package br.com.klab.poc_current_token_manager.service;

import br.com.klab.poc_current_token_manager.client.TokenFeignClient;
import br.com.klab.poc_current_token_manager.dto.TokenResponse;
import br.com.klab.poc_current_token_manager.property.CredentialsToken;
import br.com.klab.poc_current_token_manager.property.KeycloakFormRequest;
import br.com.klab.poc_current_token_manager.tokenmanagement.exception.GenerateTokenException;
import br.com.klab.poc_current_token_manager.tokenmanagement.service.GenerateTokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceAPI implements GenerateTokenService {

    private final TokenFeignClient client;
    private final CredentialsToken credentials;

    public TokenServiceAPI(TokenFeignClient client, CredentialsToken credentials) {
        this.client = client;
        this.credentials = credentials;
    }

    @Override
    public TokenResponse execute() throws GenerateTokenException {
        String formData = KeycloakFormRequest.ini(credentials.grant_type(),
                credentials.client_id(), credentials.client_secret()).generateForm();

        return this.client.getToken(formData);
    }
}
