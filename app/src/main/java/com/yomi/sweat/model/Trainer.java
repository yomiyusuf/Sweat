package com.yomi.sweat.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trainer
{

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("code_name")
    private String codeName;
    @SerializedName("image_url")
    private String imageUrl;

    /**
     * No args constructor for use in serialization
     *
     */
    public Trainer() {
    }

    /**
     *
     * @param id
     * @param codeName
     * @param imageUrl
     * @param name
     */
    public Trainer(Integer id, String name, String codeName, String imageUrl) {
        super();
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
