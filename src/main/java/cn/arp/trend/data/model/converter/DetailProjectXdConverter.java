package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.DetailProjectXdInfoDTO;
import cn.arp.trend.data.model.response.DetailProjectXdResponse;
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
public interface DetailProjectXdConverter {

    DetailProjectXdConverter INSTANCE = Mappers.getMapper(DetailProjectXdConverter.class);

    @Mappings({

    })
    DetailProjectXdResponse domain2dto(DetailProjectXdInfoDTO detailProjectXdInfo);

    List<DetailProjectXdResponse> domain2dto(List<DetailProjectXdInfoDTO> detailProjectXdInfoList);
}
