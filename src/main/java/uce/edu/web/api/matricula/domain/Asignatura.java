package uce.edu.web.api.matricula.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.persistence.Column;

@XmlRootElement
@Entity
@Table(name = "Asignatura")
@SequenceGenerator(name = "asignatura_seq", sequenceName = "asignatura_secuencia", allocationSize = 1)
public class Asignatura extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asignatura_seq")
    public Integer id;
    
    @Column(unique = true, nullable = false)
    public String codigo;
    
    @Column(nullable = false)
    public String nombre;
    
    public Integer creditos;
    
    public Integer horasSemanales;
    
    public Integer nivel;
    
    public String descripcion;
}
