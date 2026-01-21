package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Asignatura;
import uce.edu.web.api.matricula.infraestructure.AsignaturaRepository;

@ApplicationScoped
public class AsignaturaService {

    @Inject
    private AsignaturaRepository asignaturaRepository;

    public List<Asignatura> listarTodas() {
        return this.asignaturaRepository.listAll();
    }

    public Asignatura consultarPorId(Integer id) {
        return this.asignaturaRepository.findById(id.longValue());
    }

    public Asignatura consultarPorCodigo(String codigo) {
        return this.asignaturaRepository.findByCodigo(codigo);
    }

    public List<Asignatura> consultarPorNombre(String nombre) {
        return this.asignaturaRepository.findByNombre(nombre);
    }

    public List<Asignatura> consultarPorNivel(Integer nivel) {
        return this.asignaturaRepository.findByNivel(nivel);
    }

    @Transactional
    public void crear(Asignatura asignatura) {
        this.asignaturaRepository.persist(asignatura);
    }

    @Transactional
    public void actualizar(Integer id, Asignatura asig) {
        Asignatura asignatura = this.consultarPorId(id);
        if (asignatura != null) {
            asignatura.codigo = asig.codigo;
            asignatura.nombre = asig.nombre;
            asignatura.creditos = asig.creditos;
            asignatura.horasSemanales = asig.horasSemanales;
            asignatura.nivel = asig.nivel;
            asignatura.descripcion = asig.descripcion;
        }
    }

    @Transactional
    public void actualizarParcial(Integer id, Asignatura asig) {
        Asignatura asignatura = this.consultarPorId(id);
        if (asignatura != null) {
            if (asig.codigo != null) {
                asignatura.codigo = asig.codigo;
            }
            if (asig.nombre != null) {
                asignatura.nombre = asig.nombre;
            }
            if (asig.creditos != null) {
                asignatura.creditos = asig.creditos;
            }
            if (asig.horasSemanales != null) {
                asignatura.horasSemanales = asig.horasSemanales;
            }
            if (asig.nivel != null) {
                asignatura.nivel = asig.nivel;
            }
            if (asig.descripcion != null) {
                asignatura.descripcion = asig.descripcion;
            }
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.asignaturaRepository.deleteById(id.longValue());
    }

    @Transactional
    public Long eliminarPorCodigo(String codigo) {
        return this.asignaturaRepository.deleteByCodigo(codigo);
    }
}
