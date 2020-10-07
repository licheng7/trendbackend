package cn.arp.trend.data.model.DTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:下午4:10
 **/
public class GoToByTimeObjDTO {

    private String time;

    private GoAndComeLinkDTO to;

    private GoAndComeLinkDTO come;

    public GoToByTimeObjDTO(String time, GoAndComeLinkDTO to, GoAndComeLinkDTO come) {
        this.time = time;
        this.to = to;
        this.come = come;
    }

    public GoToByTimeObjDTO() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public GoAndComeLinkDTO getTo() {
        return to;
    }

    public void setTo(GoAndComeLinkDTO to) {
        this.to = to;
    }

    public GoAndComeLinkDTO getCome() {
        return come;
    }

    public void setCome(GoAndComeLinkDTO come) {
        this.come = come;
    }
}
