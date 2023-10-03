package ibmec.ap1.ap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ibmec.ap1.ap1.model.Marcas;

@Repository
public interface MarcasRepository extends JpaRepository<Marcas, Long> {

    int countByNomeMarca(String nomeMarca);
    
}
