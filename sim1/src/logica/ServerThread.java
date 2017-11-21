/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServerThread extends Thread {

    String line = null;
    BufferedReader is = null;
    PrintWriter os = null;
    BaseJugador jugador;
    Socket s = null;

    public ServerThread(Socket s, BaseJugador jugador) {
        this.s = s;
        this.jugador = jugador;
    }

    public void run() {
        try {
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            jugador.getLog().EscribirLog("MensajeRecibido");
            Paquete_envio tipo = (Paquete_envio) ois.readObject();
            if (tipo.getTipo() == 0) {
                Pedido p = (Pedido) tipo;

                jugador.RecibirPedido(p);
                jugador.getLog().EscribirLog("Pedido Recibido De: " + p.getNombreSolicitante() + ",Cantidad : " + p.getCantidad());
            }
            if (tipo.getTipo() == 1) {
                Paquete p = (Paquete) tipo;
                System.out.println("Paquete Recibido,Cantidad " + p.getInventario() + "Precio :" + p.getPrecio());
                jugador.RecibirPaquete(p);
                jugador.getLog().EscribirLog("Paquete Recibido De: " + p.getP().getNombre_Proveed() + ",Cantidad" + p.getInventario() + "Precio :" + p.getPrecio());

            }

            if (tipo.getTipo() == 2) {
                RegistroJugador r = (RegistroJugador) tipo;
                jugador.getLog().EscribirLog("Registro Recibido de " + r.getNombre());
                (  jugador).RecibirMinorista(r);

            }

            if (tipo.getTipo() == 3) {
                PasarDia r = (PasarDia) tipo;
                jugador.getLog().EscribirLog("AvanzarDia");
                this.jugador.Iterar();

            }

            if (tipo.getTipo() == 4) {
                Jugada j = (Jugada) tipo;
                jugador.getLog().EscribirLog("Movimiento Recibida De " + j.Nombre);
                (  jugador).RecibirJugada(j);
            }

            if (tipo.getTipo() == 5) {
                ValidarRegistro j = (ValidarRegistro) tipo;
                jugador.getLog().EscribirLog("Validacion Recibida ");
                jugador.getLog().EscribirLog(j.res ? "Registrado Exitosamente" : "Fallo Al Registar");
                (  jugador).Registrar(j);

            }

        } catch (IOException e) {

            line = this.getName(); //reused String line for getting thread name
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, e);
        } catch (NullPointerException e) {
            line = this.getName(); //reused String line for getting thread name
            System.out.println("Client ass " + line + " Closed"+e.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (is != null) {
                    is.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if (os != null) {
                    os.close();
                    System.out.println("Socket Out Closed");
                }
                if (s != null) {
                    s.close();
                    System.out.println("Socket Closed");
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }//end finally
    }
}
