import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProdutosModel} from '../model/produtos.model';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  constructor(private http: HttpClient) { }

  public getProdutos(idMarca:any) : Observable<ProdutosModel[]> {
    return this.http.get<ProdutosModel[]>(`https://ap1cloud.azurewebsites.net/marcas/${idMarca}/produtos`);
  }

  public createProdutos(idMarca: any, produtos: ProdutosModel): Observable<ProdutosModel> {
    return this.http.post<ProdutosModel>(`https://ap1cloud.azurewebsites.net/marcas/${idMarca}/produtos`, produtos);
  }
}
