/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author pc
 */
public interface VistaINt {

    public void EscribirLog(String s);
    public void EsribirVista1(String s);
    public void EsribirVista2(String s);
    public int  GetCantidad();
    public int  GetPuntodeReorden();
    public void   ControlManual(boolean  t);
    public void   ControlDias(boolean  t);
    public void Registrar(Boolean res);

}
