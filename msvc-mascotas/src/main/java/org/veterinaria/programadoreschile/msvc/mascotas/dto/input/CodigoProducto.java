package org.veterinaria.programadoreschile.msvc.mascotas.dto.input;

import javax.validation.constraints.Pattern;

public class CodigoProducto {

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    @Pattern(regexp ="^[a-zA-Z0-9]*$", message = "{codigoPartner.alfanumerico}")
    public String codigoProducto;
}
