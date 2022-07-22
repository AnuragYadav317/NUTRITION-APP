
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { baseServerUrl } from 'src/app/Common/constant';
import { Food } from 'src/app/model/Food';
import { AuthenticationService } from './authentication.service';

@Injectable({ providedIn: 'root' })
export class FoodService {

    constructor(private client: HttpClient, private authService: AuthenticationService) { }
    getFoodList(): Observable<Food[]> {
        const url = "https://api.nal.usda.gov/fdc/v1/foods/list?dataType=Branded&pageSize=25&pageNumber=0&api_key=iF7Sd3cffQaE8VB45pGjBUxBmpEtvzg8uM8X8tOq";
        const observable: Observable<Food[]> = this.client.get<Food[]>(url);
        return observable;

    }

    addToFavourite(food: Food): Observable<Food> {
        const username = this.authService.getUsername();

        const requestData = { ...food, username };

        const url = baseServerUrl + "/favourites/add";
        const observable: Observable<Food> = this.client.post<Food>(url, requestData);
        return observable;



    }

    getFavouriteFoodList(): Observable<Food[]> {
        const username = this.authService.getUsername();
        const url = baseServerUrl + "/favourites/findby/" + username;
        const observable: Observable<Food[]> = this.client.get<Food[]>(url);
        return observable;
    }



    removeItemFromFavourite(food: Food): Observable<Food> {
        const username = this.authService.getUsername();

        const data = { username, fdcId: food.fdcId };
        const url = baseServerUrl + "/favourites/delete"
        const observable: Observable<Food> = this.client.delete<Food>(url, { body: data });
        return observable;

    }
}



