package br.com.klab.poc_current_token_manager.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service.client-token")
public record CredentialsToken(
        String grant_type,
        String client_id,
        String client_secret
) {
}
