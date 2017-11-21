/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author pc
 */
public class RegistroJugador extends Paquete_envio {

    String Nombre;
    String Ip;
    int Puerto;
    String TipoEntidad;

    public RegistroJugador(String Nombre, int tipo, String ip, int puerto, String TipoEntidad) {
        this.Nombre = Nombre;
        this.Tipo = 2;
        this.TipoEntidad = TipoEntidad;

        this.Ip = ip;
        this.Puerto = puerto;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public int getPuerto() {
        return Puerto;
    }

    public void setPuerto(int Puerto) {
        this.Puerto = Puerto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "nombre" + this.Nombre + "ip" + this.getIp() + "puerto" + this.Puerto; //To change body of generated methods, choose Tools | Templates.

    }

}
