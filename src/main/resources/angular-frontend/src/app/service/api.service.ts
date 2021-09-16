import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Orders, Products } from '../pricelist/pricelist.component';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  
  constructor(private http:HttpClient) { }

  getPriceList(){
    return this.http.get<any>("http://localhost:8080/api/price-list");
  }

  getCalculatedPrice(itemList: Orders[]) {
    return this.http.post("http://localhost:8080/api/price-calculator", itemList);
  }
}