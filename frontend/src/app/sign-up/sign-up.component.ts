import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, RequiredValidator, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PizzaServiceService } from 'src/pizza-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  constructor(private pizza:PizzaServiceService,private router:Router, private snackBar: MatSnackBar) {}
 
  signupformdata:any = new FormGroup(
    {
      name:new FormControl('',[Validators.required]),
      emailId:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required]),
      phoneNo: new FormControl('',[Validators.required, Validators.minLength(10)]),
      address:new FormControl('')


    }
  )

  sendsignupdata()
  {
    // alert("hello")
    this.pizza.registercustomer(this.signupformdata.value).subscribe(data=>console.log(data));
    this.router.navigateByUrl("/login")
  }

  movetosignup()
  {
    this.router.navigateByUrl("/signup")
  }
  movetologinpage()
  {
    alert("hello")
    this.router.navigateByUrl("/login")
  }

  movetohomepage()
  {
    this.router.navigateByUrl("/home")
  }
  movetoorderpage()
  {
    alert("hello")
    this.router.navigateByUrl("/order")
  }

  saveCustomer=()=>{
    console.log(this.signupformdata.value);
    //display snackbar
  
      this.snackBar.open('Signup Successful','close' );

  }
  //define getter for name
  get name(){
    return this.signupformdata.get('name');
  }
   //define getter for password
   get password(){
    return this.signupformdata.get('password');
  }
    //define getter for EmailId
    get emailId(){
      return this.signupformdata.get('emailId');
    }
    //define getter for phone No
    get phoneNo(){
      return this.signupformdata.get('phoneNo');
    }
   

}
