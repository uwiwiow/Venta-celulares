package itsrv.models;
/**
 *
 * @author gael_
 */
public class Empleado {
    
    private String nombreE;
    private String apeP;
    private String apeM;
    private String turno;
    private int numEmpleado;

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getApeP() {
        return apeP;
    }

    public void setApeP(String apeP) {
        this.apeP = apeP;
    }

    public String getApeM() {
        return apeM;
    }

    public void setApeM(String apeM) {
        this.apeM = apeM;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
    
    public String getNombreCompleto() {
        return nombreE + " " + apeP + " " + apeM;
    }
    
}
