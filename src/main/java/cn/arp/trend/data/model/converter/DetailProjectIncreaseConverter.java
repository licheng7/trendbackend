package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.DetailProjectIncreaseInfoDTO;
import cn.arp.trend.data.model.response.DetailProjectIncreaseResponse;
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
public interface DetailProjectIncreaseConverter {

    DetailProjectIncreaseConverter INSTANCE = Mappers.getMapper(DetailProjectIncreaseConverter.class);

    @Mappings({

    })
    DetailProjectIncreaseResponse domain2dto(DetailProjectIncreaseInfoDTO detailProjectIncreaseInfo);

    List<DetailProjectIncreaseResponse> domain2dto(List<DetailProjectIncreaseInfoDTO> detailProjectIncreaseInfoList);
}
