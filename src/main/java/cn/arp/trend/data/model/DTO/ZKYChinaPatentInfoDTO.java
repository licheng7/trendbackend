package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
@ToString
public class ZKYChinaPatentInfoDTO {

    private List<Map<String, Object>> pie;

    private List<Map<String, Object>> graph;

    private List<Map<String, Object>> arpGraph;

    private List<List> histogramList;

    private List<String> yearList;

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
}
