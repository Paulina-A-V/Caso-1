package main;

public class Productor extends Thread {

	private int numProductos;
	private String tipoProducto;
	private Buffer buffer;

	public Productor(int numProductos, String tipoProducto, Buffer buffer) {
		this.numProductos = numProductos;
		this.tipoProducto = tipoProducto;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		producir();
	}

	public void producir() {
		for (int i = 0; i < this.numProductos; i++) {
			this.buffer.poner(tipoProducto);
		}
		this.buffer.poner("FIN_" + tipoProducto);
	}

}
