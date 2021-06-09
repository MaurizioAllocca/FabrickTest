package it.fabrick.configuration;

import it.fabrick.interceptor.RestTemplateHeaderModifierInterceptor;
import it.fabrick.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Configuration
public class Config {

    @Autowired
    private RestTemplateHeaderModifierInterceptor restTemplateHeaderModifierInterceptor;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rt = new RestTemplate();
        rt.getInterceptors().add(restTemplateHeaderModifierInterceptor);
        return rt;
    }

}
