import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BeneficioService } from '../../services/beneficio.service';
import { Beneficio } from '../../models/beneficio.model';

@Component({
  selector: 'app-beneficio-lista',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './beneficio-lista.component.html',
  styleUrl: './beneficio-lista.component.scss'
})
export class BeneficioListaComponent implements OnInit {
  beneficios: Beneficio[] = [];

  // modal edição
  editando = false;
  beneficioEdit: Beneficio | null = null;

  constructor(private beneficioService: BeneficioService) {}

  ngOnInit(): void {
    this.carregarBeneficios();
  }

  carregarBeneficios(): void {
    this.beneficioService.listar().subscribe({
      next: (dados) => (this.beneficios = dados),
      error: (err) => console.error('Erro ao buscar benefícios:', err),
    });
  }

  confirmarExclusao(b: Beneficio): void {
    const ok = confirm(`Excluir o benefício "${b.nome}"? Essa ação não pode ser desfeita.`);
    if (!ok) return;

    this.beneficioService.excluir(Number(b.id)).subscribe({
      next: () => {
        // atualiza a tela sem refetch (mais rápido)
        this.beneficios = this.beneficios.filter(x => x.id !== b.id);
      },
      error: (err) => console.error('Erro ao excluir benefício:', err),
    });
  }

  abrirEdicao(b: Beneficio): void {
    // clona pra não editar a lista “ao vivo” antes de salvar
    this.beneficioEdit = { ...b };
    this.editando = true;
  }

  cancelarEdicao(): void {
    this.editando = false;
    this.beneficioEdit = null;
  }

  

  salvarEdicao(): void {
    if (!this.beneficioEdit) return;

    const id = Number(this.beneficioEdit.id);

    this.beneficioService.atualizar(id, this.beneficioEdit).subscribe({
      next: (atualizado) => {
        // substitui na lista
        this.beneficios = this.beneficios.map(x => x.id === atualizado.id ? atualizado : x);
        this.cancelarEdicao();
      },
      error: (err) => console.error('Erro ao atualizar benefício:', err),
    });
  }
}
