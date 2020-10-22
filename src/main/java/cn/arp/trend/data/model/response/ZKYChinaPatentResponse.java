package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class ZKYChinaPatentResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private List<Map<String, Object>> pie;

    private List<Map<String, Object>> graph;

    private List<Map<String, Object>> arpGraph;

    @JsonProperty("histogram")
    private List<List> histogramList;

    @JsonProperty("newYearList")
    private List<String> yearList;

    @JsonProperty("result_array")
    private List<List> resultArray;

    public List<Map<String, Object>> getPie() {
        return pie;
    }

    public void setPie(List<Map<String, Object>> pie) {
        this.pie = pie;
    }

    public List<Map<String, Object>> getGraph() {
        return graph;
    }

    public void setGraph(List<Map<String, Object>> graph) {
        this.graph = graph;
    }

    public List<Map<String, Object>> getArpGraph() {
        return arpGraph;
    }

    public void setArpGraph(List<Map<String, Object>> arpGraph) {
        this.arpGraph = arpGraph;
    }

    public List<List> getHistogramList() {
        return histogramList;
    }

    public void setHistogramList(List<List> histogramList) {
        this.histogramList = histogramList;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<List> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<List> resultArray) {
        this.resultArray = resultArray;
    }

    public ZKYChinaPatentResponse(List<Map<String, Object>> pie, List<Map<String, Object>> graph, List<Map<String, Object>> arpGraph, List<List> histogramList, List<String> yearList, List<List> resultArray) {
        this.pie = pie;
        this.graph = graph;
        this.arpGraph = arpGraph;
        this.histogramList = histogramList;
        this.yearList = yearList;
        this.resultArray = resultArray;
    }

    public ZKYChinaPatentResponse() {
    }
}
