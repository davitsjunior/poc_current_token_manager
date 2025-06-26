package br.com.klab.poc_current_token_manager.tokenmanagement.service;

import br.com.klab.poc_current_token_manager.tokenmanagement.exception.GenerateTokenException;
import br.com.klab.poc_current_token_manager.tokenmanagement.model.TokenModel;

public interface GenerateTokenService {

    public TokenModel execute() throws GenerateTokenException;

}
