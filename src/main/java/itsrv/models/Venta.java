package itsrv.models;

import java.sql.Date;

/**
 *
 * @author gael_
 */
public class Venta {
    
    private int noE;
    private int noC;
    private Date fecha;
    private int folioV;

    public int getNoE() {
        return noE;
    }

    public void setNoE(int noE) {
        this.noE = noE;
    }

    public int getNoC() {
        return noC;
    }

    public void setNoC(int noC) {
        this.noC = noC;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getFolioV() {
        return folioV;
    }

    public void setFolioV(int folioV) {
        this.folioV = folioV;
    }
    
}
