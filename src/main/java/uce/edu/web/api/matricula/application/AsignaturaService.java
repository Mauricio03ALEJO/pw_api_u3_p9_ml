package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Asignatura;
import uce.edu.web.api.matricula.infraestructure.AsignaturaRepository;
import uce.edu.web.api.matricula.application.representation.AsignaturaRepresentation;

@ApplicationScoped
public class AsignaturaService {

    @Inject
    private AsignaturaRepository asignaturaRepository;

    public List<AsignaturaRepresentation> listarTodas() {
        List<AsignaturaRepresentation> list = new ArrayList<>();
        for (Asignatura asig : this.asignaturaRepository.listAll()) {
            list.add(this.mapperToAR(asig));
        }
        return list;
    }

    public AsignaturaRepresentation consultarPorId(Integer id) {
        return this.mapperToAR(this.asignaturaRepository.findById(id.longValue()));
    }

    public AsignaturaRepresentation consultarPorCodigo(String codigo) {
        return this.mapperToAR(this.asignaturaRepository.findByCodigo(codigo));
    }

    public List<AsignaturaRepresentation> consultarPorNombre(String nombre) {
        List<AsignaturaRepresentation> list = new ArrayList<>();
        for (Asignatura asig : this.asignaturaRepository.findByNombre(nombre)) {
            list.add(this.mapperToAR(asig));
        }
        return list;
    }

    public List<AsignaturaRepresentation> consultarPorNivel(Integer nivel) {
        List<AsignaturaRepresentation> list = new ArrayList<>();
        for (Asignatura asig : this.asignaturaRepository.findByNivel(nivel)) {
            list.add(this.mapperToAR(asig));
        }
        return list;
    }

    @Transactional
    public void crear(AsignaturaRepresentation asig) {
        this.asignaturaRepository.persist(this.mapperToAsignatura(asig));
    }

    @Transactional
    public void actualizar(Integer id, AsignaturaRepresentation asig) {
        Asignatura asignatura = this.asignaturaRepository.findById(id.longValue());
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
    public void actualizarParcial(Integer id, AsignaturaRepresentation asig) {
        Asignatura asignatura = this.asignaturaRepository.findById(id.longValue());
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

    private AsignaturaRepresentation mapperToAR(Asignatura asig) {
        if (asig == null) return null;
        AsignaturaRepresentation ar = new AsignaturaRepresentation();
        ar.id = asig.id;
        ar.codigo = asig.codigo;
        ar.nombre = asig.nombre;
        ar.creditos = asig.creditos;
        ar.horasSemanales = asig.horasSemanales;
        ar.nivel = asig.nivel;
        ar.descripcion = asig.descripcion;
        return ar;
    }

    private Asignatura mapperToAsignatura(AsignaturaRepresentation ar) {
        Asignatura asig = new Asignatura();
        asig.id = ar.id;
        asig.codigo = ar.codigo;
        asig.nombre = ar.nombre;
        asig.creditos = ar.creditos;
        asig.horasSemanales = ar.horasSemanales;
        asig.nivel = ar.nivel;
        asig.descripcion = ar.descripcion;
        return asig;
    }
}
