package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午7:00
 **/
@ToString
public class MapResult<K, V> implements Serializable {

    private static final long serialVersionUID = -3051842475314708841L;

    private K name;

    private V value;

    public K getName() {
        return name;
    }

    public void setName(K name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public MapResult() {
    }

    public MapResult(K name, V value) {
        this.name = name;
        this.value = value;
    }
}
