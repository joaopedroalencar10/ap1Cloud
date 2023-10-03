package ibmec.ap1.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibmec.ap1.ap1.model.Produtos;
import ibmec.ap1.ap1.repository.ProdutosRepository;
import ibmec.ap1.ap1.model.Marcas;

@Service
public class ProdutosService {
    @Autowired
    ProdutosRepository produtosRepository;

    @Autowired
    MarcasService marcasService;

    public List<Produtos> findAll() {
        return this.produtosRepository.findAll();
    }

    public Optional<Produtos> findById(long id) {
        return this.produtosRepository.findById(id);
    }

    public Produtos create(long idMarcas, Produtos newProdutos) throws Exception {
        Optional<Marcas> opMarcas = this.marcasService.findById(idMarcas);

        if (opMarcas.isPresent() == false) {
            throw new Exception("Não encontrei a Marcas para adicionar o produto");
        }

        Marcas marcas = opMarcas.get();
        marcas.addProdutos(newProdutos);
        this.marcasService.saveProdutos(marcas);

        Produtos result = marcas.getProdutos().get(marcas.getProdutos().size() - 1);
        return result;
    }

    public Produtos update(long id, Produtos newData) throws Exception {
        Optional<Produtos> existingItemOptional = produtosRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new Exception("Não encontrei o Produtos a ser atualizado");

        Produtos existingItem = existingItemOptional.get();

        existingItem.setNomeProduto(newData.getNomeProduto());
        existingItem.setPreco(newData.getPreco());
        existingItem.setCategoria(newData.getCategoria());
        existingItem.setDescricao(newData.getDescricao());

        produtosRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws Exception {
        Optional<Produtos> produtos = this.produtosRepository.findById(id);

        if (produtos.isPresent() == false)
            throw new Exception("Não encontrei o Produtos a ser atualizado");

        this.produtosRepository.delete(produtos.get());
    }

}
