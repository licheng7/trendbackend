package cn.arp.trend.data.model.DTO;

import lombok.ToString;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午6:40
 **/
@ToString
public class MapResultDTO<K, V> {

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

    public MapResultDTO() {
    }

    public MapResultDTO(K name, V value) {
        this.name = name;
        this.value = value;
    }
}
