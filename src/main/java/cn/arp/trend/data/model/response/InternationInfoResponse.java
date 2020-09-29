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
 * Date:2020/9/29
 * Time:上午1:24
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InternationInfoResponse implements Serializable {

    private static final long serialVersionUID = -2726800844033725680L;

    private List<String> country;

    private List<String> nationality;

    private List<String> form;

    private List<Integer> ageList;

    private List<SexResult> sexList;
}
