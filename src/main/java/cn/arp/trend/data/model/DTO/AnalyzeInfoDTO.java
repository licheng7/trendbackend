package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/6
 * Time:下午10:31
 **/
@ToString
public class AnalyzeInfoDTO {

    List<String> fieldMap;

    List<AnalyzeAllResultDTO> all;

    public AnalyzeInfoDTO() {
    }

    public AnalyzeInfoDTO(List<String> fieldMap, List<AnalyzeAllResultDTO> all) {
        this.fieldMap = fieldMap;
        this.all = all;
    }

    public List<String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(List<String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public List<AnalyzeAllResultDTO> getAll() {
        return all;
    }

    public void setAll(List<AnalyzeAllResultDTO> all) {
        this.all = all;
    }
}
