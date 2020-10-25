package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.DetailAwardDetailInfoDTO;
import cn.arp.trend.data.model.response.AwardDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/25
 * Time:下午8:20
 **/
@Mapper
public interface DetailAwardDetailInfoConverter {

    DetailAwardDetailInfoConverter INSTANCE = Mappers.getMapper(DetailAwardDetailInfoConverter.class);

    @Mappings({

    })
    AwardDetailResponse domain2dto(DetailAwardDetailInfoDTO detailAwardDetailInfo);

    List<AwardDetailResponse> domain2dto(List<DetailAwardDetailInfoDTO> detailAwardDetailInfoList);
}
