package itsrv.models;
/**
 *
 * @author gael_
 */
public class Cliente {
    
    private String nombreC;
    private String apePC;
    private String apeMC;
    private String calleC;
    private int numero;
    private String colonia;
    private int numCliente;

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getApePC() {
        return apePC;
    }

    public void setApePC(String apePC) {
        this.apePC = apePC;
    }

    public String getApeMC() {
        return apeMC;
    }

    public void setApeMC(String apeMC) {
        this.apeMC = apeMC;
    }

    public String getCalleC() {
        return calleC;
    }

    public void setCalleC(String calleC) {
        this.calleC = calleC;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }
    
    public String getNombreCompleto() {
        return nombreC + " " + apePC + " " + apeMC;
    }
    
    public String getDireccion() {
        return calleC + " #" + numero + ", " + colonia;
    }
    
}
