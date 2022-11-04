package org.veterinaria.programadoreschile.msvc.mascotas.dto.input;

//import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PatenteVinDTO {
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

  //  @Schema(description = "vin")
    //@Size(min = 3, max = 17,  message = "{vin.size}")
    @Pattern(regexp ="^[a-zA-Z0-9]*$", message = "{vin.alfanumerico}")
    private String vin;
    //@Schema(description = "patente")
    //@Size(min = 3, max = 17,  message = "{patente.size}")
    @Pattern(regexp ="^[a-zA-Z0-9]*$", message = "{patente.alfanumerico}")
    private String patente;
}
