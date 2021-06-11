package it.fabrick.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class RestTemplateUtils {

    private static RestTemplate rt;

    @Autowired
    private RestTemplate rtBean;

    public static <T> T makeRequest(
        String url, HttpMethod method, HttpEntity entity, Class<T> clazz
    ) {
        ResponseEntity<T> re = rt
            .exchange(
                url,
                method,
                entity,
                clazz);

        return re != null ? re.getBody() : null;

    }

    @PostConstruct
    public void init() {
        this.rt = rtBean;
    }
}
