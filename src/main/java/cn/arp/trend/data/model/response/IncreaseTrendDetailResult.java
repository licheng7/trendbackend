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

    private Map<String, List> ZS;

    private Map<String, List> ZYJSRY;

    private Map<String, List> XZGLRY;

    private Map<String, List> GR;

    private List<Map<String, List>> typeList;

    private int totalZYJSRY;

    private int totalXZGLRY;

    private int totalGR;

    private Object allTotal;

    public Map<String, List> getZS() {
        return ZS;
    }

    public void setZS(Map<String, List> ZS) {
        this.ZS = ZS;
    }

    public Map<String, List> getZYJSRY() {
        return ZYJSRY;
    }

    public void setZYJSRY(Map<String, List> ZYJSRY) {
        this.ZYJSRY = ZYJSRY;
    }

    public Map<String, List> getXZGLRY() {
        return XZGLRY;
    }

    public void setXZGLRY(Map<String, List> XZGLRY) {
        this.XZGLRY = XZGLRY;
    }

    public Map<String, List> getGR() {
        return GR;
    }

    public void setGR(Map<String, List> GR) {
        this.GR = GR;
    }

    public List<Map<String, List>> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Map<String, List>> typeList) {
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
