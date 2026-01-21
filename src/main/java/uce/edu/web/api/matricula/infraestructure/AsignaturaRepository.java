package uce.edu.web.api.matricula.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Asignatura;

import java.util.List;

@ApplicationScoped
public class AsignaturaRepository implements PanacheRepository<Asignatura> {

    public Asignatura findByCodigo(String codigo) {
        return find("codigo", codigo).firstResult();
    }

    public List<Asignatura> findByNombre(String nombre) {
        return list("LOWER(nombre) LIKE LOWER(?1)", "%" + nombre + "%");
    }

    public List<Asignatura> findByNivel(Integer nivel) {
        return list("nivel", nivel);
    }

    public Long deleteByCodigo(String codigo) {
        return delete("codigo", codigo);
    }
}
