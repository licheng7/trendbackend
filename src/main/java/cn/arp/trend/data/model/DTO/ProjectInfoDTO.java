package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午9:27
 **/
@ToString
public class ProjectInfoDTO {

    private List<String> category;

    private List<String> year;

    private long nsfcCumulation;

    private long nsfcNew;

    private List<String> nsfcProject;

    private List<String> nsfcFunds;

    private long stdCumulation;

    private long stdNew;

    private List<String> stdProject;

    private List<String> stdFunds;

    private long xdCumulation;

    private long xdNew;

    private List<String> xdProject;

    private List<String> xdFunds;

    private List<OrderDTO> order;

    private String nsfcUpdateTime;

    private String stdUpdateTime;

    private String xdUpdateTime;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public long getNsfcCumulation() {
        return nsfcCumulation;
    }

    public void setNsfcCumulation(long nsfcCumulation) {
        this.nsfcCumulation = nsfcCumulation;
    }

    public long getNsfcNew() {
        return nsfcNew;
    }

    public void setNsfcNew(long nsfcNew) {
        this.nsfcNew = nsfcNew;
    }

    public List<String> getNsfcProject() {
        return nsfcProject;
    }

    public void setNsfcProject(List<String> nsfcProject) {
        this.nsfcProject = nsfcProject;
    }

    public List<String> getNsfcFunds() {
        return nsfcFunds;
    }

    public void setNsfcFunds(List<String> nsfcFunds) {
        this.nsfcFunds = nsfcFunds;
    }

    public long getStdCumulation() {
        return stdCumulation;
    }

    public void setStdCumulation(long stdCumulation) {
        this.stdCumulation = stdCumulation;
    }

    public long getStdNew() {
        return stdNew;
    }

    public void setStdNew(long stdNew) {
        this.stdNew = stdNew;
    }

    public List<String> getStdProject() {
        return stdProject;
    }

    public void setStdProject(List<String> stdProject) {
        this.stdProject = stdProject;
    }

    public List<String> getStdFunds() {
        return stdFunds;
    }

    public void setStdFunds(List<String> stdFunds) {
        this.stdFunds = stdFunds;
    }

    public long getXdCumulation() {
        return xdCumulation;
    }

    public void setXdCumulation(long xdCumulation) {
        this.xdCumulation = xdCumulation;
    }

    public long getXdNew() {
        return xdNew;
    }

    public void setXdNew(long xdNew) {
        this.xdNew = xdNew;
    }

    public List<String> getXdProject() {
        return xdProject;
    }

    public void setXdProject(List<String> xdProject) {
        this.xdProject = xdProject;
    }

    public List<String> getXdFunds() {
        return xdFunds;
    }

    public void setXdFunds(List<String> xdFunds) {
        this.xdFunds = xdFunds;
    }

    public List<OrderDTO> getOrder() {
        return order;
    }

    public void setOrder(List<OrderDTO> order) {
        this.order = order;
    }

    public String getNsfcUpdateTime() {
        return nsfcUpdateTime;
    }

    public void setNsfcUpdateTime(String nsfcUpdateTime) {
        this.nsfcUpdateTime = nsfcUpdateTime;
    }

    public String getStdUpdateTime() {
        return stdUpdateTime;
    }

    public void setStdUpdateTime(String stdUpdateTime) {
        this.stdUpdateTime = stdUpdateTime;
    }

    public String getXdUpdateTime() {
        return xdUpdateTime;
    }

    public void setXdUpdateTime(String xdUpdateTime) {
        this.xdUpdateTime = xdUpdateTime;
    }

    public class OrderDTO {

        private MapResultDTO<List<String>, List<Long>> nsfc;

        private MapResultDTO<List<String>, List<Long>> std;

        private MapResultDTO<List<String>, List<Long>> xd;

        public MapResultDTO<List<String>, List<Long>> getNsfc() {
            return nsfc;
        }

        public void setNsfc(MapResultDTO<List<String>, List<Long>> nsfc) {
            this.nsfc = nsfc;
        }

        public MapResultDTO<List<String>, List<Long>> getStd() {
            return std;
        }

        public void setStd(MapResultDTO<List<String>, List<Long>> std) {
            this.std = std;
        }

        public MapResultDTO<List<String>, List<Long>> getXd() {
            return xd;
        }

        public void setXd(MapResultDTO<List<String>, List<Long>> xd) {
            this.xd = xd;
        }

        public OrderDTO() {
        }

        public OrderDTO(MapResultDTO<List<String>, List<Long>> nsfc, MapResultDTO<List<String>, List<Long>> std, MapResultDTO<List<String>, List<Long>> xd) {
            this.nsfc = nsfc;
            this.std = std;
            this.xd = xd;
        }
    }
}

