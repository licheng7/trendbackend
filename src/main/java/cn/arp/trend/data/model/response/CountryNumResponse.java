package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
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

    private List<List<Map<String, Object>>> countryNumList;

    public CountryNumResponse() {
    }

    public CountryNumResponse(List<List<Map<String, Object>>> countryNumList) {
        this.countryNumList = countryNumList;
    }

    public List<List<Map<String, Object>>> getCountryNumList() {
        return countryNumList;
    }

    public void setCountryNumList(List<List<Map<String, Object>>> countryNumList) {
        this.countryNumList = countryNumList;
    }
}
