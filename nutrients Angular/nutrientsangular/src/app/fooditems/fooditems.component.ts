import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/service/authentication.service';
import { FoodService } from 'src/service/FoodService';
import { Food } from '../model/Food';

@Component({
  selector: 'app-fooditems',
  templateUrl: './fooditems.component.html',
  styleUrls: ['./fooditems.component.css']
})
export class FooditemsComponent {
  foodItem: Food[] | undefined


  constructor(private service: FoodService, private authService: AuthenticationService) {
    const observer = {
      next: (result: Food[]) => {
        this.foodItem = result;
      },

      error: (err: Error) => {
        console.log('error received', err.message);
      },
    };
    const observable: Observable<Food[]> = service.getFoodList();
    observable.subscribe(observer);
  }




  addToFavourites(foodItem: Food) {
    console.log(foodItem);
    const food = { userId: 1, ...foodItem };

    const observer = {
      next: (result: Food) => {

      },
      error: (err: Error) => {
        console.log('error received', err.message);

      },
    };
    const observable: Observable<Food> =
      this.service.addToFavourite(food);
    observable.subscribe(observer);
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();

  }
}