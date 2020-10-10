package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/10
 * Time:下午3:29
 **/
@ToString
public class ProjectOrderResult implements Serializable {

    private static final long serialVersionUID = -5255516084419352562L;

    private MapResult<List<String>, List<Long>> nsfc;

    private MapResult<List<String>, List<Long>> std;

    private MapResult<List<String>, List<Long>> xd;

    public MapResult<List<String>, List<Long>> getNsfc() {
        return nsfc;
    }

    public void setNsfc(MapResult<List<String>, List<Long>> nsfc) {
        this.nsfc = nsfc;
    }

    public MapResult<List<String>, List<Long>> getStd() {
        return std;
    }

    public void setStd(MapResult<List<String>, List<Long>> std) {
        this.std = std;
    }

    public MapResult<List<String>, List<Long>> getXd() {
        return xd;
    }

    public void setXd(MapResult<List<String>, List<Long>> xd) {
        this.xd = xd;
    }

    public ProjectOrderResult() {
    }

    public ProjectOrderResult(MapResult<List<String>, List<Long>> nsfc, MapResult<List<String>, List<Long>> std, MapResult<List<String>, List<Long>> xd) {
        this.nsfc = nsfc;
        this.std = std;
        this.xd = xd;
    }
}
