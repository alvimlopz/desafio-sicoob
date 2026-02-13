import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BeneficioService } from '../../services/beneficio.service';
import { Beneficio, TransferRequest } from '../../models/beneficio.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transferencia-form',
  standalone: true,
  imports: [CommonModule, FormsModule], 
  templateUrl: './transferencia-form.component.html',
  styleUrl: './transferencia-form.component.scss'
})
export class TransferenciaFormComponent implements OnInit {
  beneficios: Beneficio[] = [];
  
  transferencia: TransferRequest = {
    fromId: 0,
    toId: 0,
    amount: 0
  };

  constructor(
    private beneficioService: BeneficioService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.carregarBeneficios();
  }

  carregarBeneficios(): void {
    this.beneficioService.listar().subscribe({
      next: (dados) => this.beneficios = dados,
      error: (err) => console.error('Erro ao carregar combo de benefícios', err)
    });
  }

  voltar(): void {
    this.router.navigate(['/beneficios']);
  }

  cancelar(): void {
    this.router.navigate(['/beneficios']);
  }

  executar(): void {
    if (this.transferencia.fromId === this.transferencia.toId) {
      alert('O benefício de origem não pode ser o mesmo de destino.');
      return;
    }

    this.beneficioService.transferir(this.transferencia).subscribe({
      next: () => {
        alert('Transferência realizada com sucesso!');
        this.router.navigate(['/beneficios']);
      },
      error: (err) => {
        console.error('Erro na transferência:', err);
        alert('Falha ao realizar transferência. Verifique o saldo ou os logs do JBoss.');
      }
    });
  }
}