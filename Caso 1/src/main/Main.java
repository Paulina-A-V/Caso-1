package main;

public class Main {
    public static void main(String[] args) {
        int capDepProd = 10;
        int capDepDist = 10;
        int numProductos = 1;


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

       

        // wait for all threads to finish
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

    }
}
