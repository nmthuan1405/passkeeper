package com.example.passkeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoriteStatus {
    @SerializedName("is_fav")
    @Expose
    private Boolean isFavorite;

    public FavoriteStatus(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Boolean getFavoriteStatus() {
        return isFavorite;
    }

    public void setFavoriteStatus(Boolean favorite) {
        isFavorite = favorite;
    }
}
