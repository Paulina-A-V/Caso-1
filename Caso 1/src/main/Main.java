package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);


        System.out.print("Entre el número de producto que desea producir: ");
        int numProductos = scanner.nextInt();

        
        System.out.print("Entre la capacidad del deposito de producción: ");
        int capDepProd = scanner.nextInt();

        
        System.out.print("Entre la capacidad del deposito de distribución: ");
        int capDepDist = scanner.nextInt();

        Buffer depositoProduccion = new Buffer(capDepProd);
        Buffer depositoDistribucion = new Buffer(capDepDist);
        Buffer cinta = new Buffer(1);

        Productor productorA1 = new Productor(numProductos, "A", depositoProduccion);
        Productor productorA2 = new Productor(numProductos, "A", depositoProduccion);
        Productor productorB1 = new Productor(numProductos, "B", depositoProduccion);
        Productor productorB2 = new Productor(numProductos, "B", depositoProduccion);
        
        Distribuidor distribuidorA1 = new Distribuidor("A", depositoDistribucion);
        Distribuidor distribuidorA2 = new Distribuidor("A", depositoDistribucion);
        Distribuidor distribuidorB1 = new Distribuidor("B", depositoDistribucion);
        Distribuidor distribuidorB2 = new Distribuidor("B", depositoDistribucion);

        Interno interno1 = new Interno(4, depositoProduccion, cinta);
        Interno interno2 = new Interno(4, cinta, depositoDistribucion);

        productorA1.start();
        productorA2.start();
        productorB1.start();
        productorB2.start();

        distribuidorA1.start();
        distribuidorA2.start();
        distribuidorB1.start();
        distribuidorB2.start();

        interno1.start();
        interno2.start();

       
        try {
            productorA1.join();
            productorA2.join();
            productorB1.join();
            productorB2.join();
            System.out.println("Productores finished");

            interno1.join();
            interno2.join();
            System.out.println("Internos finished");

            distribuidorA1.join();
            distribuidorA2.join();
            distribuidorB1.join();
            distribuidorB2.join();
            System.out.println("Distribuidores finished");

            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished");

        scanner.close();
    }
}
