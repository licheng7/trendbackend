package cn.arp.trend.data.model.response.contrast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/25
 * Time:下午4:21
 **/
@ToString
public class ContrastTheWorkerByFieldResponse implements Serializable {

    private static final long serialVersionUID = 2828943461724995123L;

    private HashMap<String, ArrayList<Object>> tendency;

    private HashMap<String, ArrayList<Object>> age;

    private ArrayList<HashMap<String, Object>> title;

    private ArrayList<HashMap<String, Object>> educationAry;

    public void setTendency(HashMap<String, ArrayList<Object>> tendency) {
        this.tendency = tendency;
    }

    public HashMap<String, ArrayList<Object>> getTendency() {
        return tendency;
    }

    public void setAge(HashMap<String, ArrayList<Object>> age) {
        this.age = age;
    }

    public HashMap<String, ArrayList<Object>> getAge() {
        return age;
    }

    public void setTitle(ArrayList<HashMap<String, Object>> title) {
        this.title = title;
    }

    public ArrayList<HashMap<String, Object>> getTitle() {
        return title;
    }

    public void setEducationAry(ArrayList<HashMap<String, Object>> educationAry) {
        this.educationAry = educationAry;
    }

    public ArrayList<HashMap<String, Object>> getEducationAry() {
        return educationAry;
    }

    public ContrastTheWorkerByFieldResponse() {
    }

    public ContrastTheWorkerByFieldResponse(
            HashMap<String, ArrayList<Object>> tendency,
            HashMap<String, ArrayList<Object>> age,
            ArrayList<HashMap<String, Object>> title,
            ArrayList<HashMap<String, Object>> educationAry) {
        this.tendency = tendency;
        this.age = age;
        this.title = title;
        this.educationAry = educationAry;
    }
}
