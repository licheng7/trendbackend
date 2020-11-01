package cn.arp.trend.data.model.DTO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:下午4:10
 **/
public class GoToByTimeObjDTO {

    private List<GoAndComeLinkDTO> to;

    private List<GoAndComeLinkDTO> come;

    public List<GoAndComeLinkDTO> getTo() {
        return to;
    }

    public void setTo(List<GoAndComeLinkDTO> to) {
        this.to = to;
    }

    public List<GoAndComeLinkDTO> getCome() {
        return come;
    }

    public void setCome(List<GoAndComeLinkDTO> come) {
        this.come = come;
    }

    public GoToByTimeObjDTO() {
    }

    public GoToByTimeObjDTO(List<GoAndComeLinkDTO> to, List<GoAndComeLinkDTO> come) {
        this.to = to;
        this.come = come;
    }
}
