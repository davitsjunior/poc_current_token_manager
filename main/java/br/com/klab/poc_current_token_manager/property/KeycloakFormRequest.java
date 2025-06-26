package br.com.klab.poc_current_token_manager.property;

import org.springframework.web.util.UriComponentsBuilder;

public class KeycloakFormRequest {

    private final String grantType;
    private final String clientId;
    private final String clientSecret;

    public KeycloakFormRequest(String grantType, String clientId, String clientSecret) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public static KeycloakFormRequest ini(String grantType, String clientId, String clientSecret){
        return new KeycloakFormRequest(grantType, clientId, clientSecret);
    }

    public String generateForm(){
        return UriComponentsBuilder.newInstance()
                .queryParam("grant_type", this.grantType)
                .queryParam("client_id", this.clientId)
                .queryParam("client_secret", this.clientSecret)
                .build()
                .encode()
                .toString()
                .replace("?", "");
    }
}
