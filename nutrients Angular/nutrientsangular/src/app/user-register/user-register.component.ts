import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/service/authentication.service';
import { User } from '../model/User';


@Component({
  selector: 'user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {

  user: User | undefined;

  nameCtrl: FormControl;
  passwordCtrl: FormControl;
  myform: FormGroup;
  errMsg: string | undefined;


  constructor(private builder: FormBuilder, private router: Router, private authService: AuthenticationService) {
    this.nameCtrl = builder.control('', [Validators.required, Validators.minLength(2)]);
    this.passwordCtrl = builder.control('', [Validators.required, Validators.minLength(2), Validators.maxLength(15)]);
    const mappingObj = {
      username: this.nameCtrl,
      password: this.passwordCtrl
    };
    this.myform = builder.group(mappingObj);

  }


  addUser() {

    if (!this.myform.valid) {
      this.myform.markAllAsTouched();
      return;
    }

    const userName: string = this.nameCtrl.value;
    const password: string = this.passwordCtrl.value;
    this.user = new User(userName, password);
    const observable: Observable<string> = this.authService.register(userName, password);
    observable.subscribe({
      next: (token: string) => {
        console.log("received token", token);
        this.errMsg = undefined;

        this.router.navigateByUrl('/user-login');
      },
      error: (err: Error) => {
        this.errMsg = err.message;
        console.log(this.errMsg)
      }
    });
    console.log("inside onFormSubmit username=" + userName + " ,password=" + password);

  }


  isControlTouchedOrDirty(control: FormControl) {
    return (control.touched || control.dirty);
  }

  isControlRequireValid(control: FormControl) {
    const touchedOrDirty = this.isControlTouchedOrDirty(control);
    if (!touchedOrDirty) {
      return true;
    }
    return !control.errors?.['required'];
  }

  isNameCtrlRequireValid() {
    const valid = this.isControlRequireValid(this.nameCtrl);
    return valid;
  }

  isNameCtrlMinLengthValid() {
    const touchedOrDirty = this.isControlTouchedOrDirty(this.nameCtrl);
    if (!touchedOrDirty) {
      return true;
    }
    return !this.nameCtrl.errors?.['minlength']
  }

  isPasswordCtrlRequireValid() {
    const valid: boolean = this.isControlRequireValid(this.passwordCtrl);
    return valid;
  }

  isPasswordCtrlMinOrMaxValid() {
    const touchedOrDirty = this.isControlTouchedOrDirty(this.passwordCtrl);
    if (!touchedOrDirty) {
      return true;
    }
    return !(this.passwordCtrl.errors?.['minlength'] || this.passwordCtrl.errors?.['maxlength'])
  }

}



