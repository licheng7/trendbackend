package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/27
 * Time:下午11:41
 **/
@ToString
public class ColormapResponse implements Serializable {

    private static final long serialVersionUID = -9179991408140527233L;

    private List<String> field;

    @JsonProperty("field_color")
    private List<String> fieldColor;

    public List<String> getField() {
        return field;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    public List<String> getFieldColor() {
        return fieldColor;
    }

    public void setFieldColor(List<String> fieldColor) {
        this.fieldColor = fieldColor;
    }

    public ColormapResponse() {
    }

    public ColormapResponse(List<String> field, List<String> fieldColor) {
        this.field = field;
        this.fieldColor = fieldColor;
    }
}
