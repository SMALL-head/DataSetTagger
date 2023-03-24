package com.zyc.common.enums;

import com.zyc.common.exception.EnumAcquireException;
import org.springframework.util.ObjectUtils;

/**
 * @author zyc
 * @version 1.0
 */
public enum TagTypeEnum {
    NUM_TAG(1, "num_tag", "数值标签"),
    CLASS_TAG(2, "class_tag","分类标签"),
    TEXT_TAG(3, "text_tag","文本标签");

    final int type;
    final String name;
    final String desc;

    TagTypeEnum(int type, String name, String desc) {
        this.type = type;
        this.desc = desc;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public static TagTypeEnum getEnumByName(String name) throws EnumAcquireException {
        for (TagTypeEnum s : TagTypeEnum.values()) {
            if (ObjectUtils.nullSafeEquals(name, s.getName())) {
                return s;
            }
        }
        throw new EnumAcquireException("name = " + name + " 无法转换枚举变量[TagTypeEnum]");
    }
}
