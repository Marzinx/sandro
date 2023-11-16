/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author JcarlosAd7
 */
public class Propiedad {
    private int id;
    private String nombre;
    private String direccion;
    private Double precioAlquiler;
    private String caracteristicas;
    private boolean activo;
    
    //Constructores
    public Propiedad() {
        
    }

    public Propiedad(int id, String nombre, String direccion, Double precioAlquiler, String caracteristicas, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.precioAlquiler = precioAlquiler;
        this.caracteristicas = caracteristicas;
        this.activo = activo;
    }
    
   
    
    //Setter y getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return caracteristicas;
    }

    public void setDescripcion(String descripcion) {
        this.caracteristicas = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
     public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(Double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    //Método toString
    @Override
    public String toString() {
        return "Propiedad{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", precioAlquiler=" + precioAlquiler + ", caracteristicas=" + caracteristicas + ", activo=" + activo + '}';
    }
    
    
    
}
