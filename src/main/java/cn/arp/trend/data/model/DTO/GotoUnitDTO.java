package cn.arp.trend.data.model.DTO;

import lombok.ToString;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午1:59
 **/
@ToString
public class GotoUnitDTO {

    private String name;

    private Integer goNum;

    private Integer comeNum;

    public GotoUnitDTO() {
    }

    public GotoUnitDTO(String name, Integer goNum, Integer comeNum) {
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
