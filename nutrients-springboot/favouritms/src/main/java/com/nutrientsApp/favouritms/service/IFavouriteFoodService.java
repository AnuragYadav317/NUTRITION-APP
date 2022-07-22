package com.nutrientsApp.favouritms.service;


import com.nutrientsApp.favouritms.dto.AddFavouriteFoodRequest;
import com.nutrientsApp.favouritms.dto.FavoritedItemDetails;

import com.nutrientsApp.favouritms.dto.RemoveFavorited;
import com.nutrientsApp.favouritms.exceptions.FavoritedItemExistException;
import com.nutrientsApp.favouritms.exceptions.FoodNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public interface IFavouriteFoodService {

    FavoritedItemDetails addFavouriteFood(@Valid AddFavouriteFoodRequest requestData)throws FavoritedItemExistException;
    List<FavoritedItemDetails> listFavoriteItemsByUsername(String username);
    void removeFavorite(RemoveFavorited requestData) throws FoodNotFoundException;

}