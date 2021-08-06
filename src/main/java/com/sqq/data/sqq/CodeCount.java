package com.sqq.data.sqq;

import lombok.Data;
import lombok.ToString;

/**
 * @author sqq
 */
@Data
@ToString
public class CodeCount {

    private String areaCode;
    private String streetCode;
    private int count;
}
