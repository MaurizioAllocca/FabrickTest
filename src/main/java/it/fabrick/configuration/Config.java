package it.fabrick.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

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
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Schema", authSchema);
        headers.add("Api-Key", apiKey);
        headers.add("Id-chiave", idChiave);
        return new HttpEntity<>(headers);
    }

}
