/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class Comunicacion {

    public static void SeñalPasarDia(RegistroJugador j, Vista.VistaINt log) {

        try {
            Socket s = new Socket(j.getIp(), j.getPuerto());

            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(new PasarDia(3, true));
            oos.close();
            os.close();
            s.close();

            log.EscribirLog("MEnsaje para PAsar Dia Enviado a " + j.getNombre());
        } catch (Exception e) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void EnviarJugada(Proovedor p, String Minorista, Vista.VistaINt log, int estado) {

        try {
            Socket s = new Socket(p.getIP(), p.getPort());
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(new Jugada(estado, 4, Minorista));
            oos.close();
            os.close();
            s.close();

            log.EscribirLog("Movimiento Enviado  ");
        } catch (Exception e) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void EntregarPaquete(Paquete p, Vista.VistaINt log) {

        try {
            Socket s = new Socket(p.getP().getIPSolicitante(), p.getP().getPuertoSolicitante());

            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(p);
            oos.close();
            os.close();
            s.close();

            log.EscribirLog("Paquete Entregado a: " + p.getP().getNombreSolicitante() + ",Cantidad: " + p.getInventario());
        } catch (Exception e) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void SolicitarPedido(Pedido p, Vista.VistaINt log) {

        try {
            Socket s = new Socket(p.getProovedor().getIP(), p.getProovedor().getPort());

            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(p);
            oos.close();
            os.close();
            s.close();
            log.EscribirLog("pedido  Solicitado al Provedor");
        } catch (Exception e) {
            log.EscribirLog("No Se Puedo Enviar Pedido");
        } finally {

        }
    }

    public static void RegistrarJugador(RegistroJugador j, Vista.VistaINt log, String host, int port) {

        try {
            Socket s = new Socket(host, port);

            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(j);
            oos.close();
            os.close();
            s.close();
            log.EscribirLog("Registrar minorista");
        } catch (IOException e) {
            log.EscribirLog("No Se Pudo registrar:" + e.getMessage());
        } finally {

        }
    }

    public static void EnviarRespuestaRegistro(RegistroJugador j, Vista.VistaINt log, boolean res, String ip, int port, String nombre) {

        try {
            ValidarRegistro v = new ValidarRegistro(res, 5);
            v.ip = ip;
            v.port = port;
            v.nombre = nombre;
            System.out.println("NOMBRE!!" + nombre);
            Socket s = new Socket(j.getIp(), j.getPuerto());
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(v);
            oos.close();
            os.close();
            s.close();
            log.EscribirLog(res ? "Registrado minorista :" + j.Nombre : "No se Registro minorista :" + j.Nombre);
        } catch (IOException e) {
            log.EscribirLog("No Se Pudo registrar");
        }
    }

}
