package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.GoAndComeLinkDTO;
import cn.arp.trend.entity.biz.GoAndComeLink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:下午4:36
 **/
@Mapper
public interface GoAndComeLinkConverter {

    GoAndComeLinkConverter INSTANCE = Mappers.getMapper(GoAndComeLinkConverter.class);

    @Mappings({
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "num", target = "num"),
            @Mapping(source = "country", target = "country"),
            @Mapping(source = "city", target = "city")
    })
    GoAndComeLinkDTO domain2dto(GoAndComeLink goAndComeLink);

    List<GoAndComeLinkDTO> domain2dto(List<GoAndComeLink> goAndComeLinkList);
}
