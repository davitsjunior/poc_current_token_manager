package br.com.klab.poc_current_token_manager.tokenmanagement.tokenControl;

import br.com.klab.poc_current_token_manager.tokenmanagement.entity.ExpiresFactorEntity;
import br.com.klab.poc_current_token_manager.tokenmanagement.service.GenerateTokenService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CurrentTokenManager {

    private static final Map<String, CurrentToken> cache = new ConcurrentHashMap<>();

    private CurrentTokenManager() {
    }

    public static CurrentToken getOrCreate(String tokenName, GenerateTokenService tokenService) {
        return cache.computeIfAbsent(tokenName, name -> CurrentToken.init(tokenService, name));
    }

    public static CurrentToken getOrCreate(String tokenName, GenerateTokenService tokenService, ExpiresFactorEntity factor) {
        return cache.computeIfAbsent(tokenName, name -> CurrentToken.init(tokenService, factor, name));
    }

    public static void remove(String tokenName) {
        cache.remove(tokenName);
    }

    public static void clear() {
        cache.clear();
    }
}
