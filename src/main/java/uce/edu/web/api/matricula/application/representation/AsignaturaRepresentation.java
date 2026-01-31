package uce.edu.web.api.matricula.application.representation;

import java.io.Serializable;
import java.util.List;

public class AsignaturaRepresentation implements Serializable {
    public Integer id;
    public String codigo;
    public String nombre;
    public Integer creditos;
    public Integer horasSemanales;
    public Integer nivel;
    public String descripcion;
    public List<LinkDto> links;
}
