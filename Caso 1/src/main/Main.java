package main;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int capDepProd = 10;
        int capDepDist = 10;
        int numProductos = 1;

        List<Productor> productores = new ArrayList<>();
        List<Distribuidor> distribuidores = new ArrayList<>();

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

        Interno interno = new Interno(4, depositoProduccion, cinta);
        Interno interno2 = new Interno(4, cinta, depositoDistribucion);

        productores.add(productorA1);
        productores.add(productorA2);
        productores.add(productorB1);
        productores.add(productorB2);

        distribuidores.add(distribuidorA1);
        distribuidores.add(distribuidorA2);
        distribuidores.add(distribuidorB1);
        distribuidores.add(distribuidorB2);

        interno.start();
        interno2.start();

        for (Productor productor : productores) {
            productor.start();
        }

        for (Distribuidor distribuidor : distribuidores) {
            distribuidor.start();
        }

        // wait for all threads to finish
        try {
            for (Productor productor : productores) {
                productor.join();
            }
            System.out.println("Productores finished");

            interno.join();
            interno2.join();
            System.out.println("Internos finished");

            for (Distribuidor distribuidor : distribuidores) {
                distribuidor.join();
            }
            System.out.println("Distribuidores finished");

            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished");

    }
}
