package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.*;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/20
 * Time:上午11:22
 **/
public interface DetailProjectService {

    DetailProjectNsfcInfoDTO nsfcQuery(ProjectQueryDO query);

    DetailProjectKjbInfoDTO kjbQuery(ProjectQueryDO query);

    DetailProjectXdInfoDTO xdQuery(ProjectQueryDO query);

    DetailProjectIncreaseInfoDTO increaseQuery(ProjectQueryDO query);

    DetailProjectNsfcRelationInfoDTO nsfcRelationQuery(ProjectQueryDO query);

    DetailProjectKjbRelationInfoDTO kjbRelationQuery(ProjectQueryDO query);

    DetailProjectXdRelationInfoDTO xdRelationQuery(ProjectQueryDO query);
}
