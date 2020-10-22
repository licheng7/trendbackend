package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.DetailProjectKjbRelationInfoDTO;
import cn.arp.trend.data.model.response.DetailProjectKjbRelationResponse;
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
public interface DetailProjectKjbRelationConverter {

    DetailProjectKjbRelationConverter INSTANCE = Mappers.getMapper(DetailProjectKjbRelationConverter.class);

    @Mappings({

    })
    DetailProjectKjbRelationResponse domain2dto(DetailProjectKjbRelationInfoDTO detailProjectKjbRelationInfo);

    List<DetailProjectKjbRelationResponse> domain2dto(List<DetailProjectKjbRelationInfoDTO> detailProjectKjbRelationInfoList);
}
