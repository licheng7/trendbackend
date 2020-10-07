package cn.arp.trend.data.model.DTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:下午4:27
 **/
public class GoAndComeLinkDTO {

    private String date;

    private int num;

    private String country;

    private String city;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
