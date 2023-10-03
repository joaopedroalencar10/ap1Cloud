package ibmec.ap1.ap1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibmec.ap1.ap1.model.Produtos;
import ibmec.ap1.ap1.service.ProdutosService;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
     @Autowired
    ProdutosService produtosService;

    @GetMapping
    public ResponseEntity<List<Produtos>> getAll() {
        try {
            return new ResponseEntity<>(produtosService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Produtos> getById(@PathVariable("id") Long id) {
        Optional<Produtos> existingItemOptional = produtosService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{idMarca}")
    public ResponseEntity<Produtos> create(@PathVariable("idMarca") long idMarca, @RequestBody Produtos produtos) {
        try {
            Produtos result = this.produtosService.create(idMarca, produtos);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Produtos> update(@PathVariable("id") Long id, @RequestBody Produtos produtos) {
        try {
            Produtos result = this.produtosService.update(id, produtos);    
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            this.produtosService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
