package com.zyc.common.enums;

import com.zyc.common.exception.EnumAcquireException;
import org.springframework.util.ObjectUtils;

/**
 * @author zyc
 * @version 1.0
 */
public enum SampleTypeEnum {
    PICTURE(1, "picture", "图片"),
    AUDIO(2, "audio", "音频"),
    TEXT(3, "text", "文本");

    final int type;
    final String name;
    final String desc;

    SampleTypeEnum(int type, String name, String desc) {
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

    public static SampleTypeEnum getEnumById(int id) throws EnumAcquireException {
        for (SampleTypeEnum s : SampleTypeEnum.values()) {
            if (s.getType() == id) {
                return s;
            }
        }
        throw new EnumAcquireException("id = " + id + " 不是正确的类型");
    }

    public static SampleTypeEnum getEnumByName(String name) throws EnumAcquireException {
        for (SampleTypeEnum s : SampleTypeEnum.values()) {
            if (ObjectUtils.nullSafeEquals(name, s.getName())) {
                return s;
            }
        }
        throw new EnumAcquireException("name = " + name + " 不是正确的类型");
    }


}
