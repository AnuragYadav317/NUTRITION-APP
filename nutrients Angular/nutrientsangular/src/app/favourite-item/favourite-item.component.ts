import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FoodService } from 'src/service/FoodService';
import { Food } from '../model/Food';


@Component({
  selector: 'favourite-item',
  templateUrl: './favourite-item.component.html',
  styleUrls: ['./favourite-item.component.css']
})
export class FavouriteItemComponent implements OnInit {


  foodItem: Food[] | undefined

  constructor(private service: FoodService) {

  }


  ngOnInit(): void {
    const observer = {
      next: (result: any[]) => {
        this.foodItem = result;
      },

      error: (err: Error) => {
        console.log('error received', err.message);
      },
    };
    const observable: Observable<Food[]> = this.service.getFavouriteFoodList();
    observable.subscribe(observer);

  }



  removeFromFavourites(foodItem: Food) {
    //alert("Food is removed from favourite list");
    const observer = {
      next: (result: Food) => {
        this.ngOnInit();


      },
      error: (err: Error) => {
        console.log('error received', err.message);
      },
    };
    const observable: Observable<Food> =
      this.service.removeItemFromFavourite(foodItem);
    observable.subscribe(observer);
  }
}








