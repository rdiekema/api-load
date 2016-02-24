package org.diekema.skunk.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by rdiekema on 2/24/16.
 */
public class AuthenticationProvider {

    interface ApiEndpoints {
        final static String SCORER_ROOT = "/sanctionslistscorer";
        final static String SCORER_SCORE = SCORER_ROOT + "/api/scorer/score";

        final static String SECURITY_ROOT = SCORER_ROOT + "/security";
        final static String CSRF_TOKEN = SECURITY_ROOT + "/csrftoken";
        final static String HANDSHAKE = SECURITY_ROOT + "/handshake";
        final static String LOGIN = SECURITY_ROOT + "/login";

    }

    @Autowired
    RestTemplate restTemplate;

    private String ENDPOINT = "http://localhost";

    private static final String loginRequest = "{\n" +
            "  \"loginTokens\": [\n" +
            "   {\n" +
            "      \"key\": \"com.bottomline.security.provider.login.user\",\n" +
            "      \"value\": \"jdoe\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"key\": \"com.bottomline.security.provider.login.password\",\n" +
            "      \"value\": \"jdoe\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"key\": \"com.bottomline.security.provider.login.realm\",\n" +
            "      \"value\": \"demo\"\n" +
            "   }\n" +
            "  ],\n" +
            "  \"apiVersion\": {\n" +
            "        \"major\": \"1\",\n" +
            "        \"minor\": \"1\",\n" +
            "        \"patch\": \"0\",\n" +
            "        \"build\": \"0\"\n" +
            "   },\n" +
            "  \"purpose\": \"scoring\"\n" +
            "}";

    private HttpHeaders headers = new HttpHeaders();
    {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public AuthenticationProvider(final String endpoint) {
        this.ENDPOINT = endpoint;
    }

    public HttpHeaders authenticate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ApiLogin apiLogin = objectMapper.readValue(loginRequest, ApiLogin.class);


        HttpEntity<ApiLogin> apiLoginHttpEntity = new HttpEntity<>(apiLogin, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(ApiEndpoints.LOGIN, HttpMethod.GET, apiLoginHttpEntity, String.class);

        return headers;
    }


}


