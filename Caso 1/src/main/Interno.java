package main;

public class Interno extends Thread {
	private String tipoProducto = "Any";
	private int contadorFin;
	private Buffer inputBuff;
	private Buffer outputBuff;

	public Interno(int contadorFin, Buffer inputBuffer, Buffer outputBuffer) {
		this.contadorFin = contadorFin;
		this.inputBuff = inputBuffer;
		this.outputBuff = outputBuffer;
	}

	@Override
	public void run() {
		while (contadorFin > 0) {
			while (this.inputBuff.getVacio()) {
				Thread.yield();
			}
			String str = this.inputBuff.quitar(this.tipoProducto);
			System.out.println("Interno con thread id: " + Thread.currentThread().threadId() + " ha quitado: " + str);

			if (str.contains("FIN")) {
				System.out.println("Se encontro un FIN");
				contadorFin--;
			}

			while (this.outputBuff.getLleno()) {
				Thread.yield();
			}

			this.outputBuff.poner(str);
			System.out.println("Interno con thread id: " + Thread.currentThread().threadId() + " ha puesto: " + str);
		}
		System.out.println("Interno con thread id: " + Thread.currentThread().threadId() + " ha terminado de procesar");
	}
}
