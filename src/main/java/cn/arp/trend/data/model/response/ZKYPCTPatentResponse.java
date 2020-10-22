package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class ZKYPCTPatentResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private List<List> classify;

    private List<List<Object>> fields;

    @JsonProperty("topAffiliations")
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

    public ZKYPCTPatentResponse() {
    }

    public ZKYPCTPatentResponse(List<List> classify, List<List<Object>> fields, List<List<Object>> orderChinapatent, String updateTime, List<String> yearList) {
        this.classify = classify;
        this.fields = fields;
        this.orderChinapatent = orderChinapatent;
        this.updateTime = updateTime;
        this.yearList = yearList;
    }
}
