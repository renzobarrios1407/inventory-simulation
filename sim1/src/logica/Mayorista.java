/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Vista.VistaINt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author pc
 */
public class Mayorista extends BaseJugador {

    PedidoMayorista p;
    ArrayList<Jugada> jugadas = new ArrayList();

    @Override
    public void setTiempoEspera(int tiempoEspera) {
        super.setTiempoEspera(tiempoEspera); //To change body of generated methods, choose Tools | Templates.
    }

    public Mayorista(VistaINt log,
            String JugadorNombre,
            int tiempoEspera,
            boolean esperando,
            int InventariadoAcutal,
            int inventariominimo,
            int Pedido,
            int PrecioDeVentaUnidad,
            String ip,
            int Puerto)
            throws IOException {
        super(log, JugadorNombre, tiempoEspera, esperando, InventariadoAcutal, inventariominimo, PrecioDeVentaUnidad, ip, Puerto);
        tiempoEspera = 3;
    }

    @Override
    public void RecibirPedido(logica.Pedido p) {
        ListaPedidos.add(p);
    }

    @Override
    public void RecibirPaquete(Paquete p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void PasarDia() {
        log.EscribirLog("Enviando Señal para Pasar el  Dia a los Minoristas");
        for (int i = 0; i < minoristas.size(); i++) {
            RegistroJugador m = minoristas.get(i);

            Comunicacion.SeñalPasarDia(m, log);

        }
    }

    @Override
    public void Iterar() {
        super.Iterar();
        log.ControlDias(false);
        inventariominimo=log.GetPuntodeReorden();
        if (InventariadoAcutal < inventariominimo) {
            log.EscribirLog("Inventario Por debajo del limite Inferior : Procediendo a  Solicitar Pedido");
            HacerPedido();

        }
        if (p != null) {
            p.tiempoEspera--;
            if (p.tiempoEspera <= 0) {
                InventariadoAcutal += p.cantidad;
                p = null;
            }
        }
        AtenderPedido();
        RestarDias();
        PasarDia();
        for (Jugada jugada : jugadas) {
            jugada.Estado = 0;
        }

    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        int cont;
        String Vista;
        String Vista2;
        while (true) {
            Vista = "";
            Vista2 = "";
            Vista += "Dia :" + dias + "\n";
            Vista2 += "Dia :" + dias + "\n";

            Vista += "Clientes Activos :" + jugadas.size() + "\n";
            Vista2 += "Clientes Activos :" + jugadas.size() + "\n";
            Vista += "Inventario Actual:" + this.InventariadoAcutal + "\n";
            if (p != null) {
                Vista2 += "El Pedido Llegara en " + p.tiempoEspera + " dias\n";
            }
            Vista += "Encargos Actuales\n";
            for (int i = 0; i < ListaPedidos.size(); i++) {
                Pedido p = ListaPedidos.get(i);
                Vista += " Minorista" + p.getNombreSolicitante() + " Cantidad: " + p.getCantidad() + " Tiempo Dispuesto a Esperar: " + p.getTiempoEspera() + "\n";

            }
            Vista += "--------------\n";
            Vista += "Minoristas\n";
            for (int i = 0; i < jugadas.size(); i++) {
                Jugada j = jugadas.get(i);
                Vista += "Nombre: " + j.Nombre + " Estado: ";
                Vista += j.Estado == 0 ? "Esperando Movimiento" : j.Estado == 1 ? "Esperando Pedido" : j.Estado == 2 ? "Envio Pedido" : "Omitio el Dia";
                Vista += "\n";
            }
            Vista2 += "Lista de Pedidos Perdidos\n";
            for (Pedido ListaPedido : ListaPedidos) {
                Vista2 += "Pedido Solicitado Por: " + ListaPedido.getNombreSolicitante() + " " + "Cantidad " + ListaPedido.getCantidad() + "\n";

            }

            log.EsribirVista1(Vista);
            log.EsribirVista2(Vista2);

            cont = 0;
            for (Jugada jugada : jugadas) {
                //VERIFICAR JUGADAS
                if (jugada.Estado != 0) {
                    cont++;
                }
            }
            if (cont == jugadas.size() && cont > 0) {//REINICIAR JUGADAS
                log.ControlDias(true);

            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mayorista.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private logica.Pedido EXtraerPrioridad() {
        int max = 0;
        Pedido maximo = null;
        for (Pedido pedido : ListaPedidos) {
            if (pedido.getPrioridad() >= max) {
                maximo = pedido;

            }

        }

        return maximo;
    }

    @Override
    public void RecibirMinorista(RegistroJugador r) {

        if (validarRepeticion(r) && minoristas.size() <= 10 && r.TipoEntidad.equals("M")) {
            System.out.println("Validado:");
            minoristas.add(r);
            jugadas.add(new Jugada(0, -1, r.Nombre));
            Comunicacion.EnviarRespuestaRegistro(r, log, true, this.getIp(), this.getPuerto(), this.JugadorNombre);
            log.ControlDias(false);

        } else {
            Comunicacion.EnviarRespuestaRegistro(r, log, false, this.getIp(), this.getPuerto(), this.JugadorNombre);
        }

    }

    @Override
    public void RecibirJugada(Jugada j) {
        for (int i = 0; i < jugadas.size(); i++) {
            if (jugadas.get(i).Nombre.equals(j.Nombre)) {
                System.out.println("DebeEntrar");
                jugadas.get(i).Estado = j.Estado;
            }

        }

    }

    private void RealizarTransaccion(logica.Pedido p) {
        throw new UnsupportedOperationException("Not supporte|d yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void HacerPedido() {
        CantidadaPedir=log.GetCantidad();
        p = new PedidoMayorista(CantidadaPedir, calTiempoEspera());

    }

    private void AtenderPedido() {
        for (int i = 0; i < ListaPedidos.size(); i++) {

            Pedido ped = EXtraerPrioridad();
            if (ped != null) {

                if (getInventariadoAcutal() >= ped.getCantidad()) {
                    ActualizarJugada(ped);
                    ListaPedidos.remove(ped);
                    if (ped.getCantidad() > 0) {
                        log.EscribirLog("Atendiendo Pedido de " + ped.getNombreSolicitante());
                        ped.setNombre_Proveed(getJugadorNombre());
                        Paquete paq = new Paquete(ped, PrecioDeVentaUnidad, 1);
                        Comunicacion.EntregarPaquete(paq, log);
                        setInventariadoAcutal(getInventariadoAcutal() - ped.getCantidad());
                    }

                }

            }
        }
    }

    private void ActualizarJugada(Pedido p) {
        for (int i = 0; i < jugadas.size(); i++) {
            if (jugadas.get(i).Nombre.equals(p.getNombreSolicitante())) {

                jugadas.get(i).Estado = 0;
            }

        }
    }

    @Override
    public void Registrar(ValidarRegistro v) {

    }

    @Override
    public void RestarDias() {
        for (Pedido ListaPedido : ListaPedidos) {
            ListaPedido.setTiempoEspera(ListaPedido.getTiempoEspera() - 1);
            if (ListaPedido.getTiempoEspera() < 0) {
                Pedidosperdidos.add(ListaPedido);
                ListaPedidos.remove(ListaPedido);
            }
        }
    }

    private int calTiempoEspera() {
        return 3;
    }
}
