package com.assignment.filters.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11-06-2019.
 */

public class BaseResponse {
    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
