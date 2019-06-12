package com.assignment.filters.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11-06-2019.
 */

public class Filter
{
    @SerializedName("name")
    private String name;
    @SerializedName("default")
    private int _default;
    @SerializedName("id")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefault() {
        return _default;
    }

    public void setDefault(int _default) {
        this._default = _default;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
