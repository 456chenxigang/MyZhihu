
package com.myzhihu.mvp.myzhihu.model.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "images",
    "type",
    "id",
    "ga_prefix",
    "title",
    "multipic"
})
public class Story {

    @JsonProperty("images")
    private List<String> images = new ArrayList<String>();
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("ga_prefix")
    private String gaPrefix;
    @JsonProperty("title")
    private String title;
    @JsonProperty("date")
    private String date;
    @JsonProperty("multipic")
    private Boolean multipic;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The images
     */
    @JsonProperty("images")
    public List<String> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    @JsonProperty("images")
    public void setImages(List<String> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The gaPrefix
     */
    @JsonProperty("ga_prefix")
    public String getGaPrefix() {
        return gaPrefix;
    }

    /**
     * 
     * @param gaPrefix
     *     The ga_prefix
     */
    @JsonProperty("ga_prefix")
    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     *
     * @return
     *     The title
     */
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     *     The title
     */
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The multipic
     */
    @JsonProperty("multipic")
    public Boolean getMultipic() {
        return multipic == null?false:multipic;
    }

    /**
     * 
     * @param multipic
     *     The multipic
     */
    @JsonProperty("multipic")
    public void setMultipic(Boolean multipic) {
        this.multipic = multipic;
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
