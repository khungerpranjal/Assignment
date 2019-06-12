package com.assignment.filters.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11-06-2019.
 */

public class ExcludeList
{
    @SerializedName("category_id")
    private int categoryId;
    @SerializedName("filter_id")
    private int filterId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getFilterId() {
        return filterId;
    }

    public void setFilterId(int filterId) {
        this.filterId = filterId;
    }
}
