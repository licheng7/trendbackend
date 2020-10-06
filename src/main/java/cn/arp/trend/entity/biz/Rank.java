package cn.arp.trend.entity.biz;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午12:01
 **/
public class Rank {

    private String jgmc;

    private String country;

    private String ydyl;

    private int numberOfTeam;

    private String visitDate;

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYdyl() {
        return ydyl;
    }

    public void setYdyl(String ydyl) {
        this.ydyl = ydyl;
    }

    public int getNumberOfTeam() {
        return numberOfTeam;
    }

    public void setNumberOfTeam(int numberOfTeam) {
        this.numberOfTeam = numberOfTeam;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }
}
