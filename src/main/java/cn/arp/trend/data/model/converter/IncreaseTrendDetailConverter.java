package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.IncreaseTrendInfoDTO;
import cn.arp.trend.data.model.response.IncreaseTrendDetailResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/17
 * Time:下午6:41
 **/
@Mapper
public interface IncreaseTrendDetailConverter {

    IncreaseTrendDetailConverter INSTANCE = Mappers.getMapper(IncreaseTrendDetailConverter.class);

    @Mappings({

    })
    IncreaseTrendDetailResult domain2dto(IncreaseTrendInfoDTO.IncreaseTrendDetailResultDTO result);

    List<IncreaseTrendDetailResult> domain2dto(List<IncreaseTrendInfoDTO.IncreaseTrendDetailResultDTO> result);
}
