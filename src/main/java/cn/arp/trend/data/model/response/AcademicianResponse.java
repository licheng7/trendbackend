package cn.arp.trend.data.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/25
 * Time:下午4:21
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AcademicianResponse implements Serializable {

    private static final long serialVersionUID = 2838943461724995830L;

    private FieldsResult fields;

    private List<String> institution;
}
