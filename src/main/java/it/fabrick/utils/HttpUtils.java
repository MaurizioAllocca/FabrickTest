package it.fabrick.utils;

import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Builder
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
