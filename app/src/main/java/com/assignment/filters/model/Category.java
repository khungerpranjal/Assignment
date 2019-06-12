package com.assignment.filters.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 11-06-2019.
 */

public class Category
{
    @SerializedName("category_id")
    private int categoryId;
    @SerializedName("name")
    private String name;
    @SerializedName("filters")
    private List<Filter> filters = null;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}
