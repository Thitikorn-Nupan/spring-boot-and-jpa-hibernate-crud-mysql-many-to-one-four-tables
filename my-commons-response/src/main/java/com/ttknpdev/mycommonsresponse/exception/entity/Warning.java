package com.ttknpdev.mycommonsresponse.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Warning {
    private Short status;
    private String code;
    private String cause; // cause (n. สาเหตุ)

}
