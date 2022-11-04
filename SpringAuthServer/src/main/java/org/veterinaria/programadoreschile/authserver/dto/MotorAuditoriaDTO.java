package org.veterinaria.programadoreschile.authserver.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class MotorAuditoriaDTO {

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

    private Integer IdMotorAuditoria;


    private LocalDateTime registro;


    private boolean estado;

    @NotNull
    @Size(min = 5, message = "{descripcion.size}")
    private String descripcion;

    @NotNull
    @Size(min = 5, max = 150, message = "{tabla.size}")
    private String tabla;

    @NotNull
    @Size(min = 3, message = "{usuario.size}")
    private String usuario;
}
