import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Principal {

    // "ANIMÇÃO" DE ESPERA
    public static void sleep () {
        try {
            for (int i = 0; i < 3; i ++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.print(".");
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        Scanner enter = new Scanner(System.in);

        String path;
        String[] numeros;
        int vertices;
        int vertice1, vertice2;

        System.out.println("\n--------- Bem-vindo(a) ao sistema! ---------\n");
        System.out.println("Digite o nome do arquivo que deseja abrir:");
        System.out.print("> ");
        path = enter.nextLine();

        Manipulador arquivo = new Manipulador(path);

        numeros = arquivo.read();
        vertices = Integer.parseInt(numeros[0]);

        Grafo G = new Grafo(vertices);

        System.out.print("\n> Construindo grafo e realizando buscas");
        sleep();

        while ( (numeros = arquivo.read()) != null ) {
            vertice1 = Integer.parseInt(numeros[0]);
            vertice2 = Integer.parseInt(numeros[1]);

            G.addEdge(vertice1, vertice2);
        }

        G.depthSearch(1);
        G.breathSearch(1);

        G.ifConnected();
        G.ifCiycle();

        G.print();
        
        arquivo.close();
        enter.close();
    }

}