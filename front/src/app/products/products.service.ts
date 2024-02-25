import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from './product.class';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

    //private static productslist: Product[] = null;
    //private products$: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);

    constructor(private http: HttpClient) { }

    getProducts(): Observable<Product[]> {
      return this.http.get<Product[]>("http://localhost:8080/products");
    }

    create(prod: Product): Observable<Product> {
      return this.http.post<Product>("http://localhost:8080/products", prod);
    }

    update(prod: Product): Observable<Product>{
      return this.http.patch<Product>("http://localhost:8080/products"+prod.id, prod);
    }


    delete(id: number): Observable<void>{
      return this.http.delete<void>("http://localhost:8080/products"+id);
    }
}
