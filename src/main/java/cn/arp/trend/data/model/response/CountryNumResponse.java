package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:上午12:02
 **/
@ToString
public class CountryNumResponse implements Serializable {

    private static final long serialVersionUID = 5527033241423427788L;

    private Map<String, Map<String, Integer>> countryNumList;

    public CountryNumResponse() {
    }

    public CountryNumResponse(Map<String, Map<String, Integer>> countryNumList) {
        this.countryNumList = countryNumList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Map<String, Map<String, Integer>> getCountryNumList() {
        return countryNumList;
    }

    public void setCountryNumList(Map<String, Map<String, Integer>> countryNumList) {
        this.countryNumList = countryNumList;
    }
}
