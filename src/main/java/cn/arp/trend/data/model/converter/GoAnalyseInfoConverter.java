package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.GoAnalyseInfoDTO;
import cn.arp.trend.data.model.response.GoAnalyseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午6:02
 **/
@Mapper
public interface GoAnalyseInfoConverter {

    GoAnalyseInfoConverter INSTANCE = Mappers.getMapper(GoAnalyseInfoConverter.class);

    @Mappings({

    })
    GoAnalyseResponse domain2dto(GoAnalyseInfoDTO goAnalyseInfo);

    List<GoAnalyseResponse> domain2dto(List<GoAnalyseInfoDTO> goAnalyseInfoList);
}
