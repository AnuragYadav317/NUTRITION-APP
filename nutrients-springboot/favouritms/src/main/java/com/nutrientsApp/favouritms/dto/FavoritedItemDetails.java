package com.nutrientsApp.favouritms.dto;

import com.nutrientsApp.favouritms.entity.FoodNutrient;


import java.util.List;
import java.util.Objects;

public class FavoritedItemDetails {
   
    private long fdcId;
    private String description;
    private String brandOwner;
    private List<FoodNutrient> foodNutrients;
    private String username;
 
    public long getFdcId() {
        return fdcId;
    }

    public void setFdcId(long fdcId) {
        this.fdcId = fdcId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public List<FoodNutrient> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(List<FoodNutrient> nutrients) {
        this.foodNutrients = nutrients;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
