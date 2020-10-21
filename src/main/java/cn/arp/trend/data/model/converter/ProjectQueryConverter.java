package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.request.ProjectQueryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午9:21
 **/
@Mapper
public interface ProjectQueryConverter {

    ProjectQueryConverter INSTANCE = Mappers.getMapper(ProjectQueryConverter.class);

    @Mappings({

    })
    ProjectQueryDO domain2dto(ProjectQueryRequest request);

    List<ProjectQueryDO> domain2dto(List<ProjectQueryRequest> requestList);
}
