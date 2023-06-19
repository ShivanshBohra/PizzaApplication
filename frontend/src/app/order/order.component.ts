import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/assets/Customer';
import { Products } from 'src/assets/Products';
import { PizzaServiceService } from 'src/pizza-service.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent {

  constructor(private service:PizzaServiceService,private router:Router)
  {
console.log(this.customer)
  }

  userdetails:boolean = false;
  orders:boolean = false ;
  customer:any
  Orders:Products[]=[]


  // getuserdetails()
  // {
  //  this.userdetails=true;
  //  this.orders=false;
  //  this.service.getuserdetails().subscribe(data=>this.customer=data)
  // }
  emailId: any;

  getuserdetails() {
    this.userdetails = true;
    this.orders = false;
    this.service.getuserdetails().subscribe(data => {
      if(data){
        this.customer = data;
        this.emailId = this.customer.emailId;
      }
    });
  }



  getorderdetails()
 {
   this.orders=true;
   this.userdetails=false;
   this.service.getalluserorders().subscribe(data=>this.Orders=data)
 }


movetohome()
{
this.router.navigateByUrl("/home");
}
  deleteorder(id: any) {
    this.service.getuserdetails().subscribe(data => {
      if(data){
        this.customer = data;
        this.emailId = this.customer.emailId;
        this.service.deleteProductById(id, this.emailId).subscribe(data => {
          this.getorderdetails();
          this.Orders = data
        });
      }
    });
  }

}
