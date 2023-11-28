package ibmec.ap1.ap1.model;


import com.azure.core.annotation.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "produtos")
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @NotBlank(message = "O campo nome não pode ser vazio")
    private String nomeProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Column(nullable = false)
    @NotBlank(message = "O campo preco não pode ser vazio")
    private String preco;

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
     
  
    @Column(nullable = false)
    @NotBlank(message = "O campo categoria não pode ser vazio")
    private String categoria;

     public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(nullable = false)
    @NotBlank(message = "O campo descricao não pode ser vazio")
    private String descricao;

    @ManyToOne
    @JsonIgnore
    private Marcas marcas;

    public Marcas getMarcas() {
        return marcas;
    }

    public void setMarcas(Marcas marcas) {
        this.marcas = marcas;
    }



  
}
