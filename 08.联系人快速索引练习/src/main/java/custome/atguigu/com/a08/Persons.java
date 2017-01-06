package custome.atguigu.com.a08;

/**
 * Created by 刘红豪 on 2017/1/5.
 */

public class Persons {
    private String name;
    private String pinyin;
    public Persons(String name) {
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
        return "Persons{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
