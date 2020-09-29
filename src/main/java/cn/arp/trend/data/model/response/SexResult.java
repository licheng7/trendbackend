package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午2:19
 **/
@ToString
public class SexResult implements Serializable {

    private static final long serialVersionUID = -8344339157575146784L;

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexResult() {
    }

    public SexResult(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
