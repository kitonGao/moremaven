package com.example.enums;


import java.util.ArrayList;
import java.util.List;

/**
 * 缓存类型列表配置
 */
public enum CacheObjectEnum {
    BaseEnumValue("基本数据缓存类型"),
    ;

    CacheObjectEnum( String desc) {
        this.objType = this.name();
        this.desc = desc;
    }

    private String objType;

    private String desc;

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objectType) {
        this.objType = objectType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static List<String> allObjTypes(){
       List<String> objTypes = new ArrayList<>();
       for (CacheObjectEnum cacheObjectEnum : CacheObjectEnum.values()) {
           objTypes.add(cacheObjectEnum.getObjType());
       }
       return objTypes;
    }

}
