/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metificar;

/**
 *
 * @author Dream7ouch
 */
public class Articulo {
    private String codigo_barras;
    private String descripcion;
    private String precio;
    private String peso;
    private int cantidad = 0;

    public Articulo(String codigo_barras, String descripcion, String precio, String peso,int cantidad) {
        this.codigo_barras = codigo_barras;
        this.descripcion = descripcion;
        this.precio = precio;
        this.peso = peso;
        this.cantidad = cantidad;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getPeso() {
        return peso;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
