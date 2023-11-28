package ibmec.ap1.ap1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibmec.ap1.ap1.exception.MarcasException;
import ibmec.ap1.ap1.model.Marcas;
import ibmec.ap1.ap1.service.MarcasService;


@RestController
@RequestMapping("/marcas")
@CrossOrigin
class MarcasController {

    @Autowired
    MarcasService marcasService;

    @GetMapping
    public ResponseEntity<List<Marcas>> getAll() {
        try {
            List<Marcas> items = new ArrayList<Marcas>();

            marcasService.getAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Marcas> getById(@PathVariable("id") long id) {
        Optional<Marcas> existingItemOptional = marcasService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Marcas> create(@RequestBody Marcas item) throws MarcasException {
        Marcas savedItem = marcasService.create(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Marcas> update(@PathVariable("id") long id, @RequestBody Marcas item) throws MarcasException {
        return new ResponseEntity<>(marcasService.update(id, item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws MarcasException {
        marcasService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}")
    public ResponseEntity<String> uploadMarcasImage(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) throws MarcasException, Exception {
        marcasService.uploadFileToMarcas(file, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}