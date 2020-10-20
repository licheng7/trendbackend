package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.DetailProjectKjbInfoDTO;
import cn.arp.trend.data.model.response.DetailProjectKjbResponse;
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
public interface DetailProjectKjbConverter {

    DetailProjectKjbConverter INSTANCE = Mappers.getMapper(DetailProjectKjbConverter.class);

    @Mappings({

    })
    DetailProjectKjbResponse domain2dto(DetailProjectKjbInfoDTO detailProjectKjbInfo);

    List<DetailProjectKjbResponse> domain2dto(List<DetailProjectKjbInfoDTO> detailProjectKjbInfoList);
}
