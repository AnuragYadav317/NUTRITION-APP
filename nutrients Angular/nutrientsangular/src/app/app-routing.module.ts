import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FavouriteItemComponent } from './favourite-item/favourite-item.component';
import { FooditemsComponent } from './fooditems/fooditems.component';
import { LoginGuard } from './guards/login.guard';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegisterComponent } from './user-register/user-register.component';

const routes: Routes = [
  {
    path: 'user-register',
    component: UserRegisterComponent
  },
  {
    path: 'user-login',
    component: UserLoginComponent
  },
  {
    path: '',
    component: FooditemsComponent,

  },
  {
    path: 'favourite-item',
    component: FavouriteItemComponent,
    canActivate: [LoginGuard]

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
