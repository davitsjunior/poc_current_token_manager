package br.com.klab.poc_current_token_manager.controller;

import br.com.klab.poc_current_token_manager.dto.TokenResponse;
import br.com.klab.poc_current_token_manager.service.GetTokenService;
import br.com.klab.poc_current_token_manager.service.GetTokenServiceDuplicate;
import br.com.klab.poc_current_token_manager.tokenmanagement.exception.GenerateTokenException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TokenViewController {

    private final GetTokenService getTokenService;
    private final GetTokenServiceDuplicate getTokenServiceDuplicate;

    public TokenViewController(GetTokenService getTokenService, GetTokenServiceDuplicate getTokenServiceDuplicate) {
        this.getTokenService = getTokenService;
        this.getTokenServiceDuplicate = getTokenServiceDuplicate;
    }

    @GetMapping("/tokens/view")
    public String viewTokens(Model model) throws GenerateTokenException {
        Map<String, TokenResponse> service1Tokens = getTokenService.execute();
        Map<String, TokenResponse> service2Tokens = getTokenServiceDuplicate.execute();

        model.addAttribute("service1Tokens", service1Tokens);
        model.addAttribute("service2Tokens", service2Tokens);

        return "tokens-view";
    }
}
