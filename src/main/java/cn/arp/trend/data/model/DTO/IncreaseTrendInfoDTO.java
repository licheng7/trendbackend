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

        private Map<String, Object> ZS;

        private Map<String, Object> ZYJSRY;

        private Map<String, Object> XZGLRY;

        private Map<String, Object> GR;

        private List<Map<String, Object>> typeList;

        private int totalZYJSRY;

        private int totalXZGLRY;

        private int totalGR;

        private Object allTotal;

        public Map<String, Object> getZS() {
            return ZS;
        }

        public void setZS(Map<String, Object> ZS) {
            this.ZS = ZS;
        }

        public Map<String, Object> getZYJSRY() {
            return ZYJSRY;
        }

        public void setZYJSRY(Map<String, Object> ZYJSRY) {
            this.ZYJSRY = ZYJSRY;
        }

        public Map<String, Object> getXZGLRY() {
            return XZGLRY;
        }

        public void setXZGLRY(Map<String, Object> XZGLRY) {
            this.XZGLRY = XZGLRY;
        }

        public Map<String, Object> getGR() {
            return GR;
        }

        public void setGR(Map<String, Object> GR) {
            this.GR = GR;
        }

        public List<Map<String, Object>> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<Map<String, Object>> typeList) {
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
