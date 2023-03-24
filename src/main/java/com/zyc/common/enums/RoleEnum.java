package com.zyc.common.enums;

/**
 * @author zyc
 * @version 1.0
 */
public enum RoleEnum {

    TAGGER("TAGGER", "标记者", 1),
    PUBLISHER("PUBLISHER", "发布者", 2);
    final String name;
    final String desc;
    final int rid;
    RoleEnum(String name, String desc, int rid) {
        this.name = name;
        this.desc = desc;
        this.rid = rid;
    }

    public int getRid() {
        return rid;
    }

    public String getName() {
        return name;
    }
}
