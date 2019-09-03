package com.hmassad.godaddyddns;

import com.hmassad.godaddyddns.godaddy.GoDaddyDomainsService;
import com.hmassad.godaddyddns.ipinfo.IPInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoDaddyDDnsService {

    private final GoDaddyDomainsService goDaddyDomainsService;
    private final IPInfoService ipInfoService;

    @Autowired
    public GoDaddyDDnsService(GoDaddyDomainsService goDaddyDomainsService, IPInfoService ipInfoService) {
        this.goDaddyDomainsService = goDaddyDomainsService;
        this.ipInfoService = ipInfoService;
    }

    public void verifyAndUpdate() {
        log.info("verifying public IP and configured IP...");

        String ipInfoIP = ipInfoService.getIP();
        if (ipInfoIP == null) {
            log.error("IP Info returned an empty IP");
            return;
        }

        String goDaddyIP = goDaddyDomainsService.getConfiguredIP();
        if (goDaddyIP == null) {
            log.error("GoDaddy returned an empty IP");
            return;
        }

        if (ipInfoIP.equals(goDaddyIP)) {
            log.info("IPs are the same.");
            return;
        }

        log.info("IPs are not the same, updating...");
        goDaddyDomainsService.updateConfiguredIP(ipInfoIP);
        log.info("GoDaddy IP updated.");
    }
}
