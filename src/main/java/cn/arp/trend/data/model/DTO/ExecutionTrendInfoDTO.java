package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:07
 **/
@ToString
public class ExecutionTrendInfoDTO {

    private List<Map<String, Object>> detail;

    private List<Map<String, Object>> resultArray;

    public List<Map<String, Object>> getDetail() {
        return detail;
    }

    public void setDetail(List<Map<String, Object>> detail) {
        this.detail = detail;
    }

    public List<Map<String, Object>> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<Map<String, Object>> resultArray) {
        this.resultArray = resultArray;
    }

    public ExecutionTrendInfoDTO(List<Map<String, Object>> detail, List<Map<String, Object>> resultArray) {
        this.detail = detail;
        this.resultArray = resultArray;
    }

    public ExecutionTrendInfoDTO() {
    }
}
