package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.OverviewInfoDTO;
import cn.arp.trend.data.model.response.OverviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/19
 * Time:上午1:05
 **/
@Mapper
public interface OverviewConverter {

    OverviewConverter INSTANCE = Mappers.getMapper(OverviewConverter.class);

    @Mappings({

    })
    OverviewResponse domain2dto(OverviewInfoDTO overviewInfo);

    List<OverviewResponse> domain2dto(List<OverviewInfoDTO> overviewInfoList);
}
