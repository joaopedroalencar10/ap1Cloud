package ibmec.ap1.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibmec.ap1.ap1.model.Produtos;
import ibmec.ap1.ap1.repository.ProdutosRepository;
import ibmec.ap1.ap1.exception.MarcasException;
import ibmec.ap1.ap1.exception.ProdutosException;
import ibmec.ap1.ap1.model.Marcas;

@Service
public class ProdutosService {

        @Autowired
        ProdutosRepository repository;
    
        @Autowired 
        MarcasService marcasService;
    
        public List<Produtos> findAll() {
            return this.repository.findAll();
        }
    
        public Optional<Produtos> findById(long id) {
            return this.repository.findById(id);
        }
    
        public Produtos update(long id, Produtos newData) throws ProdutosException {
            Optional<Produtos> opProdutos = this.repository.findById(id);
    
            if (opProdutos.isPresent() == false) {
                throw new ProdutosException("Não encontrei o comentário a ser atualizado");
            }
    
            Produtos produtos = opProdutos.get();
    
            produtos.setNomeProduto(newData.getNomeProduto());
            produtos.setDescricao(newData.getDescricao());
            produtos.setCategoria(newData.getCategoria());
            produtos.setPreco(newData.getPreco());
    
            this.repository.save(produtos);
    
            return produtos;
        }
        
        public Produtos save(long idMarcas, Produtos item) throws ProdutosException, MarcasException {
            Optional<Marcas> opMarcas = this.marcasService.findById(idMarcas);
    
            if (opMarcas.isPresent() == false) {
                throw new ProdutosException("Produto não encontrado");
            }
    
            Marcas marcas = opMarcas.get();

            //Adiciona um novo produto na marca
            marcas.getProdutos().add(item);
            
            //Atualiza a marca com o novo produto
            this.marcasService.salvarNovoProduto(marcas);
           
            return item;
        }
    
        public void delete(long id) throws ProdutosException {
            Optional<Produtos> opMarcas = this.repository.findById(id);
    
            if (opMarcas.isPresent() == false) {
                throw new ProdutosException("Não encontrei o comentário a ser excluído");
            }
    
            this.repository.delete(opMarcas.get());
        }
        
    }

