package com.zsc.api.dingding;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangSuchao
 * @create 2019/4/14
 * @since 1.0.0
 */
@Data
public class DingUser implements Serializable {

    private String userid;

    private String name;

    private List<Long> department;

    private String position;

    private String mobile;

    private String tel;

    private String workPlace;

    private String remark;

    private String email;

    private String orgEmail;

    private String jobnumber;

    private Boolean isHide;

    private Boolean isSenior;

    private Map<String, String> extattr;

}
