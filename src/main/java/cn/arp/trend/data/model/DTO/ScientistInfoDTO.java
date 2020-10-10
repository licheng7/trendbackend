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
public class ScientistInfoDTO {

    private List<String> year;

    private List<MapResultDTO<String, List<Integer>>> domestic;

    private List<MapResultDTO<String, List<Integer>>> newWorldlist;

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<MapResultDTO<String, List<Integer>>> getDomestic() {
        return domestic;
    }

    public void setDomestic(List<MapResultDTO<String, List<Integer>>> domestic) {
        this.domestic = domestic;
    }

    public List<MapResultDTO<String, List<Integer>>> getNewWorldlist() {
        return newWorldlist;
    }

    public void setNewWorldlist(List<MapResultDTO<String, List<Integer>>> newWorldlist) {
        this.newWorldlist = newWorldlist;
    }
}
