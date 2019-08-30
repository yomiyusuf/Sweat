package com.yomi.sweat.model;

import com.google.gson.annotations.SerializedName;

public class Tag
{

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public Tag() {
    }

    /**
     *
     * @param id
     * @param name
     */
    public Tag(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
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

}