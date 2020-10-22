package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DO.DetailPaperQueryDO;
import cn.arp.trend.data.model.request.DetailPaperRequest;
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
public interface DetailPaperQueryConverter {

    DetailPaperQueryConverter INSTANCE = Mappers.getMapper(DetailPaperQueryConverter.class);

    @Mappings({

    })
    DetailPaperQueryDO domain2dto(DetailPaperRequest detailPaperRequest);

    List<DetailPaperQueryDO> domain2dto(List<DetailPaperRequest> detailPaperRequestList);
}
