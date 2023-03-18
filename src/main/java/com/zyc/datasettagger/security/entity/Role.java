package com.zyc.datasettagger.security.entity;

/**
 * @author zyc
 * @version 1.0
 */
public class Role {
    Integer id;
    String name;
    String nameZh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    @Override
    public String toString() {
        return "Role{" +
            "name='" + name + '\'' +
            '}';
    }
}
