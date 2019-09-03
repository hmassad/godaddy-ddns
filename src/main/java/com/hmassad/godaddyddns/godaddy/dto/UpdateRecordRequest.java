package com.hmassad.godaddyddns.godaddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecordRequest {
    private Integer ttl;
    private String data;
}
