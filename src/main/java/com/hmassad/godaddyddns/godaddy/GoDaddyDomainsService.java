package com.hmassad.godaddyddns.godaddy;

import com.hmassad.godaddyddns.godaddy.dto.Record;
import com.hmassad.godaddyddns.godaddy.dto.UpdateRecordRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GoDaddyDomainsService {

    @Qualifier("GoDaddyRestTemplate")
    private final RestTemplate goDaddyRestTemplate;

    @Value("${godaddy.domain:}")
    private String domain;

    @Value("${godaddy.host:}")
    private String host;

    @Autowired
    public GoDaddyDomainsService(RestTemplate goDaddyRestTemplate) {
        this.goDaddyRestTemplate = goDaddyRestTemplate;
    }

    public String getConfiguredIP() {
        // query GoDaddy
        ResponseEntity<List<Record>> response = goDaddyRestTemplate.exchange(
                "https://api.godaddy.com/v1/domains/" + domain + "/records/A",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Record>>() {
                });

        // find our record
        List<Record> records = response.getBody();
        if (records != null) {
            for (Record r : records) {
                if (r.getName().equals(host))
                    return r.getData();
            }
        }

        return null;
    }

    public void updateConfiguredIP(String ip) {

        goDaddyRestTemplate.put(
                "https://api.godaddy.com/v1/domains/" + domain + "/records/A/" + host,
                new UpdateRecordRequest[]{new UpdateRecordRequest(3600000, ip)}); // 1 hour
    }
}
