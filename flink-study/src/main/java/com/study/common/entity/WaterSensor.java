package com.study.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class WaterSensor {
    private String id;
    private Long ts;
    private Integer vc;
}
