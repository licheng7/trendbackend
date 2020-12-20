package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午9:59
 **/
@ToString
public class ResearchFundsInfoDTO {

    private List<String> year;

    private List<MapResultDTO> detail;

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<MapResultDTO> getDetail() {
        return detail;
    }

    public void setDetail(List<MapResultDTO> detail) {
        this.detail = detail;
    }
}
