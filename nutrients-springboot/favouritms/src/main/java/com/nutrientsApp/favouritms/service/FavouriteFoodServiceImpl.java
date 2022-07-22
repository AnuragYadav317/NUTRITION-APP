package com.nutrientsApp.favouritms.service;

import com.nutrientsApp.favouritms.dao.IFavouritFoodRepository;
import com.nutrientsApp.favouritms.dto.AddFavouriteFoodRequest;
import com.nutrientsApp.favouritms.dto.FavoritedItemDetails;
import com.nutrientsApp.favouritms.dto.RemoveFavorited;
import com.nutrientsApp.favouritms.entity.FavoritedItem;
import com.nutrientsApp.favouritms.exceptions.FavoritedItemExistException;
import com.nutrientsApp.favouritms.exceptions.FoodNotFoundException;
import com.nutrientsApp.favouritms.util.FavouriteFoodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteFoodServiceImpl implements IFavouriteFoodService {
    @Autowired
    private IFavouritFoodRepository repository;
    @Autowired
    private FavouriteFoodUtil util;

    @Override
    public FavoritedItemDetails addFavouriteFood(AddFavouriteFoodRequest requestData)throws FavoritedItemExistException{
       Optional<FavoritedItem>  optional=repository.findByUsernameAndFdcId(requestData.getUsername(), requestData.getFdcId());
        if(optional.isPresent()){
            throw new FavoritedItemExistException("Favourit Food aklready exist");
        }
        FavoritedItem favoritedItem=util.toFavoritedItem(requestData);
        favoritedItem=repository.save(favoritedItem);
        FavoritedItemDetails desired=util.toFavouritFoodDetails(favoritedItem);
        return desired;
    }



  

    @Override
    public void  removeFavorite(RemoveFavorited requestData) throws FoodNotFoundException {
        Optional<FavoritedItem>optional=repository.findByUsernameAndFdcId(requestData.getUsername(), requestData.getFdcId());
        if(!optional.isPresent()){
            throw new FoodNotFoundException("Food item not found");
        }
        FavoritedItem favoritedItem= optional.get();
        repository.delete(favoritedItem);
       

    }


    @Override
    public List<FavoritedItemDetails> listFavoriteItemsByUsername(String username) {
        List<FavoritedItem>favoritedItems=repository.findByUsername(username);
        List<FavoritedItemDetails>desired=util.toListFavouritFoodDetails(favoritedItems);
        return desired;
    }
}
