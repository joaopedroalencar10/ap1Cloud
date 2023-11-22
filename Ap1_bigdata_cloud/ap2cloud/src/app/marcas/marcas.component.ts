import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { MarcasService } from '../services/marcas.service';
import { Marcas } from '../model/marcas.model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-post',
  templateUrl: './marcas.component.html',
  styleUrls: ['./marcas.component.css']
})
export class MarcasComponent  implements OnInit{
  posts: Marcas[]= [];

  constructor(private marcasService: MarcasService, private router: Router) {

  }

  ngOnInit(): void {
    this.marcasService.getMarcas().subscribe(response => {
      this.posts = response;
    });
  }

  redirectToDetail(id: any) {
      this.router.navigate(["detail", id]);
  }


 /* ngBeforeViewInit(): void {

  }

  ngAfterViewInit(): void {

  }

  ngChanges(): void {

  }

  ngOnDestroy(): void {

  }
*/
}

