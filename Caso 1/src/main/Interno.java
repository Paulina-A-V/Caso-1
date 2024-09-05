package main;

import java.util.Random;

public class Interno extends Thread {

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
		String str = this.inputBuff.withdraw();

		while (!str.equals("FIN")) {
			str = String.format(str + "T%d%d", level, processNum);
			this.outputBuff.store(str);
			str = this.inputBuff.withdraw();
			try {
				Thread.sleep(rand.nextInt(501) + 50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.outputBuff.store(str);
	}
}
