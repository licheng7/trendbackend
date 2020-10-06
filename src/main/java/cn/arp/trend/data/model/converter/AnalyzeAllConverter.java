package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.AnalyzeAllResultDTO;
import cn.arp.trend.data.model.response.AnalyzeAllResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/6
 * Time:下午10:39
 **/
@Mapper
public interface AnalyzeAllConverter {

    AnalyzeAllConverter INSTANCE = Mappers.getMapper(AnalyzeAllConverter.class);

    @Mappings({
            @Mapping(source = "index", target = "index"),
            @Mapping(source = "faf", target = "faf"),
            @Mapping(source = "mentor", target = "mentor"),
            @Mapping(source = "concurrent", target = "concurrent"),
            @Mapping(source = "talent100", target = "talent100"),
            @Mapping(source = "patent", target = "patent"),
            @Mapping(source = "paper", target = "paper"),
            @Mapping(source = "projectTotal", target = "projectTotal"),
            @Mapping(source = "projectNsfc", target = "projectNsfc"),
            @Mapping(source = "projectKjb", target = "projectKjb"),
            @Mapping(source = "projectXd", target = "projectXd"),
            @Mapping(source = "finance", target = "finance"),
            @Mapping(source = "acadenician", target = "acadenician"),
            @Mapping(source = "cas", target = "cas"),
            @Mapping(source = "cae", target = "cae"),
            @Mapping(source = "award", target = "award")
    })
    AnalyzeAllResult domain2dto(AnalyzeAllResultDTO analyzeAllResult);

    List<AnalyzeAllResult> domain2dto(List<AnalyzeAllResultDTO> analyzeAllResults);
}
