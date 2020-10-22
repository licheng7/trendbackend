package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
@ToString
public class ZKYPCTPatentInfoDTO {

    private List<List> classify;

    private List<List<Object>> fields;

    private List<List<Object>> orderChinapatent;

    private String updateTime;

    private List<String> yearList;

    public List<List> getClassify() {
        return classify;
    }

    public void setClassify(List<List> classify) {
        this.classify = classify;
    }

    public List<List<Object>> getFields() {
        return fields;
    }

    public void setFields(List<List<Object>> fields) {
        this.fields = fields;
    }

    public List<List<Object>> getOrderChinapatent() {
        return orderChinapatent;
    }

    public void setOrderChinapatent(List<List<Object>> orderChinapatent) {
        this.orderChinapatent = orderChinapatent;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }
}
