package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午9:59
 **/
@ToString
public class CompareAwardInfoDTO {

    private List<String> allOrgNameList;

    private List<Integer> zrkxList;

    private List<Integer> jsfmList;

    private List<Integer> jsjbList;

    List<Map<String, Object>> qsjcList;

    List<Map<String, Object>> hlhlList;

    public List<String> getAllOrgNameList() {
        return allOrgNameList;
    }

    public void setAllOrgNameList(List<String> allOrgNameList) {
        this.allOrgNameList = allOrgNameList;
    }

    public List<Integer> getZrkxList() {
        return zrkxList;
    }

    public void setZrkxList(List<Integer> zrkxList) {
        this.zrkxList = zrkxList;
    }

    public List<Integer> getJsfmList() {
        return jsfmList;
    }

    public void setJsfmList(List<Integer> jsfmList) {
        this.jsfmList = jsfmList;
    }

    public List<Integer> getJsjbList() {
        return jsjbList;
    }

    public void setJsjbList(List<Integer> jsjbList) {
        this.jsjbList = jsjbList;
    }

    public List<Map<String, Object>> getQsjcList() {
        return qsjcList;
    }

    public void setQsjcList(List<Map<String, Object>> qsjcList) {
        this.qsjcList = qsjcList;
    }

    public List<Map<String, Object>> getHlhlList() {
        return hlhlList;
    }

    public void setHlhlList(List<Map<String, Object>> hlhlList) {
        this.hlhlList = hlhlList;
    }

    public CompareAwardInfoDTO() {
    }

    public CompareAwardInfoDTO(List<String> allOrgNameList, List<Integer> zrkxList, List<Integer> jsfmList, List<Integer> jsjbList, List<Map<String, Object>> qsjcList, List<Map<String, Object>> hlhlList) {
        this.allOrgNameList = allOrgNameList;
        this.zrkxList = zrkxList;
        this.jsfmList = jsfmList;
        this.jsjbList = jsjbList;
        this.qsjcList = qsjcList;
        this.hlhlList = hlhlList;
    }
}
