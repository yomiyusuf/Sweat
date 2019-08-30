package com.yomi.sweat.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Program implements Serializable
{

    @SerializedName("id")
    private Integer id;
    @SerializedName("acronym")
    private String acronym;
    @SerializedName("code_name")
    private String codeName;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("attributes")
    private List<Attribute> attributes = null;
    @SerializedName("trainer")
    private Trainer trainer;
    @SerializedName("tags")
    private List<Tag> tags = null;
    private final static long serialVersionUID = -168455240174985158L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Program() {
    }

    /**
     *
     * @param tags
     * @param id
     * @param codeName
     * @param acronym
     * @param name
     * @param image
     * @param attributes
     * @param trainer
     */
    public Program(Integer id, String acronym, String codeName, String name, String image, List<Attribute> attributes, Trainer trainer, List<Tag> tags) {
        super();
        this.id = id;
        this.acronym = acronym;
        this.codeName = codeName;
        this.name = name;
        this.image = image;
        this.attributes = attributes;
        this.trainer = trainer;
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}


