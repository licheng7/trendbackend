package cn.arp.trend.data.model.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午9:02
 **/
public class ExecutionTrendResponse implements Serializable {

    private static final long serialVersionUID = -6880710850280864142L;

    private List<Map<String, Object>> detail;

    private List<Map<String, Object>> resultArray;

    public ExecutionTrendResponse() {
    }

    public ExecutionTrendResponse(List<Map<String, Object>> detail, List<Map<String, Object>> resultArray) {
        this.detail = detail;
        this.resultArray = resultArray;
    }

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
}
