package cn.arp.trend.data.model.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/27
 * Time:下午11:45
 **/
@Data
@ToString
public class MenuResult implements Serializable {

    private static final long serialVersionUID = -2977528911356160742L;

    private String name;

    private String url;

    public MenuResult(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
