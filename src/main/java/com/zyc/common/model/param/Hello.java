package com.zyc.common.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zyc
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hello implements Serializable {
    boolean is_public;
}
