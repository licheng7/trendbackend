package cn.arp.trend.data.model.response.contrast;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ContrastPatentByFieldResponse implements Serializable {

    private static final long serialVersionUID = 2828943461724995123L;

    @JsonProperty("ancient_china")
    private HashMap<String, List<Object>> ancientChina;

    @JsonProperty("ancient_pct")
    private HashMap<String, List<Object>> ancientPct;

    public void setAncientChina(HashMap<String, List<Object>> ancientChina) {
        this.ancientChina = ancientChina;
    }

    public HashMap<String, List<Object>> getAncientChina() {
        return ancientChina;
    }

    public void setAncientPct(HashMap<String, List<Object>> ancientPct) {
        this.ancientPct = ancientPct;
    }

    public HashMap<String, List<Object>> getAncientPct() {
        return ancientPct;
    }

    public ContrastPatentByFieldResponse() {
    }

    public ContrastPatentByFieldResponse(
            HashMap<String, List<Object>> ancientChina,
            HashMap<String, List<Object>> ancientPct) {
        this.ancientChina = ancientChina;
        this.ancientPct = ancientPct;
    }
}
