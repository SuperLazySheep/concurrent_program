package com.sqq.data.sqq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 * @author sqq
 * @Date 2021/1/22
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@ToString
public class Organization {

    /**
     * 机构
     */
    private String area;

    private String street;

    private String organizationType;

    private String name;

    private String tel;

    private String address;

//    private Double longitude;
    private String longitudeGD;

//    private Double latitude;
    private String latitudeGD;



}
