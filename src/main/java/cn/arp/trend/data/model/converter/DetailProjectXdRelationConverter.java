package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.DetailProjectXdRelationInfoDTO;
import cn.arp.trend.data.model.response.DetailProjectXdRelationResponse;
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
public interface DetailProjectXdRelationConverter {

    DetailProjectXdRelationConverter INSTANCE = Mappers.getMapper(DetailProjectXdRelationConverter.class);

    @Mappings({

    })
    DetailProjectXdRelationResponse domain2dto(DetailProjectXdRelationInfoDTO detailProjectXdRelationInfo);

    List<DetailProjectXdRelationResponse> domain2dto(List<DetailProjectXdRelationInfoDTO> detailProjectXdRelationInfoList);
}
