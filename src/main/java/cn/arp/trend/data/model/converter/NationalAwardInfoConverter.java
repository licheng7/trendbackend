package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.NationalAwardInfoDTO;
import cn.arp.trend.data.model.response.NationalAwardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午12:45
 **/
@Mapper
public interface NationalAwardInfoConverter {

    NationalAwardInfoConverter INSTANCE = Mappers.getMapper(NationalAwardInfoConverter.class);

    @Mappings({

    })
    NationalAwardResponse domain2dto(NationalAwardInfoDTO nationalAwardInfo);

    List<NationalAwardResponse> domain2dto(List<NationalAwardInfoDTO> nationalAwardInfo);
}
