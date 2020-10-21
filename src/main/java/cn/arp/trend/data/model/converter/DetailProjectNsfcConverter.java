package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.DetailProjectNsfcInfoDTO;
import cn.arp.trend.data.model.response.DetailProjectNsfcResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/20
 * Time:下午5:04
 **/
@Mapper
public interface DetailProjectNsfcConverter {

    DetailProjectNsfcConverter INSTANCE = Mappers.getMapper(DetailProjectNsfcConverter.class);

    @Mappings({

    })
    DetailProjectNsfcResponse domain2dto(DetailProjectNsfcInfoDTO detailProjectNsfcInfo);

    List<DetailProjectNsfcResponse> domain2dto(List<DetailProjectNsfcInfoDTO> detailProjectNsfcInfoList);
}
