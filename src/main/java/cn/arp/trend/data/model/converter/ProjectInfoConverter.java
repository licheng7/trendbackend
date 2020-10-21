package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.ProjectInfoDTO;
import cn.arp.trend.data.model.response.ProjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/10
 * Time:下午3:33
 **/
@Mapper
public interface ProjectInfoConverter {

    ProjectInfoConverter INSTANCE = Mappers.getMapper(ProjectInfoConverter.class);

    @Mappings({
            @Mapping(target = "order", ignore = true)
    })
    ProjectResponse domain2dto(ProjectInfoDTO projectInfo);

    List<ProjectResponse> domain2dto(List<ProjectInfoDTO> projectInfoList);
}
