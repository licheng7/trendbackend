package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/27
 * Time:下午11:41
 **/
@ToString
public class MenuResponse implements Serializable {

    private static final long serialVersionUID = -9179991408140527233L;

    /**
     * 不知道用途，老代码里有
     */
    int code;

    List<MenuResult> menuList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<MenuResult> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuResult> menuList) {
        this.menuList = menuList;
    }

    public MenuResponse() {}

    public MenuResponse(int code, List<MenuResult> menuList) {
        this.code = code;
        this.menuList = menuList;
    }
}
