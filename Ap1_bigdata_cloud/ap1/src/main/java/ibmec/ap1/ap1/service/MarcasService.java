package ibmec.ap1.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ibmec.ap1.ap1.exception.MarcasException;
import ibmec.ap1.ap1.model.Marcas;
import ibmec.ap1.ap1.repository.MarcasRepository;

@Service
public class MarcasService {
 @Autowired
    MarcasRepository marcasRepository;

    @Autowired
    private AzureStorageAccountService azureStorageAccountService;

    public List<Marcas> findAll() {
        return this.marcasRepository.findAll();
    }

    public Optional<Marcas> findById(long id) {
        return this.marcasRepository.findById(id);
    }

    public List<Marcas> getAll() {
        return this.marcasRepository.findAll();
    }

    public void saveOrUpdate(Marcas item) {
        this.marcasRepository.save(item);
    }

    public Marcas create(Marcas marcas) {

        if (marcas.geturlFoto() == null) {
            marcas.seturlFoto("https://dummyimage.com/300");
        }

        return this.marcasRepository.save(marcas);
    }

    public Marcas salvarNovoProduto(Marcas newData) 
    {
        return this.marcasRepository.save(newData);
    }

    public Marcas update(long id, Marcas newData) throws MarcasException{
        Optional<Marcas> existingItemOptional = marcasRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new MarcasException("Não encontrei o marcas a ser atualizado");

        Marcas existingItem = existingItemOptional.get();

        existingItem.setNomeMarca(newData.getNomeMarca());
        existingItem.setPais(newData.getPais());
        existingItem.setContato(newData.getContato());
        existingItem.setSite(newData.getSite());
        marcasRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws MarcasException {
        Optional<Marcas> endereco = this.marcasRepository.findById(id);

        if (endereco.isPresent() == false)
            throw new MarcasException("Não encontrei o endereco a ser atualizado");

        this.marcasRepository.delete(endereco.get());
    }

    public void saveComentario(Marcas marcas) {
        this.marcasRepository.save(marcas);
    }

    public void uploadFileToMarcas(MultipartFile file, long id) throws MarcasException, Exception {
        
        Optional<Marcas> opMarcas = this.marcasRepository.findById(id);
        
        if (opMarcas.isPresent() == false) {
            throw new MarcasException("Não encontrei o marcas a ser atualizado");
        }

        Marcas marcas = opMarcas.get();
        String ulrImage = this.azureStorageAccountService.uploadFileToAzure(file);
        marcas.seturlFoto(ulrImage);
        this.marcasRepository.save(marcas);
    }

}
