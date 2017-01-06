package com.atguigu.indexview;

/**
 * 作者：尚硅谷-杨光福 on 2017/1/3 10:59
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：人的信息:姓名和拼音
 */
public class Person {
    private String name;
    private String pinyin;
    public Person(String name) {
        this.name = name;
        this.pinyin = PinYinUtils.getPinYin(name);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
