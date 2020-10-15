package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/14
 * Time:上午1:07
 **/
public class MentorDetailInfoDTO {

    private List<Map> data;

    public List<Map> getData() {
        return data;
    }

    public void setData(List<Map> data) {
        this.data = data;
    }

    public MentorDetailInfoDTO() {
    }

    public MentorDetailInfoDTO(List<Map> data) {
        this.data = data;
    }
}
