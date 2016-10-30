package com.example.kellyjohanazapataestrada.forcegym;

public class Usuario
{
    private String Nombre, Pass, Repass, Email, IMC, Categoria;
    private String Estatura, Peso, id;

    public Usuario()
    {
    }

    public Usuario(String nombre, String pass, String repass, String email, String IMC, String categoria, String estatura, String peso, String id) {
        Nombre = nombre;
        Pass = pass;
        Repass = repass;
        Email = email;
        this.IMC = IMC;
        Categoria = categoria;
        Estatura = estatura;
        Peso = peso;
        this.id = id;
    }

    public String getIMC() {
        return IMC;
    }

    public void setIMC(String IMC) {
        this.IMC = IMC;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getRepass() {
        return Repass;
    }

    public void setRepass(String repass) {
        Repass = repass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEstatura() {
        return Estatura;
    }

    public void setEstatura(String estatura) {
        Estatura = estatura;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

