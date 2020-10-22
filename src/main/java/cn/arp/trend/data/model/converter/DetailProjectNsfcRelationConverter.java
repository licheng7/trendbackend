package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.DetailProjectNsfcRelationInfoDTO;
import cn.arp.trend.data.model.response.DetailProjectNsfcRelationResponse;
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
public interface DetailProjectNsfcRelationConverter {

    DetailProjectNsfcRelationConverter INSTANCE = Mappers.getMapper(DetailProjectNsfcRelationConverter.class);

    @Mappings({

    })
    DetailProjectNsfcRelationResponse domain2dto(DetailProjectNsfcRelationInfoDTO detailProjectNsfcRelationInfo);

    List<DetailProjectNsfcRelationResponse> domain2dto(List<DetailProjectNsfcRelationInfoDTO> detailProjectNsfcRelationInfoList);
}
