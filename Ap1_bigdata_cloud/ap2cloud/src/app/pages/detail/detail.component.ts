import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Marcas} from 'src/app/model/marcas.model';
import { ProdutosService } from 'src/app/services/produtos.service';
import { MarcasService } from 'src/app/services/marcas.service';
import { ProdutosModel} from 'src/app/model/produtos.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})

export class DetailComponent implements OnInit {

  marcas?: Marcas;
  produtos?: ProdutosModel[];
  showCriarProdutos = false;

  constructor(private marcasService: MarcasService,
    private produtosService: ProdutosService,
    private route: ActivatedRoute) {

  }
  ngOnInit(): void {

    let idMarca = this.route.snapshot.params["idMarca"];
    this.marcasService.getMarcasbyId(idMarca).subscribe(response => {
      this.marcas = response;
      console.log(response);
    });

    this.carregaProduto();
  }

  private carregaProduto() {
    let idMarca = this.route.snapshot.params["idMarca"];
    this.produtosService.getProdutos(idMarca).subscribe(response => {
      this.produtos = response;
    });
  }

  public mostrarCriarProduto() {
    this.showCriarProdutos = true;
  }

  public atualizarProduto() {
    this.carregaProduto();
  }

}
