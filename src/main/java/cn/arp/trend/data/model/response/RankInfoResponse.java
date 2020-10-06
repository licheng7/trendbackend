package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午1:01
 **/
@ToString
public class RankInfoResponse implements Serializable {

    private static final long serialVersionUID = -1242441389797329060L;

    private int goCountryNum;

    private int goCountryPeopleNum;

    private int goydylCountryNum;

    private int goydylCountryPeopleNum;

    private int comeCountryNum;

    private int comeCountryPeopleNum;

    private int comeydylCountryNum;

    private int comeydylCountryPeopleNum;

    public RankInfoResponse(int goCountryNum, int goCountryPeopleNum, int goydylCountryNum, int goydylCountryPeopleNum, int comeCountryNum, int comeCountryPeopleNum, int comeydylCountryNum, int comeydylCountryPeopleNum) {
        this.goCountryNum = goCountryNum;
        this.goCountryPeopleNum = goCountryPeopleNum;
        this.goydylCountryNum = goydylCountryNum;
        this.goydylCountryPeopleNum = goydylCountryPeopleNum;
        this.comeCountryNum = comeCountryNum;
        this.comeCountryPeopleNum = comeCountryPeopleNum;
        this.comeydylCountryNum = comeydylCountryNum;
        this.comeydylCountryPeopleNum = comeydylCountryPeopleNum;
    }

    public RankInfoResponse() {
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
