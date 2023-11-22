
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ProdutosService } from '../services/produtos.service';
import { ProdutosModel } from '../model/produtos.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-produtos',
  templateUrl: './create-produtos.component.html',
  styleUrls: ['./create-produtos.component.css']
})
export class CreateProdutosComponent {

  produtos = new FormControl('', [Validators.required]);
  @Output() newProdutosEvent = new EventEmitter();
  @Input() idMarca:any = '';

  constructor(private produtosService: ProdutosService, private snackBar: MatSnackBar) {

  }

  public criarNovoProduto() {
    if (this.produtos.hasError("required")) {
      return;
    }

    let produtos: ProdutosModel = {
      nomeProduto: this.produtos.value as string,
      categoria: this.produtos.value as string,
      descricao: this.produtos.value as string,
      preco: this.produtos.value as string,
    };

    this.produtosService.createProdutos(this.idMarca, produtos).subscribe(response => {
      this.snackBar.open("Produto criado com sucesso", "Ok");
      this.newProdutosEvent.emit();
    });
  }

}


