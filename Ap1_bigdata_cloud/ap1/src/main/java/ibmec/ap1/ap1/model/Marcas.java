package ibmec.ap1.ap1.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "marcas")
public class Marcas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length = 200, nullable = false)
    @NotBlank(message = "O campo nome não pode ser vazio")
    private String nomeMarca;

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }


    @Column(nullable = true)
    private String urlFoto;

    public String geturlFoto() {
        return urlFoto;
    }

    public void seturlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }


    @Column(length = 200, nullable = false)
    @NotBlank(message = "O campo país não pode ser vazio")
    private String pais;

    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Column(length = 200, nullable = false)
    @NotBlank(message = "O campo contato não pode ser vazio")
    private String contato;
    
 
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Column(length = 200, nullable = false)
     @NotBlank(message = "O campo site não pode ser vazio")
    private String site;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "marcas_id")
    private List<Produtos> produtos;

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public void addProdutos(Produtos Produtos){
        this.produtos.add(Produtos);
    }

  
}