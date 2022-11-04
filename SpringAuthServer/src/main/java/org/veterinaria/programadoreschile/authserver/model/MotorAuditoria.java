package org.veterinaria.programadoreschile.authserver.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MotorAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMotorAuditoria")
    private Integer IdMotorAuditoria;

    @Column(name = "registro", insertable = false, updatable = false)
    private LocalDateTime registro;

    @Column(name = "estado", insertable = false, updatable = false)
    private boolean estado;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "tabla", length = 100)
    private String tabla;

    @Column(name = "usuario", length = 50)
    private String usuario;

    public Integer getIdMotorAuditoria() {
        return IdMotorAuditoria;
    }

    public void setIdMotorAuditoria(Integer idMotorAuditoria) {
        IdMotorAuditoria = idMotorAuditoria;
    }

    public LocalDateTime getRegistro() {
        return registro;
    }

    public void setRegistro(LocalDateTime registro) {
        this.registro = registro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


}
