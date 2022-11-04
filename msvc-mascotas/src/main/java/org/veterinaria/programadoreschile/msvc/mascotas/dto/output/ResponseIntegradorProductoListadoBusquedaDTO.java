package org.veterinaria.programadoreschile.msvc.mascotas.dto.output;

import java.math.BigDecimal;

public class ResponseIntegradorProductoListadoBusquedaDTO {

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPpu() {
        return ppu;
    }

    public void setPpu(String ppu) {
        this.ppu = ppu;
    }

    public int getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(int codModelo) {
        this.codModelo = codModelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getNomFamiliaCorp() {
        return nomFamiliaCorp;
    }

    public void setNomFamiliaCorp(String nomFamiliaCorp) {
        this.nomFamiliaCorp = nomFamiliaCorp;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public String getNomMarcaProducto() {
        return nomMarcaProducto;
    }

    public void setNomMarcaProducto(String nomMarcaProducto) {
        this.nomMarcaProducto = nomMarcaProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombreWeb() {
        return nombreWeb;
    }

    public void setNombreWeb(String nombreWeb) {
        this.nombreWeb = nombreWeb;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPrecioFormateado() {
        return precioFormateado;
    }

    public void setPrecioFormateado(String precioFormateado) {
        this.precioFormateado = precioFormateado;
    }

    public String vin;
    public String ppu;
    public int codModelo;
    public String marca;
    public String modelo;
    public int anioFabricacion;
    public String nomFamiliaCorp;
    public String codProducto;
    public String nomProducto;
    public String nomMarcaProducto;
    public int stock;
    public BigDecimal precio;
    public int codEmpresa;
    public String url;
    public String nombreWeb;
    public String partNumber;
    //precioFormateado: variable usada para formatear precio en miles
    public String precioFormateado;

    }
