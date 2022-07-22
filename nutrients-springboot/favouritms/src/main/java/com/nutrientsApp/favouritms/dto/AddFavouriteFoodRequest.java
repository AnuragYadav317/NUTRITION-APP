
package com.nutrientsApp.favouritms.dto;

import com.nutrientsApp.favouritms.entity.FoodNutrient;

import org.hibernate.validator.constraints.Length;


import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddFavouriteFoodRequest {
	@NotNull
    private long fdcId;
    
    private String description; 
    private String brandOwner;
    private List<FoodNutrient> foodNutrients;
    @NotBlank
	@Length(min=2,max=15)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
}
