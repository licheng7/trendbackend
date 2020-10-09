package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.MapResultDTO;
import cn.arp.trend.data.model.response.MapResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午7:01
 **/
@Mapper
public interface MapResultConverter {

    MapResultConverter INSTANCE = Mappers.getMapper(MapResultConverter.class);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "value", target = "value")
    })
    MapResult domain2dto(MapResultDTO mapResult);

    List<MapResult> domain2dto(List<MapResultDTO> mapResultList);
}
