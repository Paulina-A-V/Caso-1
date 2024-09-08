package main;


public class Distribuidor extends Thread {

    private String tipoProducto;
    private Buffer buffer;

    public Distribuidor(String tipoProducto, Buffer buffer){
        this.tipoProducto = tipoProducto;
        this.buffer = buffer;

    }

    @Override
    public void run(){
        consumir();
    }

    public void consumir(){
        boolean endFound = false;
        while (!endFound) {
            String producto = this.buffer.quitar(this.tipoProducto);
            if (producto.equals("FIN_" + this.tipoProducto)){
                endFound = true;
            } else {
                System.out.println("Distribuidor con thread id: " + Thread.currentThread().threadId() + " ha consumido: " + tipoProducto);
            }
        }

        System.out.println("Distribuidor con thread id: " + Thread.currentThread().threadId() + " ha terminado de consumir: " + tipoProducto);

    }

    

    

}