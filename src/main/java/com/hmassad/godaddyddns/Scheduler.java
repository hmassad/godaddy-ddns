package com.hmassad.godaddyddns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Scheduler {

    private final GoDaddyDDnsService goDaddyDDnsService;

    @Autowired
    public Scheduler(GoDaddyDDnsService goDaddyDDnsService) {
        this.goDaddyDDnsService = goDaddyDDnsService;
    }

    @Scheduled(fixedRateString = "${scheduler.interval:600000}", initialDelay = 0)
    public void schedule() {
        goDaddyDDnsService.verifyAndUpdate();
    }
}
