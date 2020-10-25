package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.YoungProjectInfoDTO;
import cn.arp.trend.data.model.response.YoungProjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/25
 * Time:上午1:11
 **/
@Mapper
public interface YoungProjectInfoConverter {

    YoungProjectInfoConverter INSTANCE = Mappers.getMapper(YoungProjectInfoConverter.class);

    @Mappings({

    })
    YoungProjectResponse domain2dto(YoungProjectInfoDTO projectInfo);

    List<YoungProjectResponse> domain2dto(List<YoungProjectInfoDTO> projectInfoList);
}
