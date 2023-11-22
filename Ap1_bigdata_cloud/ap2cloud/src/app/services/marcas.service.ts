import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Marcas } from '../model/marcas.model';

@Injectable({
  providedIn: 'root'
})
export class MarcasService {

  constructor(private httpClient: HttpClient) { }

public getMarcas() : Observable<Marcas[]> {
  return this.httpClient.get<Marcas[]>("https://ap1cloud.azurewebsites.net/marcas")
}


public getMarcasbyId(id : number) : Observable<Marcas> {
  return this.httpClient.get<Marcas>("https://ap1cloud.azurewebsites.net/marcas/" + id)
}
}
