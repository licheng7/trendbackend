package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:07
 **/
@ToString
public class IncreaseTrendInfoDTO {

    private List<String> year;

    private IncreaseTrendDetailResultDTO detail;

    private String updateTime;

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public IncreaseTrendDetailResultDTO getDetail() {
        return detail;
    }

    public void setDetail(IncreaseTrendDetailResultDTO detail) {
        this.detail = detail;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public IncreaseTrendInfoDTO() {
    }

    public IncreaseTrendInfoDTO(List<String> year, IncreaseTrendDetailResultDTO detail, String updateTime) {
        this.year = year;
        this.detail = detail;
        this.updateTime = updateTime;
    }

    public class IncreaseTrendDetailResultDTO {

        private Map<String, List> ZS;

        private Map<String, List> ZYJSRY;

        private Map<String, List> XZGLRY;

        private Map<String, List> GR;

        private List<Map<String, List>> typeList;

        private int totalZYJSRY;

        private int totalXZGLRY;

        private int totalGR;

        private Object allTotal;

        public Map<String, List> getZS() {
            return ZS;
        }

        public void setZS(Map<String, List> ZS) {
            this.ZS = ZS;
        }

        public Map<String, List> getZYJSRY() {
            return ZYJSRY;
        }

        public void setZYJSRY(Map<String, List> ZYJSRY) {
            this.ZYJSRY = ZYJSRY;
        }

        public Map<String, List> getXZGLRY() {
            return XZGLRY;
        }

        public void setXZGLRY(Map<String, List> XZGLRY) {
            this.XZGLRY = XZGLRY;
        }

        public Map<String, List> getGR() {
            return GR;
        }

        public void setGR(Map<String, List> GR) {
            this.GR = GR;
        }

        public List<Map<String, List>> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<Map<String, List>> typeList) {
            this.typeList = typeList;
        }

        public int getTotalZYJSRY() {
            return totalZYJSRY;
        }

        public void setTotalZYJSRY(int totalZYJSRY) {
            this.totalZYJSRY = totalZYJSRY;
        }

        public int getTotalXZGLRY() {
            return totalXZGLRY;
        }

        public void setTotalXZGLRY(int totalXZGLRY) {
            this.totalXZGLRY = totalXZGLRY;
        }

        public int getTotalGR() {
            return totalGR;
        }

        public void setTotalGR(int totalGR) {
            this.totalGR = totalGR;
        }

        public Object getAllTotal() {
            return allTotal;
        }

        public void setAllTotal(Object allTotal) {
            this.allTotal = allTotal;
        }
    }
}
