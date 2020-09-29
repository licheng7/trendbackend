package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午1:24
 **/
@ToString
public class InternationInfoResponse implements Serializable {

    private static final long serialVersionUID = -2726800844033725680L;

    private List<String> country;

    private List<String> nationality;

    private List<String> form;

    private List<Integer> ageList;

    private List<SexResult> sexList;

    public InternationInfoResponse() {}

    public InternationInfoResponse(List<String> country, List<String> nationality, List<String> form, List<Integer> ageList, List<SexResult> sexList) {
        this.country = country;
        this.nationality = nationality;
        this.form = form;
        this.ageList = ageList;
        this.sexList = sexList;
    }
}
