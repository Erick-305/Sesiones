package org.erick.ManejodeSesiones.models;

public class Articulo {
    private Long idarticulo;
    private Long idcategoria;
    private String codigo;
    private String nombre;
    private double stock;
    private String descripcion;
    private String imagen;
    private int condicion;

    public Articulo() {
    }

    public Articulo(Long idarticulo, Long idcategoria, String codigo, String nombre, double stock, String descripcion, String imagen, int condicion) {
        this.idarticulo = idarticulo;
        this.idcategoria = idcategoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.condicion = condicion;
    }

    public Long getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Long idarticulo) {
        this.idarticulo = idarticulo;
    }

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }
}