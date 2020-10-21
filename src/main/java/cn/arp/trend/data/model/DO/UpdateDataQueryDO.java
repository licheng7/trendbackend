package cn.arp.trend.data.model.DO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午9:23
 **/
public class UpdateDataQueryDO {

    private String classify;

    private String name;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdateDataQueryDO(String classify, String name) {
        this.classify = classify;
        this.name = name;
    }

    public UpdateDataQueryDO() {
    }
}
