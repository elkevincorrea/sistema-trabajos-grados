/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author kecc
 */
@Entity
public class Propuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tematica;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Estudiante> estudiantes;
    
    private String rutaPropuesta;
    
    private Integer modalidad;
    
    /**
     * 1 - En espera
     * 2 - Corrección
     * 3 - Aprobada
     * 4 - Rechazada
     */
    private Integer estado;
    
    public Propuesta(){        
    }

    public Propuesta(String tematica, List<Estudiante> estudiantes, int modalidad) {
        this.tematica = tematica;
        this.estudiantes = estudiantes;
        this.modalidad = modalidad;
        this.estado = 1;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getRutaPropuesta() {
        return rutaPropuesta;
    }

    public void setRutaPropuesta(String rutaPropuesta) {
        this.rutaPropuesta = rutaPropuesta;
    }

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propuesta)) {
            return false;
        }
        Propuesta other = (Propuesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Propuesta[ id=" + id + " ]";
    }
    
}
