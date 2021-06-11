package it.fabrick.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpUtilsTest {

    @Test
    public void createStringUrlTest() {

        assertThat(HttpUtils.builder()
            .domain("http://localhost:8080")
            .uri("/testUri")
            .build()
            .createStringUrl(),
            is("http://localhost:8080/testUri"));

        assertThat(HttpUtils.builder()
            .url("http://localhost:8080/testUri")
            .build()
            .createStringUrl(),
            is("http://localhost:8080/testUri"));

        assertThat(HttpUtils.builder()
            .domain("http://localhost:8080")
            .uri("/testUri/{id}")
            .pathVariables(new String[]{"1234"})
            .build()
            .createStringUrl(),
            is("http://localhost:8080/testUri/1234"));

        assertThat(HttpUtils.builder()
            .domain("http://localhost:8080")
            .uri("/testUri/{id}")
            .pathVariables(new String[]{"1234"})
            .queryParams(new LinkedMultiValueMap<>(Map.of(
                "param1", Collections.singletonList("12"),
                "param2", Collections.singletonList("34")))
            )
            .build()
            .createStringUrl(),
            anyOf(
            Arrays.asList(
                equalToObject("http://localhost:8080/testUri/1234?param1=12&param2=34"),
                equalToObject("http://localhost:8080/testUri/1234?param2=34&param1=12"))));

    }
}
