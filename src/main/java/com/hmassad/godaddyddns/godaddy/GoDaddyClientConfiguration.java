package com.hmassad.godaddyddns.godaddy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GoDaddyClientConfiguration {

    @Bean(name = "GoDaddyRestTemplate")
    public RestTemplate buildGoDaddyRestTemplate(
            @Value("${godaddy.api.key:}") String apiKey,
            @Value("${godaddy.api.secret:}") String apiSecret) {

        if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(apiSecret))
            throw new RuntimeException("invalid GoDaddy API keys");


        return new RestTemplateBuilder()
                .additionalInterceptors((request, body, execution) -> {
                    request.getHeaders().add("Authorization", "sso-key " + apiKey + ":" + apiSecret);
                    return execution.execute(request, body);
                })
                .build();
    }
}
