package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.PaperSciInfoDTO;
import cn.arp.trend.data.model.response.PaperSciResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/6
 * Time:下午10:39
 **/
@Mapper
public interface PaperSciInfoConverter {

    PaperSciInfoConverter INSTANCE = Mappers.getMapper(PaperSciInfoConverter.class);

    @Mappings({

    })
    PaperSciResponse domain2dto(PaperSciInfoDTO paperSciInfo);

    List<PaperSciResponse> domain2dto(List<PaperSciInfoDTO> paperSciInfoList);
}
