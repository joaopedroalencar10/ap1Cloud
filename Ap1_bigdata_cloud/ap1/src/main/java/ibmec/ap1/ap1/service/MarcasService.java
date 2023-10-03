package ibmec.ap1.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibmec.ap1.ap1.model.Marcas;
import ibmec.ap1.ap1.repository.MarcasRepository;

@Service
public class MarcasService {

    @Autowired
    private MarcasRepository _marcasRepository;

    public List<Marcas> findAll() {
        return this._marcasRepository.findAll();
    }

    public Optional<Marcas> findById(long id) {
        return this._marcasRepository.findById(id);
    }

    public Marcas save(Marcas marcas) throws Exception {
        if (this._marcasRepository.countByNomeMarca(marcas.getNomeMarca()) > 0) {
            throw new Exception("Este nome já existe na base de dados");
        }
        this._marcasRepository.save(marcas);
        return marcas;
    }

    public Marcas update(long id, Marcas newData) throws Exception {
        Optional<Marcas> result = this._marcasRepository.findById(id);

        if (result.isPresent() == false) {
            throw new Exception("Não encontrei a Marcas a ser atualizada");
        }

        Marcas marcasASerAtualizada = result.get();
        marcasASerAtualizada.setNomeMarca(newData.getNomeMarca());
        marcasASerAtualizada.setPais(newData.getPais());
        marcasASerAtualizada.setContato(newData.getContato());
         marcasASerAtualizada.setSite(newData.getSite());
        this._marcasRepository.save(marcasASerAtualizada);
        return marcasASerAtualizada;
    }

    public void delete(long id) throws Exception {
        Optional<Marcas> marcasASerExcluida = this._marcasRepository.findById(id);
        // Não achei as Marcas a ser excluida
        if (marcasASerExcluida.isPresent() == false) {
            throw new Exception("Não encontrei a Marcas a ser atualizada");
        }
        this._marcasRepository.delete(marcasASerExcluida.get());
    }

    public void saveProdutos(Marcas marcas) {
        this._marcasRepository.save(marcas);
    }

}
