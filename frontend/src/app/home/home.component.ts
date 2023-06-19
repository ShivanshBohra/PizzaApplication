import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PizzaServiceService } from 'src/pizza-service.service';
import { Products } from 'src/assets/Products';
import {AuthService} from "../auth/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private router:Router,private pizzas:PizzaServiceService, private auth:AuthService)
  {
    this.showpizza();
this.getallproducts();
this.checklocalstorage();
  }
  beverages=false;
  pizza=true;
  softdrinks=false;
  signform=true;



  showpizza()
  {
    this.beverages=false;
    this.pizza=true;
    this.softdrinks=false;


  }
  showsoftdrinks()
  {
    this.beverages=false;
    this.pizza=false;
    this.softdrinks=true;

  }
  showbeverages()
  {
    this.beverages=true;
    this.pizza=false;
    this.softdrinks=false;

  }
movetosignup() {
  this.router.navigateByUrl("/signup")
}
  movetologinpage() {
    this.router.navigateByUrl("/login")
  }

  movetohomepage(){

  this.router.navigateByUrl("/home")
}
  movetoorderpage() {

      this.router.navigateByUrl("/order")


  }

  allproducts:Products[]=[]
  getallproducts()
  {
    this.pizzas.getallproducts().subscribe(data=>this.allproducts=data)
  }

  cart=false;
  login=true;
  signup=true
  logout=false

  checklocalstorage()
  {
    if(localStorage.getItem("role")=="Customer")
    {
    this.cart=true;
    this.signup=false;
    this.login=false;
    this.logout=true;
    this.usercart=true;
    }
  }

  movetologout()
  {
  this.auth.logout();
    this.signup=false;
    this.logout=false;
    localStorage.clear();
    window.location.reload()
  }

  usercart=false;
  movedatatocart()
  {

   //
  }





allorders:any[]=[]
  movetoviewcart(p:any)
  {
    this.allorders.push(p);
    console.log(this.allorders);
    this.placeorder(p)
  }

  cartview=false;

  removeitem(a:any)
  {
this.allorders = this.removeobjfromid(this.allorders,a);
console.log(this.allorders);
  }

removeobjfromid(arr:any[],id:any)
{
  return arr.filter((abc)=>abc.productId !==id)
}

mainview = true ;
ab=true;

viewcart()
{

  this.mainview = false ;
  this.cartview=true;
  this.ab=false;
}

back()
{
  this.mainview = true;
  this.cartview=false;
}

placeorder(p:any)
{
  console.log(this.allorders);
  this.pizzas.addProductToUserCart(p).subscribe(data=>console.log(data))
}

places()
{
  alert("Order placed Successfully");
  this.router.navigateByUrl("/home");
  this.mainview = true;
  this.cartview=false;
}
}
