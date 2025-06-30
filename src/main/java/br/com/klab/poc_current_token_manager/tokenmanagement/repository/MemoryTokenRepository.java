package br.com.klab.poc_current_token_manager.tokenmanagement.repository;

import br.com.klab.poc_current_token_manager.tokenmanagement.model.TokenModel;

import java.util.HashMap;
import java.util.Map;

public class MemoryTokenRepository implements TokenRepository {

    private static Map<String, MemoryTokenRepository> memoryTokenRepository;
    private TokenModel tokenModel;

    public static MemoryTokenRepository instance(String tokenName) {
        if (memoryTokenRepository == null) {
            memoryTokenRepository = new HashMap<>();
        }
        return memoryTokenRepository.computeIfAbsent(tokenName, k -> new MemoryTokenRepository());
    }

    @Override
    public void setModel(TokenModel tokenModel, String tokenName) {
        memoryTokenRepository.get(tokenName).tokenModel = tokenModel;
    }

    @Override
    public TokenModel getModel(String tokenName) {
        MemoryTokenRepository repository = memoryTokenRepository.get(tokenName);
        return repository.tokenModel;
    }
}