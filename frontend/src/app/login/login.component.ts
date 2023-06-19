import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PizzaServiceService } from 'src/pizza-service.service';
import {AuthService} from "../auth/auth.service";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
/* This class is a component that has a constructor that takes in a PizzaServiceService, a Router, an AuthService, and a
MatSnackBar */
export class LoginComponent {
  constructor(private pizza:PizzaServiceService,private router:Router, private auth:AuthService,private snackBar: MatSnackBar)
  {}

  loginformdata:any=new FormGroup({
    emailId:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required])
  })

  responseData:any;
  sendLoginData(){
    this.pizza.loginvalidation(this.loginformdata.value).subscribe({
      next :response =>
    {
      this.responseData = response;
      localStorage.setItem("jwt", this.responseData.token);
      localStorage.setItem("role", this.responseData.role);
      localStorage.setItem("email", this.responseData.email);
      localStorage.setItem("password", this.responseData.password);
      if(this.responseData.role=="Customer"){
        this.auth.login();
        this.router.navigateByUrl("/home")
      }
    },
    error : error => {
      alert("Invalid Credentials");
    }
      });
  }

  movetosignup()
  {
    this.router.navigateByUrl("/signup")
  }
  movetologinpage()
  {

    this.router.navigateByUrl("/login")
  }

  movetohomepage()
  {
    this.router.navigateByUrl("/home")
  }
  movetoorderpage()
  {

    this.router.navigateByUrl("/order")
  }
  loginCustomer=()=>{
    console.log(this.loginformdata.value);

    // //display snackbar
    //  this.snackBar.open('Login Successful','close' );
  }
  //getter for emailID
  get emailId(){
    return this.loginformdata.get('emailId');
  }
   //getter for Password
   get password(){
    return this.loginformdata.get('password');
  }

}
