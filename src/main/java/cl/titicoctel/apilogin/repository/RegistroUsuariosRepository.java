package cl.titicoctel.apilogin.repository;

import cl.titicoctel.apilogin.model.Usuarios;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroUsuariosRepository extends JpaRepository<Usuarios, Long> {
    Usuarios save(Usuarios usuarios);
    List<Usuarios> findAll(Example example);
}
