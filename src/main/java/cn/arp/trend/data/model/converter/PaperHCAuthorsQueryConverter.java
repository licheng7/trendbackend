package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DO.PaperHCAuthorsQueryDO;
import cn.arp.trend.data.model.request.PaperHCAuthorsRequest;
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
public interface PaperHCAuthorsQueryConverter {

    PaperHCAuthorsQueryConverter INSTANCE = Mappers.getMapper(PaperHCAuthorsQueryConverter.class);

    @Mappings({

    })
    PaperHCAuthorsQueryDO domain2dto(PaperHCAuthorsRequest detailPaperRequest);

    List<PaperHCAuthorsQueryDO> domain2dto(List<PaperHCAuthorsRequest> detailPaperRequestList);
}
