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

import ibmec.ap1.ap1.model.Marcas;
import ibmec.ap1.ap1.service.MarcasService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/marcas")
public class MarcasController {
    
    @Autowired
    private MarcasService _marcasService;

    @GetMapping
    public ResponseEntity<List<Marcas>> getAll() {
        try {
            return new ResponseEntity<>(this._marcasService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Marcas> create(@Valid @RequestBody Marcas item) {
        try {
            Marcas result = this._marcasService.save(item);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Marcas> getById(@PathVariable("id") long id) {

        Optional<Marcas> result = this._marcasService.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } 
            
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PutMapping("{id}")
    public ResponseEntity<Marcas> update(@PathVariable("id") long id, @RequestBody Marcas marcasNovosDados) {
        try {
            Marcas result = this._marcasService.update(id, marcasNovosDados);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            this._marcasService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
