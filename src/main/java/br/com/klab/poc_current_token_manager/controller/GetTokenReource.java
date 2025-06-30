package br.com.klab.poc_current_token_manager.controller;

import br.com.klab.poc_current_token_manager.dto.TokenResponse;
import br.com.klab.poc_current_token_manager.service.GetTokenService;
import br.com.klab.poc_current_token_manager.service.GetTokenServiceDuplicate;
import br.com.klab.poc_current_token_manager.tokenmanagement.exception.GenerateTokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetTokenReource {

    private final GetTokenService getTokenService;
    private final GetTokenServiceDuplicate getTokenServiceDuplicate;

    public GetTokenReource(GetTokenService getTokenService, GetTokenServiceDuplicate getTokenServiceDuplicate) {
        this.getTokenService = getTokenService;
        this.getTokenServiceDuplicate = getTokenServiceDuplicate;
    }


    @GetMapping("/get")
    public ResponseEntity<Map<String, Map<String, TokenResponse>>> getToken() throws GenerateTokenException {
        Map<String, TokenResponse> response1 = this.getTokenService.execute();
        Map<String, TokenResponse> response2 = this.getTokenServiceDuplicate.execute();


        Map<String, Map<String, TokenResponse>> tokenResponseMap = new HashMap<>();
        tokenResponseMap.put("Service-01", response1);
        tokenResponseMap.put("Service-02", response2);
        return ResponseEntity.ok(tokenResponseMap);
    }


}
