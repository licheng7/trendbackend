package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.ComeAnalyseInfoDTO;
import cn.arp.trend.data.model.response.ComeAnalyseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午8:52
 **/
@Mapper
public interface ComeAnalyseInfoConverter {

    ComeAnalyseInfoConverter INSTANCE = Mappers.getMapper(ComeAnalyseInfoConverter.class);

    @Mappings({

    })
    ComeAnalyseResponse domain2dto(ComeAnalyseInfoDTO comeAnalyseInfo);

    List<ComeAnalyseResponse> domain2dto(List<ComeAnalyseInfoDTO> comeAnalyseInfoList);
}
