package com.springboot.springsecurity.config;

import org.springframework.web.client.RestTemplate;

public class SocailLoginConfig {

    private static void googleLogin() {
        final String uri = "http://localhost:8082/code/authorization/google";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }

}
