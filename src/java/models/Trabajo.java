/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author kecc
 */
@Entity
public class Trabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Propuesta propuesta;
    
    private String rutaDocFinal;
    @Column(name = "fecha_presentacion")
    private Date fechaPresentacion;
    
    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
    
    private String director;
    
    private int estado;

    public Trabajo() {
    }

    public Trabajo(Propuesta propuesta) {
        this.propuesta = propuesta;
        this.fechaPresentacion = new Date(System.currentTimeMillis());
        this.fechaVencimiento = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(365));
        this.estado = 1;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Propuesta getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(Propuesta propuesta) {
        this.propuesta = propuesta;
    }

    public String getRutaDocFinal() {
        return rutaDocFinal;
    }

    public void setRutaDocFinal(String rutaDocFinal) {
        this.rutaDocFinal = rutaDocFinal;
    }

    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
        if (!(object instanceof Trabajo)) {
            return false;
        }
        Trabajo other = (Trabajo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Trabajo[ id=" + id + " ]";
    }
    
}
