package cn.arp.trend.data.model.response.contrast;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/25
 * Time:下午4:21
 **/
@ToString
public class ContrastPaperByFieldResponse implements Serializable {

    private static final long serialVersionUID = 2828943461724995830L;

    private List<Map<String, Object>> ageAry;

    private List<Map<String, Object>> unitAry;

    private List<Long> yearsAry;

    public List<Map<String, Object>> getAgeAry() {
        return ageAry;
    }

    public void setAgeAry(List<Map<String, Object>> ageAry) {
        this.ageAry = ageAry;
    }

    public List<Map<String, Object>> getUnitAry() {
        return unitAry;
    }

    public void setUnitAry(List<Map<String, Object>> unitAry) {
        this.unitAry = unitAry;
    }

    public List<Long> getyearsAry() {
        return yearsAry;
    }

    public void setyearsAry(List<Long> yearsAry) {
        this.yearsAry = yearsAry;
    }

    public ContrastPaperByFieldResponse() {
    }

    public ContrastPaperByFieldResponse(
            List<Map<String, Object>> ageAry,
            List<Map<String, Object>> unitAry,
            List<Long> yearsAry) {
        this.ageAry = ageAry;
        this.unitAry = unitAry;
        this.yearsAry = yearsAry;
    }
}
