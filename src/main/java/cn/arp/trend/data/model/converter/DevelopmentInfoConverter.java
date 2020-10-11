package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.DevelopmentInfoDTO;
import cn.arp.trend.data.model.response.DevelopmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:下午12:40
 **/
@Mapper
public interface DevelopmentInfoConverter {

    DevelopmentInfoConverter INSTANCE = Mappers.getMapper(DevelopmentInfoConverter.class);

    @Mappings({
            @Mapping(target = "newkj", ignore = true),
            @Mapping(target = "newkx", ignore = true)
    })
    DevelopmentResponse domain2dto(DevelopmentInfoDTO developmentInfo);

    List<DevelopmentResponse> domain2dto(List<DevelopmentInfoDTO> developmentInfoList);
}
