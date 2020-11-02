package cn.arp.trend.data.model.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/17
 * Time:下午6:38
 **/
public class IncreaseTrendDetailResult implements Serializable {

    private static final long serialVersionUID = 3483022861349366852L;

    private Map<String, Object> ZS;

    private Map<String, Object> ZYJSRY;

    private Map<String, Object> XZGLRY;

    private Map<String, Object> GR;

    private List<Map<String, Object>> typeList;

    private int totalZYJSRY;

    private int totalXZGLRY;

    private int totalGR;

    private Object allTotal;

    public Map<String, Object> getZS() {
        return ZS;
    }

    public void setZS(Map<String, Object> ZS) {
        this.ZS = ZS;
    }

    public Map<String, Object> getZYJSRY() {
        return ZYJSRY;
    }

    public void setZYJSRY(Map<String, Object> ZYJSRY) {
        this.ZYJSRY = ZYJSRY;
    }

    public Map<String, Object> getXZGLRY() {
        return XZGLRY;
    }

    public void setXZGLRY(Map<String, Object> XZGLRY) {
        this.XZGLRY = XZGLRY;
    }

    public Map<String, Object> getGR() {
        return GR;
    }

    public void setGR(Map<String, Object> GR) {
        this.GR = GR;
    }

    public List<Map<String, Object>> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Map<String, Object>> typeList) {
        this.typeList = typeList;
    }

    public int getTotalZYJSRY() {
        return totalZYJSRY;
    }

    public void setTotalZYJSRY(int totalZYJSRY) {
        this.totalZYJSRY = totalZYJSRY;
    }

    public int getTotalXZGLRY() {
        return totalXZGLRY;
    }

    public void setTotalXZGLRY(int totalXZGLRY) {
        this.totalXZGLRY = totalXZGLRY;
    }

    public int getTotalGR() {
        return totalGR;
    }

    public void setTotalGR(int totalGR) {
        this.totalGR = totalGR;
    }

    public Object getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(Object allTotal) {
        this.allTotal = allTotal;
    }
}
