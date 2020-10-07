package cn.arp.trend.data.model.DTO;

import lombok.ToString;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午2:00
 **/
@ToString
public class GotoCountryDTO {

    private String name;

    private Integer goNum;

    private Integer comeNum;

    public GotoCountryDTO() {
    }

    public GotoCountryDTO(String name, Integer goNum, Integer comeNum) {
        this.name = name;
        this.goNum = goNum;
        this.comeNum = comeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoNum() {
        return goNum;
    }

    public void setGoNum(Integer goNum) {
        this.goNum = goNum;
    }

    public Integer getComeNum() {
        return comeNum;
    }

    public void setComeNum(Integer comeNum) {
        this.comeNum = comeNum;
    }
}
