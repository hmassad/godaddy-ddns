package com.hmassad.godaddyddns.ipinfo;

import com.hmassad.godaddyddns.ipinfo.dto.IPInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class IPInfoService {

    private static final String IP_INFO_URL = "http://ipinfo.io/json";

    public String getIP() {
        IPInfo ipInfo = new RestTemplateBuilder()
                .build()
                .getForObject(IP_INFO_URL, IPInfo.class);
        if (ipInfo == null)
            return null;
        return ipInfo.getIp();
    }
}
