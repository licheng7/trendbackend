package cn.arp.trend.data.model.response.contrast;

import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/25
 * Time:下午4:21
 **/
@ToString
public class ContrastEducationByFieldResponse implements Serializable {

    private static final long serialVersionUID = 2828943461724995830L;

    private List<HashMap<String, Object>> numAry;

    private List<HashMap<String, Object>> bsssRatio;

    private List<HashMap<String, Object>> ssssRatio;

    private List<Long> yearsAry;

    public List<HashMap<String, Object>> getNumAry() {
        return numAry;
    }

    public void setNumAry(List<HashMap<String, Object>> numAry) {
        this.numAry = numAry;
    }

    public List<HashMap<String, Object>> getBsssRatio() {
        return bsssRatio;
    }

    public void setBsssRatio(List<HashMap<String, Object>> bsssRatio) {
        this.bsssRatio = bsssRatio;
    }

    public List<HashMap<String, Object>> getSsssRatio() {
        return ssssRatio;
    }

    public void setSsssRatio(List<HashMap<String, Object>> ssssRatio) {
        this.ssssRatio = ssssRatio;
    }

    public List<Long> getyearsAry() {
        return yearsAry;
    }

    public void setyearsAry(List<Long> yearsAry) {
        this.yearsAry = yearsAry;
    }

    public ContrastEducationByFieldResponse() {
    }

    public ContrastEducationByFieldResponse(
            List<HashMap<String, Object>> numAry,
            List<HashMap<String, Object>> unitAry,
            List<HashMap<String, Object>> cumulativeAry,
            List<Long> yearsAry) {
        this.numAry = numAry;
        this.bsssRatio = unitAry;
        this.ssssRatio = cumulativeAry;
        this.yearsAry = yearsAry;
    }
}
