package br.com.klab.poc_current_token_manager.tokenmanagement.tokenControl;

import br.com.klab.poc_current_token_manager.tokenmanagement.entity.ExpiresFactorEntity;
import br.com.klab.poc_current_token_manager.tokenmanagement.entity.StatusEntity;
import br.com.klab.poc_current_token_manager.tokenmanagement.exception.GenerateTokenException;
import br.com.klab.poc_current_token_manager.tokenmanagement.model.TokenModel;
import br.com.klab.poc_current_token_manager.tokenmanagement.repository.TokenRepository;
import br.com.klab.poc_current_token_manager.tokenmanagement.service.GenerateTokenService;
import co.elastic.apm.api.CaptureSpan;

import java.util.concurrent.atomic.AtomicBoolean;

public class CurrentToken {

    private final GenerateTokenService tokenService;

    private final TokenManagement tokenManagement;

    private final AtomicBoolean syncInProgress = new AtomicBoolean(false);

    private CurrentToken(GenerateTokenService tokenService, String tokenName) {
        this.tokenService = tokenService;
        this.tokenManagement = new TokenManagement(tokenName);
    }

    private CurrentToken(GenerateTokenService tokenService, ExpiresFactorEntity expiresFactorEntity, String tokenName) {
        this.tokenService = tokenService;
        this.tokenManagement = new TokenManagement(tokenName);
        this.tokenManagement.configExpiresFactor(expiresFactorEntity);
    }

    public static CurrentToken init(GenerateTokenService tokenService, String tokenName) {
        return new CurrentToken(tokenService, tokenName);
    }

    public static CurrentToken init(GenerateTokenService tokenService, ExpiresFactorEntity expiresFactorEntity, String tokenName) {
        return new CurrentToken(tokenService, expiresFactorEntity, tokenName);
    }

    public void repository(TokenRepository repository) {
        this.tokenManagement.setTokenRepository(repository);
    }

    @CaptureSpan
    public TokenModel getCurrentToken() throws GenerateTokenException {
        StatusEntity status = this.tokenManagement.currentStatus();
        if (status.withoutToken() || status.red()) {
            this.trySyncCall();
        }
        if (status.isNotRunning() && status.isNotGreen()) {
            this.asyncCall();
        }
        return this.tokenManagement.tokenModel();
    }

//    @CaptureSpan
//    private void syncCall() throws GenerateTokenException {
//        this.generateToken();
//    }

    @CaptureSpan
    private void trySyncCall() {
        if (syncInProgress.compareAndSet(false, true)) {
            try {
                this.generateToken();
            } catch (GenerateTokenException e) {
                //TODO Validar
            } finally {
                syncInProgress.set(false);
            }
        }
    }

    @CaptureSpan
    private void asyncCall() {
        new Thread(() -> {
            try {
                this.tokenManagement.setAsyncCallStatusRunning();
                this.generateToken();
                this.tokenManagement.setAsyncCallStatusStandBy();
            } catch (GenerateTokenException e) {
                this.tokenManagement.setAsyncCallStatusError();
            }
        }).start();
    }

    private void generateToken() throws GenerateTokenException {
        TokenModel tokenModel = this.tokenService.execute();
        this.tokenManagement.update(tokenModel);
    }

    public StatusEntity currentStatus() {
        return this.tokenManagement.currentStatus();
    }
}