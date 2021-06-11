package it.fabrick.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    @Value("${auth.schema}")
    private String authSchema;

    @Value("${api.key}")
    private String apiKey;

    @Value("${id.chiave}")
    private String idChiave;

    @Override
    public ClientHttpResponse intercept(
        HttpRequest request, byte[] body, ClientHttpRequestExecution execution
    ) throws IOException {
        request.getHeaders().add("Auth-Schema", authSchema);
        request.getHeaders().add("Api-Key", apiKey);
        request.getHeaders().add("Id-chiave", idChiave);
        request.getHeaders().set("Content-Type", "application/json");
        return execution.execute(request, body);
    }
}