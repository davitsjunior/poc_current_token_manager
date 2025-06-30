package br.com.klab.poc_current_token_manager.tokenmanagement.repository;

import br.com.klab.poc_current_token_manager.tokenmanagement.model.TokenModel;

public interface TokenRepository {
    void setModel(TokenModel tokenModel, String tokenName);
    TokenModel getModel(String tokenName);
}
