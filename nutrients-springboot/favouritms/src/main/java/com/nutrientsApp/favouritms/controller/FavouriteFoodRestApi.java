package com.nutrientsApp.favouritms.controller;

import com.nutrientsApp.favouritms.dto.AddFavouriteFoodRequest;
import com.nutrientsApp.favouritms.dto.FavoritedItemDetails;
import com.nutrientsApp.favouritms.dto.RemoveFavorited;
import com.nutrientsApp.favouritms.exceptions.FavoritedItemExistException;
import com.nutrientsApp.favouritms.exceptions.FoodNotFoundException;
import com.nutrientsApp.favouritms.service.FavouriteFoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/favourites")
@RestController
public class FavouriteFoodRestApi {
    @Autowired
    private FavouriteFoodServiceImpl service;

    
    @GetMapping("/findby/{username}")
    public List<FavoritedItemDetails>getAllFavouriteFoodByUsername(@PathVariable String username)throws FoodNotFoundException{
        List<FavoritedItemDetails>response=service.listFavoriteItemsByUsername(username);
        return response;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/add")
    public FavoritedItemDetails addFavouriteFood(@RequestBody AddFavouriteFoodRequest requestData)throws FavoritedItemExistException{
        FavoritedItemDetails response=service.addFavouriteFood(requestData);
        return response;
    }
   

    @DeleteMapping("/delete")
    public void removeFavourit(@RequestBody RemoveFavorited requestdata)throws FoodNotFoundException{ 
        service.removeFavorite(requestdata);
        
    }
}
