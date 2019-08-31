package com.yomi.sweat.model;

import com.google.gson.annotations.SerializedName;

public class Attribute
{
    @SerializedName("id")
    private Integer id;
    @SerializedName("code_name")
    private String codeName;
    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private String value;
    @SerializedName("total")
    private String total;

    /**
     * No args constructor for use in serialization
     *
     */
    public Attribute() {
    }

    /**
     *
     * @param total
     * @param id
     * @param codeName
     * @param name
     * @param value
     */
    public Attribute(Integer id, String codeName, String name, String value, String total) {
        super();
        this.id = id;
        this.codeName = codeName;
        this.name = name;
        this.value = value;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getValue() {
        return Double.parseDouble(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getTotal() {
        return Double.parseDouble(total);
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int barValue(){
        return (int)Math.round(getValue()/getTotal() * 100);
    }
}
