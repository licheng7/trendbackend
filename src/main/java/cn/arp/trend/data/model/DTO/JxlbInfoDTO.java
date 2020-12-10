package cn.arp.trend.data.model.DTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/12/10
 * Time:下午4:06
 **/
public class JxlbInfoDTO {

    private String institute;

    private int zrkx;

    private int jsfm;

    private int jsjb;

    public JxlbInfoDTO() {
    }

    public JxlbInfoDTO(String institute, int zrkx, int jsfm, int jsjb) {
        this.institute = institute;
        this.zrkx = zrkx;
        this.jsfm = jsfm;
        this.jsjb = jsjb;
    }

    public JxlbInfoDTO(String institute) {
        this.institute = institute;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public int getZrkx() {
        return zrkx;
    }

    public void setZrkx(int zrkx) {
        this.zrkx = zrkx;
    }

    public int getJsfm() {
        return jsfm;
    }

    public void setJsfm(int jsfm) {
        this.jsfm = jsfm;
    }

    public int getJsjb() {
        return jsjb;
    }

    public void setJsjb(int jsjb) {
        this.jsjb = jsjb;
    }
}
