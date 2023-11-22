import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProdutosComponent } from './create-produtos.component';

describe('CreateProdutosComponent', () => {
  let component: CreateProdutosComponent;
  let fixture: ComponentFixture<CreateProdutosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateProdutosComponent]
    });
    fixture = TestBed.createComponent(CreateProdutosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
