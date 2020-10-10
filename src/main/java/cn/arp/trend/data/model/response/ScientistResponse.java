package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/10
 * Time:下午5:39
 **/
@ToString
public class ScientistResponse implements Serializable {

    private static final long serialVersionUID = -4031402579683688352L;

    private List<String> year;

    private List<MapResult<String, List<Integer>>> domestic;

    private List<MapResult<String, List<Integer>>> newWorldlist;

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<MapResult<String, List<Integer>>> getDomestic() {
        return domestic;
    }

    public void setDomestic(List<MapResult<String, List<Integer>>> domestic) {
        this.domestic = domestic;
    }

    public List<MapResult<String, List<Integer>>> getNewWorldlist() {
        return newWorldlist;
    }

    public void setNewWorldlist(List<MapResult<String, List<Integer>>> newWorldlist) {
        this.newWorldlist = newWorldlist;
    }

    public ScientistResponse() {
    }

    public ScientistResponse(List<String> year, List<MapResult<String, List<Integer>>> domestic, List<MapResult<String, List<Integer>>> newWorldlist) {
        this.year = year;
        this.domestic = domestic;
        this.newWorldlist = newWorldlist;
    }
}
