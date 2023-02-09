import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;

class Grafo {

    private int numVertices;
    private int[][] matrizAdjacencia;

    private boolean[] visitadoProfundidade;
    private ArrayList<Integer> percursoProfundidade = new ArrayList<>();

    private boolean[] visitadoLargura;
    private ArrayList<Integer> percursoLargura = new ArrayList<>();

    private boolean[] visitadoAux;
    private ArrayList<Integer> percursoAux = new ArrayList<>();

    private boolean ifConnected;
    private boolean ifCycle;

    // CONSTRUTOR DA CLASSE
    Grafo (int vertices) {
        this.numVertices = vertices+1;
        matrizAdjacencia = new int[numVertices][numVertices];

        visitadoProfundidade = new boolean[numVertices];
        for (int i = 0; i < visitadoProfundidade.length; i ++) {
            visitadoProfundidade[i] = false;
        }

        visitadoLargura = new boolean[numVertices];
        for (int i = 0; i < visitadoLargura.length; i ++) {
            visitadoLargura[i] = false;
        }

        visitadoAux = new boolean[numVertices];
        for (int i = 0; i < visitadoAux.length; i ++) {
            visitadoAux[i] = false;
        }
    }

    // ADICIONA ARESTAS AO GRAFO
    public void addEdge (int vertice1, int vertice2) {
        for (int i = 0; i <= numVertices; i ++) {
            for (int j = 0; j <= numVertices; j ++) {
                matrizAdjacencia[vertice1][vertice2] = 1;
            }
        }
    }

    // REALIZA BUSCA EM PROFUNDIDADE
    public void depthSearch (int v) {
        visitadoProfundidade[v] = true;
        percursoProfundidade.add(v);

        for (int i = 0; i < numVertices; i ++) {
            if (matrizAdjacencia[v][i] != 0 && !visitadoProfundidade[i]) {
                depthSearch(i);
            }
        }
    }

    // REALIZA BUSCA EM LARGURA
    public void breathSearch (int v) {
        Queue<Integer> fila = new ArrayDeque<>();
        visitadoLargura[v] = true;
        fila.add(v);

        while (!fila.isEmpty()) {
            v = fila.poll();
            percursoLargura.add(v);

            for (int i = 0; i < numVertices; i ++) {
                if (matrizAdjacencia[v][i] != 0 && !visitadoLargura[i]) {
                    visitadoLargura[i] = true;
                    fila.add(i);
                }
            }
        }
    }

    // TESTA SE O GRAFO É CONEXO
    public void ifConnected () {
        int contador[] = new int[numVertices];

        for (int i = 0; i < numVertices; i ++) {
            for (int j = 0; j < numVertices; j ++) {
                if ( (matrizAdjacencia[i][j] != 0) || (matrizAdjacencia[j][i] != 0) ) {
                    contador[i] = contador[i] + 1;
                } 
            }
        }

        for (int i = 0; i < numVertices; i ++) {
            if (contador[i] == 0) {
                ifConnected = false;
            }
            else {
                ifConnected = true;
            }
        }
    }

    // BUSCA EM PROFUNDIDADE PARA AUXILIAR O MÉTODO ifCycle()
    private boolean ds (int v) {
        if (visitadoAux[v] == true) {
            return true;
        }

        visitadoAux[v] = true;
        percursoAux.add(v);

        for (int i = 0; i < numVertices; i ++) {
            if (matrizAdjacencia[v][i] != 0 && !visitadoAux[i]) {
                if (ds(i)) {
                    return true;
                }
            }
        }

        return false;
    }

    // TESTA SE O GRAFO É CÍCLICO
    public void ifCiycle () {
        for (int i = 0; i < numVertices; i ++) {
            if (ds(i)) {
                ifCycle = true;
            }
            else {
                ifCycle = false;
            }
        }
    }

    // PRINTA INFORMAÇÕES DO GRAFO
    public void print () {
        System.out.println("\n\nPercurso da busca em profundidade: ");
        System.out.print("> ");

        for (int i = 0; i < percursoProfundidade.size(); i++) {
            System.out.print(percursoProfundidade.get(i) + " ");
        }

        System.out.println("\n\nPercurso da busca em largura: ");
        System.out.print("> ");
        
        for (int i = 0; i < percursoLargura.size(); i++) {
            System.out.print(percursoLargura.get(i) + " ");
        }

        System.out.println("\n\nGrafo conexo: \n> " + ifConnected);

        System.out.println("\nGrafo cíclico: \n> " + ifCycle);
    }
    
}

