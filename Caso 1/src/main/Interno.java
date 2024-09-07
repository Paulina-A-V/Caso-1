package main;

import java.util.Random;

public class Interno extends Thread {
	final static String tipoProducto = "ANY";
	private int contadorFin = 0;
	private Buffer inputBuff;
	private Buffer outputBuff;

	public Interno(int contadorFin, Buffer bufferConsumer, Buffer buffersPoner) {
		this.contadorFin = contadorFin;
		this.inputBuff = bufferConsumer;
		this.outputBuff = buffersPoner;
	}

	@Override
	public void run() {
		String str = this.inputBuff.quitar("Any");

		while (!str.equals("FIN_A"))

			// System.out.println(String.format("Enter Level: %d Process: %d", level,
			// processNum));
			str = this.inputBuff.quitar(tipoProducto);

		while (!str.equals("FIN")) {
			//No entendi cual es la idea de este string tengo entendido que solo pasamos cadenas 
			//de un lado a otro


			//str = String.format(str + "T%d%d", level, processNum);


			this.outputBuff.poner(str);
			str = this.inputBuff.quitar(tipoProducto);
			try {
				Thread.sleep(new Random().nextInt(501) + 50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.outputBuff.poner(str);
	}
}
