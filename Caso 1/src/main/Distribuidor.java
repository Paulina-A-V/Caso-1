package main;


public class Distribuidor extends Thread {

    private String tipoProducto;
    private Buffer buffer;

    public Distribuidor( String tipoProducto, Buffer buffer){
        this.buffer = buffer;
        this.tipoProducto = tipoProducto;

    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public void consumir(){
        String producto = getBuffer().quitar(tipoProducto);

        System.out.println("Se consumio el producto: " + producto);

    }

    

    

}