/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrador
 */
public class Listener extends Thread {

    ServerSocket listener;

    BaseJugador jugador;

    public Listener(BaseJugador j) throws IOException {

        this.jugador = j;

        this.start();

    }

    public BaseJugador getJugador() {
        return jugador;
    }

    public void setJugador(BaseJugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void run() {
        try {
            listener = new ServerSocket(getJugador().getPuerto());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El Puerto Ya Esta En Uso, La Aplicacion se Cerrara");
            System.exit(0);
        }
        while (true) {
            Socket socket = null;

            try {
                socket = listener.accept();
                
                System.out.println("conexion");
                ServerThread st = new ServerThread(socket,getJugador());
                st.start();

            } catch (Exception ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }
}
