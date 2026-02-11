import { Routes } from '@angular/router';
import { BeneficioListaComponent } from './components/beneficio-lista/beneficio-lista.component';
import { TransferenciaFormComponent } from './components/transferencia-form/transferencia-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'beneficios', pathMatch: 'full' },
  { path: 'beneficios', component: BeneficioListaComponent },
  { path: 'transferir', component: TransferenciaFormComponent }
];