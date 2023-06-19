import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Customer } from './assets/Customer';
import { Observable } from 'rxjs';
import { Products } from './assets/Products';

@Injectable({
  providedIn: 'root'
})
export class PizzaServiceService {

  constructor(private httpClient:HttpClient) { }

  registercustomer(signupdata:any)
  {
    // alert("Register Customer")
    return this.httpClient.post("http://localhost:8807/api/customer/register",signupdata)
  }


  loginvalidation(logindata:any)
  {
    return this.httpClient.post("http://localhost:8807/api/customer/login",logindata)
  }

  getallusers():Observable<Customer[]>
  {
    let httpHeaders = new HttpHeaders(
      {
        'Authorization' : 'Bearer ' + localStorage.getItem('jwt')

      });
      let requestOptions = { headers:httpHeaders}
      return this.httpClient.get<Customer[]>("http://localhost:8805/api/v1/get",requestOptions);
  }
  getallproducts():Observable<Products[]>
  {

      return this.httpClient.get<Products[]>("http://localhost:8809/api/v1/getproducts");
  }

  addProductToUserCart(productObj:any){
    alert("Add Project to User Cart");
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    });
    let requestOptions = {headers : httpHeaders}
    return this.httpClient.post("http://localhost:8809/api/v1/addproducttouser",productObj,requestOptions);
  }


  getuserdetails():Observable<Customer>
  {
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    });
    let requestOptions = {headers : httpHeaders}
      return this.httpClient.get<Customer>("http://localhost:8809/api/v1/getdetails",requestOptions);
  }

  getalluserorders():Observable<Products[]>
  {
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    });
    let requestOptions = {headers : httpHeaders}
      return this.httpClient.get<Products[]>("http://localhost:8809/api/v1/getproductofuser",requestOptions);
  }
  deleteProductById(id:any,email:any):Observable<Products[]>{
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    });
    let requestOptions = {headers : httpHeaders}
    return this.httpClient.delete<Products[]>("http://localhost:8809/api/v1/deleteproduct/"+id+"/"+email,requestOptions);
  }

}
