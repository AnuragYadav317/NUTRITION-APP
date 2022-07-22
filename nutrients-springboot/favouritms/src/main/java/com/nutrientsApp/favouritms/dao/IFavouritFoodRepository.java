package com.nutrientsApp.favouritms.dao;

import com.nutrientsApp.favouritms.entity.FavoritedItem;
import com.nutrientsApp.favouritms.exceptions.FoodNotFoundException;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IFavouritFoodRepository extends MongoRepository<FavoritedItem,String> {
    List<FavoritedItem>findByUsername(String username) ;

   Optional<FavoritedItem>  findByUsernameAndFdcId(String userName, long fdcId);
}
