package metificar;

public class Articulo {
    private String descripcion;
    private double precioUnitario;
    private long codigoDeBarras;
    private int cantidad;
    private double peso;

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public Articulo() {
    }

    
    public Articulo(String descripcion, double precioUnitario, long codigoDeBarras, int cantidad) {
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.codigoDeBarras = codigoDeBarras;
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public long getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(long codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
  
}
