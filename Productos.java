package com.example.kellyjohanazapataestrada.forcegym;

public class Productos
{
    private int imageId;
    private int precioP;
    private String nombreP, desP;

    public Productos(String nombreP, String desP, int precioP, int imageId) {
        this.nombreP = nombreP;
        this.desP = desP;
        this.precioP = precioP;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getPrecioP() {
        return precioP;
    }

    public void setPrecioP(int precioP) {
        this.precioP = precioP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDesP() {
        return desP;
    }

    public void setDesP(String desP) {
        this.desP = desP;
    }
}
