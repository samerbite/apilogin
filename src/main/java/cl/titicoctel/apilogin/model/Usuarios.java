package cl.titicoctel.apilogin.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String correo;
    private String password;
    @CreationTimestamp
    private Timestamp fechaCreacion;
    private Timestamp ultimoLogin;

    public Usuarios(){}
}
