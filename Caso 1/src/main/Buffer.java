package main;

import java.util.Queue;
import java.util.LinkedList;

public class Buffer {
	private int capacidad;
	private Queue<String> buffer = new LinkedList<String>();

	public Buffer(int capacidad) {
		this.capacidad = capacidad;
	}

	public synchronized boolean getLleno() {
		return this.buffer.size() == this.capacidad;
	}

	public synchronized boolean getVacio() {
		return this.buffer.isEmpty();
	}

	public synchronized String quitar(String tipoProducto) {
		if (tipoProducto.equals("Any")) {
			while (buffer.isEmpty()) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			this.notify();
			return this.buffer.poll();

		} else if (tipoProducto.equals("A")) {
			while (buffer.isEmpty() || !buffer.peek().equals("A") || !buffer.peek().equals("FIN_A")) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.notify();
			return this.buffer.poll();

		} else if (tipoProducto.equals("B")) {
			while (buffer.isEmpty() || !buffer.peek().equals("B") || !buffer.peek().equals("FIN_B")) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			this.notify();
			return this.buffer.poll();

		} else {
			return null;
		}
	}

	public synchronized void poner(String mensaje) {
		while (buffer.size() == this.capacidad) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.buffer.add(mensaje);
		this.notify();
	}
}
