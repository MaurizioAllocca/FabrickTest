package it.fabrick.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpUtils {

    private static final String ATTACHMENT = "attachment; filename=";

    private String url;
    private String domain;
    private String uri;
    private String[] pathVariables;
    private MultiValueMap<String, String> queryParams;

    public String createStringUrl() {
        if (pathVariables != null && pathVariables.length > 0) {
            return buildQueryParams(buildUrl())
                    .buildAndExpand(pathVariables)
                    .toUriString();
        }

        return buildQueryParams(buildUrl()).toUriString();
    }

    private UriComponentsBuilder buildUrl() {
        if (StringUtils.isEmpty(url)) {
            return UriComponentsBuilder.fromHttpUrl(domain + uri);
        }

        return UriComponentsBuilder.fromHttpUrl(url);
    }

    private UriComponentsBuilder buildQueryParams(UriComponentsBuilder uriComponentsBuilder) {
        if (queryParams != null && queryParams.size() > 0) {
            return uriComponentsBuilder.queryParams(queryParams);
        }

        return uriComponentsBuilder;
    }

}