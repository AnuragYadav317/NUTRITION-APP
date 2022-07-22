package com.nutrientsApp.favouritms.util;

import com.nutrientsApp.favouritms.dto.AddFavouriteFoodRequest;
import com.nutrientsApp.favouritms.dto.FavoritedItemDetails;
import com.nutrientsApp.favouritms.entity.FavoritedItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class FavouriteFoodUtil {

    private long id;
    
    
    public String generateId(String username) {
    	return ++id + "-u-" + username;   	
    }



    public FavoritedItemDetails toFavouritFoodDetails(FavoritedItem favoritedItem){
        FavoritedItemDetails favoritedItemDetails =new FavoritedItemDetails();
        favoritedItemDetails.setFdcId(favoritedItem.getFdcId());
        favoritedItemDetails.setDescription(favoritedItem.getDescription());
        favoritedItemDetails.setBrandOwner(favoritedItem.getBrandOwner());
        favoritedItemDetails. setFoodNutrients(favoritedItem.getFoodNutrients());
        favoritedItemDetails.setUsername(favoritedItem.getUsername());
        return favoritedItemDetails;
    }

    public List<FavoritedItemDetails>toListFavouritFoodDetails(Collection<FavoritedItem>favoritedItems){
        List<FavoritedItemDetails>desired=new ArrayList<>();
        for(FavoritedItem food :favoritedItems){
            FavoritedItemDetails favoritedItemDetails =toFavouritFoodDetails(food);
            desired.add(favoritedItemDetails);
        }
        return desired;
    }

     

    public FavoritedItem toFavoritedItem(AddFavouriteFoodRequest requestData){
        FavoritedItem favoritedItem=new FavoritedItem();
        String id = generateId(requestData.getUsername());
        favoritedItem.setId(id);
        favoritedItem.setFdcId(requestData.getFdcId());
        favoritedItem.setDescription(requestData.getDescription());
        favoritedItem.setBrandOwner(requestData.getBrandOwner());
        favoritedItem. setFoodNutrients(requestData. getFoodNutrients());
        favoritedItem.setUsername(requestData.getUsername());
        return favoritedItem;
    }









}
