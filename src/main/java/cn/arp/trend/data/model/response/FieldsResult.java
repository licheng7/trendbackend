package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午12:52
 **/
@ToString
public class FieldsResult implements Serializable {

    private static final long serialVersionUID = 7320752034568908870L;

    List<String> fieldsZKY;

    List<String> fieldsGCY;

    public List<String> getFieldsZKY() {
        return fieldsZKY;
    }

    public void setFieldsZKY(List<String> fieldsZKY) {
        this.fieldsZKY = fieldsZKY;
    }

    public List<String> getFieldsGCY() {
        return fieldsGCY;
    }

    public void setFieldsGCY(List<String> fieldsGCY) {
        this.fieldsGCY = fieldsGCY;
    }

    public FieldsResult() {}

    public FieldsResult(List<String> fieldsZKY, List<String> fieldsGCY) {
        this.fieldsZKY = fieldsZKY;
        this.fieldsGCY = fieldsGCY;
    }
}
