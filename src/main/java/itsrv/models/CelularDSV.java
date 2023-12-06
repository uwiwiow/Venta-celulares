package itsrv.models;

/**
 *
 * @author gael_
 */
public class CelularDSV extends Celular{
    
    private int cantidad;
    private float importe;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
    
}
