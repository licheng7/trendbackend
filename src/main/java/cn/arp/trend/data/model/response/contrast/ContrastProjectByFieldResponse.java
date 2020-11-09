package cn.arp.trend.data.model.response.contrast;

import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/25
 * Time:下午4:21
 **/
@ToString
public class ContrastProjectByFieldResponse implements Serializable {

    private static final long serialVersionUID = 2828943461724995832L;

    private List<HashMap<String, Object>> projectData;

    private List<Integer> yearsAry;

    public List<HashMap<String, Object>> getProjectData() {
        return projectData;
    }

    public void setProjectData(List<HashMap<String, Object>> projectData) {
        this.projectData = projectData;
    }

    public List<Integer> getYearsAry() {
        return yearsAry;
    }

    public void setYearsAry(List<Integer> yearsAry) {
        this.yearsAry = yearsAry;
    }

    public ContrastProjectByFieldResponse() {
    }

    public ContrastProjectByFieldResponse(
            List<HashMap<String, Object>> projectData,
            List<Integer> yearsAry) {
        this.projectData = projectData;
        this.yearsAry = yearsAry;
    }
}
