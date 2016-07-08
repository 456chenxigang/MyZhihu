package com.myzhihu.mvp.myzhihu.model.entity;

/**
 * Created by CXG on 2016/7/5.
 */
public class TopicItem {

    private String id,image,type,num;

    public void setId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
