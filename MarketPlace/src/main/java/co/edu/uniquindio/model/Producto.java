package co.edu.uniquindio.model;

public class Producto {

    private String nombre;
    private String imagen;
    private String categoria;
    private double precio;
    private Estado estado;

    public Producto() {
    }

    public Producto(String nombre, String imagen, String categoria, double precio, Estado estado) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", estado=" + estado +
                '}';
    }
}
