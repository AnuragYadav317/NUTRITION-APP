package com.nutrientsApp.favouritms.service;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nutrientsApp.favouritms.dao.IFavouritFoodRepository;
import com.nutrientsApp.favouritms.dto.AddFavouriteFoodRequest;
import com.nutrientsApp.favouritms.dto.FavoritedItemDetails;
import com.nutrientsApp.favouritms.dto.RemoveFavorited;
import com.nutrientsApp.favouritms.entity.FavoritedItem;
import com.nutrientsApp.favouritms.exceptions.FavoritedItemExistException;
import com.nutrientsApp.favouritms.exceptions.FoodNotFoundException;

import org.junit.jupiter.api.function.Executable;


import com.nutrientsApp.favouritms.util.FavouriteFoodUtil;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;




@ExtendWith(MockitoExtension.class)
public class FavouriteFoodServiceImplTest {
	    
	@Mock
	IFavouritFoodRepository repository; 
	
	@Mock
	FavouriteFoodUtil util;

	
	@Spy
	@InjectMocks
	FavouriteFoodServiceImpl service;
	
	
	/**
     * scenario: FavouriteItem list exist for the user
     * input : userId=10
     * expectation: Favourite item list returned for the user
     *     */

	
	 @Test
	    public void testlistFavoriteItemsByUserId() {
	        String userName = "Harsh";
	        List<FavoritedItem> desired =mock(List.class);
	        List<FavoritedItemDetails> expected = mock(List.class); 
	        when(repository.findByUsername(userName)).thenReturn(desired);    
	        when(util.toListFavouritFoodDetails(desired)).thenReturn(expected);
	        List<FavoritedItemDetails>  result = service.listFavoriteItemsByUsername(userName);
	        assertSame(expected,result);
	        verify(repository).findByUsername(userName);
	        
	    }
	 
	 /**@Test
	 public void testlistFavoriteItemsByUserId_2(){
		 String userName ="Roshi";
		 when(repository.findByUserName(userName)).thenThrow(FoodNotFoundException.class);
		 Executable executable = ()-> service.listFavoriteItemsByUserName(userName);
		 assertThrows(FoodNotFoundException.class ,executable);
		 
		**/ 
		 
	 
	 
	 
	 

		/**
	     * scenario: FavouriteItem do not exist for the user
	     * input : AddFavouriteFoodRequest
	     * expectation: Favouritefood details 
	     **/
	 
	 
	 @Test
	 public void testaddFavouriteFood() throws FavoritedItemExistException{	 
		 AddFavouriteFoodRequest data = mock(AddFavouriteFoodRequest.class);
		 FavoritedItem desired = mock(FavoritedItem.class);
		 FavoritedItem savedFoodItem = mock(FavoritedItem.class);
		 FavoritedItemDetails expected = mock(FavoritedItemDetails.class); 
		 Optional<FavoritedItem>optional=Optional.empty();
		 when(repository.findByUsernameAndFdcId(data.getUsername(),data.getFdcId())).thenReturn(optional); 
		 when(util.toFavoritedItem(data)).thenReturn(desired); 
		 when(repository.save(desired)).thenReturn(desired);
		 when(util.toFavouritFoodDetails(desired)).thenReturn(expected);
		 FavoritedItemDetails result = service.addFavouriteFood(data);
		 assertEquals(expected,result);	 
		 
	 }
	 
	 
	 /**
	     * scenario: FavouriteItem already exist for the user
	     * input : AddFavouriteFoodRequest
	     * expectation: FavoritedItemExistException 
	     **/
	 
	 @Test
	 public void testaddFavouriteFood_2() throws FavoritedItemExistException{
		 AddFavouriteFoodRequest data = mock(AddFavouriteFoodRequest.class);
		 FavoritedItem desired = mock(FavoritedItem.class);
		 Optional<FavoritedItem>optional=Optional.of(desired);
		 when(repository.findByUsernameAndFdcId(data.getUsername(),data.getFdcId())).thenReturn(optional);
		 Executable executable = ()-> service.addFavouriteFood(data);
		 assertThrows(FavoritedItemExistException.class ,executable);
		 
	 }
	 
	 /**
	     * scenario: FavouriteItem exist for the user
	     * input : RemoveFavorited
	     * expectation: FavoritedItemdetails 
	     **/
	 
	 
	 @Test
	 public void testRemoveFavorite()throws FoodNotFoundException{
		 RemoveFavorited data = mock(RemoveFavorited.class);
		 FavoritedItem desired = mock(FavoritedItem.class);
		 Optional<FavoritedItem>optional=Optional.of(desired);
		 when(repository.findByUsernameAndFdcId(data.getUsername(),data.getFdcId())).thenReturn(optional);
		 service.removeFavorite(data);
		 verify(repository).delete(desired);;
	 }
	
		 
		 
	 
	 
	 /**
	     * scenario: FavouriteItem do not exist for the user
	     * input : RemoveFavorited
	     * expectation: FoodNotFoundException
	     **/
	 
	 
	 @Test
	 public void testRemoveFavorite_2()throws FoodNotFoundException{
		 RemoveFavorited data = mock(RemoveFavorited.class);
		 Optional<FavoritedItem>optional=Optional.empty();
		 when(repository.findByUsernameAndFdcId(data.getUsername(),data.getFdcId())).thenReturn(optional);
		 Executable executable = ()-> service.removeFavorite(data);
		 assertThrows(FoodNotFoundException.class ,executable);
		 
		 
		 
	 }
	 
	 

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 


}
