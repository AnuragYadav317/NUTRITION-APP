import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/service/authentication.service';
import { User } from '../model/User';



@Component({
  selector: 'user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {

  nameCtrl: FormControl;
  passwordCtrl: FormControl;
  myform: FormGroup;
  errMsg: string | undefined;

  constructor(private builder: FormBuilder, private router: Router, private authService: AuthenticationService) {
    this.nameCtrl = builder.control('', [Validators.required, Validators.minLength(2)]);
    this.passwordCtrl = builder.control('', [Validators.required, Validators.minLength(2), Validators.maxLength(15)]);
    const mappingObj = {
      userName: this.nameCtrl,
      password: this.passwordCtrl
    };
    this.myform = builder.group(mappingObj);

  }


  loginUser() {

    if (!this.myform.valid) {
      this.myform.markAllAsTouched();
      return;
    }

    const username: string = this.nameCtrl.value;
    const password: string = this.passwordCtrl.value;
    const observable: Observable<string> = this.authService.login(username, password);
    observable.subscribe({
      next: (token: string) => {
        console.log("received token", token);
        this.errMsg = undefined;
        this.authService.saveToken(username, token);
        this.router.navigateByUrl('');
      },
      error: (err: Error) => {
        this.errMsg = err.message;
      }
    });
    console.log("inside onFormSubmit username=" + username + " ,password=" + password);

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
