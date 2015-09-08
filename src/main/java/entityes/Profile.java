/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vinicio.mendez
 */
@Entity
@Table(name = "profile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p"),
    @NamedQuery(name = "Profile.findById", query = "SELECT p FROM Profile p WHERE p.id = :id"),
    @NamedQuery(name = "Profile.findByUid", query = "SELECT p FROM Profile p WHERE p.uid = :uid"),
    @NamedQuery(name = "Profile.findByNombre", query = "SELECT p FROM Profile p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Profile.findByPais", query = "SELECT p FROM Profile p WHERE p.pais = :pais"),
    @NamedQuery(name = "Profile.findByCiudad", query = "SELECT p FROM Profile p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Profile.findByDireccion", query = "SELECT p FROM Profile p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Profile.findBySexo", query = "SELECT p FROM Profile p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Profile.findByRelacion", query = "SELECT p FROM Profile p WHERE p.relacion = :relacion")})
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "uid")
    private Integer uid;
    @Size(max = 255)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "Pais")
    private String pais;
    @Size(max = 255)
    @Column(name = "Ciudad")
    private String ciudad;
    @Size(max = 255)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 255)
    @Column(name = "Sexo")
    private String sexo;
    @Size(max = 255)
    @Column(name = "Relacion")
    private String relacion;

    public Profile() {
    }

    public Profile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
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
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vinixworld.vtestserver.Profile[ id=" + id + " ]";
    }
    
}
