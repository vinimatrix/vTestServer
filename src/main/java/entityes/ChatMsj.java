/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vinicio.mendez
 */
@Entity
@Table(name = "chat_msj")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatMsj.findAll", query = "SELECT c FROM ChatMsj c"),
    @NamedQuery(name = "ChatMsj.findById", query = "SELECT c FROM ChatMsj c WHERE c.id = :id"),
    @NamedQuery(name = "ChatMsj.findByDate", query = "SELECT c FROM ChatMsj c WHERE c.date = :date"),
    @NamedQuery(name = "ChatMsj.findByMsj", query = "SELECT c FROM ChatMsj c WHERE c.msj = :msj"),
    @NamedQuery(name = "ChatMsj.findByUid", query = "SELECT c FROM ChatMsj c WHERE c.uid = :uid"),
    @NamedQuery(name = "ChatMsj.findLasts", query = "SELECT c FROM ChatMsj c WHERE c.date >= :date")})
public class ChatMsj implements Serializable {
    @Size(max = 255)
    @Column(name = "uid")
    private String uid;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 255)
    @Column(name = "Msj")
    private String msj;

    public ChatMsj() {
    }

    public ChatMsj(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
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
        if (!(object instanceof ChatMsj)) {
            return false;
        }
        ChatMsj other = (ChatMsj) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vinixworld.vtestserver.ChatMsj[ id=" + id + " ]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
}
