package cn.arp.trend.data.model.DTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午3:44
 **/
public class TempObjDTO {

    private String city;

    private String country;

    private int num;

    public TempObjDTO() {
    }

    public TempObjDTO(String city, String country, int num) {
        this.city = city;
        this.country = country;
        this.num = num;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
