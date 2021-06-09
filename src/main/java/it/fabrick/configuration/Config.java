package it.fabrick.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

@Configuration
public class Config {

    @Value("${auth.schema}")
    private String authSchema;

    @Value("${api.key}")
    private String apiKey;

    @Value("${id.chiave}")
    private String idChiave;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpEntity httpEntity() {
        return new HttpEntity<>(
            new LinkedMultiValueMap<>(
                Map.of(
                "Auth-Schema", Collections.singletonList(authSchema),
                "Api-Key", Collections.singletonList(apiKey),
                "Id-chiave", Collections.singletonList(idChiave)
        )));
    }

}
