import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Beneficio, TransferRequest } from '../models/beneficio.model';

@Injectable({
  providedIn: 'root'
})
export class BeneficioService {
  private readonly API_BENEFICIOS = 'http://localhost:8082/api/v1/beneficios';
  private readonly API_TRANSFERENCIA = 'http://localhost:8082/api/v1/transferencias';

  constructor(private http: HttpClient) {}

  listar(): Observable<Beneficio[]> {
    return this.http.get<Beneficio[]>(this.API_BENEFICIOS);
  }

  salvar(beneficio: Beneficio): Observable<Beneficio> {
    return this.http.post<Beneficio>(this.API_BENEFICIOS, beneficio);
  }

  transferir(request: TransferRequest): Observable<void> {
    return this.http.post<void>(this.API_TRANSFERENCIA, request);
  }
}