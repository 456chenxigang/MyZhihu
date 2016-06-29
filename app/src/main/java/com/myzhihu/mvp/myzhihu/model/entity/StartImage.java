package com.myzhihu.mvp.myzhihu.model.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CXG on 2016/6/27.
 */
//public class StartImage {
//
//    /**
//     * text :
//     * img : https://pic4.zhimg.com/cf482a7c6f59639f9011b56987209cbd.jpg
//     */
//
//    private String text;
//    private String img;
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text",
        "img"
})
public class StartImage {

    @JsonProperty("text")   //显示指定字段的别名s
    private String text;
    @JsonProperty("img")
    private String img;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The text
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The img
     */
    @JsonProperty("img")
    public String getImg() {
        return img;
    }

    /**
     *
     * @param img
     * The img
     */
    @JsonProperty("img")
    public void setImg(String img) {
        this.img = img;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}