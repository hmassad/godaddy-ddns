package com.hmassad.godaddyddns.ipinfo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class IPInfo {
    private String ip;
    private String hostname;
    private String city;
    private String region;
    private String country;
    private String loc;
    private String org;
    private String postal;
}
