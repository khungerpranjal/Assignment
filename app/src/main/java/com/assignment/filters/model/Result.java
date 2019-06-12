package com.assignment.filters.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 11-06-2019.
 */

public class Result
{
    @SerializedName("categories")
    private List<Category> categories = null;
    @SerializedName("exclude_list")
    private List<List<ExcludeList>> excludeList = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<List<ExcludeList>> getExcludeList() {
        return excludeList;
    }

    public void setExcludeList(List<List<ExcludeList>> excludeList) {
        this.excludeList = excludeList;
    }
}
