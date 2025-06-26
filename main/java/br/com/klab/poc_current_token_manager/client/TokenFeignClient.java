package br.com.klab.poc_current_token_manager.client;

import br.com.klab.poc_current_token_manager.dto.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(name = "${service.client-token.name}",
        url = "${service.client-token.url}")
public interface TokenFeignClient {

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    TokenResponse getToken(String formRequest);
}
