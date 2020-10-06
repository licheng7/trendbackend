package cn.arp.trend.data.model.DTO;

import lombok.ToString;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午12:14
 **/
@ToString
public class RankInfoDTO {

    private int goCountryNum;

    private int goCountryPeopleNum;

    private int goydylCountryNum;

    private int goydylCountryPeopleNum;

    private int comeCountryNum;

    private int comeCountryPeopleNum;

    private int comeydylCountryNum;

    private int comeydylCountryPeopleNum;

    public RankInfoDTO() {
    }

    public int getGoCountryNum() {
        return goCountryNum;
    }

    public void setGoCountryNum(int goCountryNum) {
        this.goCountryNum = goCountryNum;
    }

    public int getGoCountryPeopleNum() {
        return goCountryPeopleNum;
    }

    public void setGoCountryPeopleNum(int goCountryPeopleNum) {
        this.goCountryPeopleNum = goCountryPeopleNum;
    }

    public int getGoydylCountryNum() {
        return goydylCountryNum;
    }

    public void setGoydylCountryNum(int goydylCountryNum) {
        this.goydylCountryNum = goydylCountryNum;
    }

    public int getGoydylCountryPeopleNum() {
        return goydylCountryPeopleNum;
    }

    public void setGoydylCountryPeopleNum(int goydylCountryPeopleNum) {
        this.goydylCountryPeopleNum = goydylCountryPeopleNum;
    }

    public int getComeCountryNum() {
        return comeCountryNum;
    }

    public void setComeCountryNum(int comeCountryNum) {
        this.comeCountryNum = comeCountryNum;
    }

    public int getComeCountryPeopleNum() {
        return comeCountryPeopleNum;
    }

    public void setComeCountryPeopleNum(int comeCountryPeopleNum) {
        this.comeCountryPeopleNum = comeCountryPeopleNum;
    }

    public int getComeydylCountryNum() {
        return comeydylCountryNum;
    }

    public void setComeydylCountryNum(int comeydylCountryNum) {
        this.comeydylCountryNum = comeydylCountryNum;
    }

    public int getComeydylCountryPeopleNum() {
        return comeydylCountryPeopleNum;
    }

    public void setComeydylCountryPeopleNum(int comeydylCountryPeopleNum) {
        this.comeydylCountryPeopleNum = comeydylCountryPeopleNum;
    }
}
