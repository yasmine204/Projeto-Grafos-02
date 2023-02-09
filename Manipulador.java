import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class Manipulador {

    private BufferedReader buffer;

    // ABRE O ARQUIVO PARA LEITURA
    Manipulador(String path) {
        try {
            buffer = new BufferedReader(new FileReader(path));
        } 
        catch (FileNotFoundException e) {
           System.out.println("Erro na abertura do arquivo!");
        }
    }

    // LÊ UMA LINHA DO ARQUIVO E SEPARA OS VÉRTICES
    @SuppressWarnings("finally")
    public String[] read () {
        String linha = null;
        String[] numeros = null;

        try {
            linha = buffer.readLine();
            linha = linha.trim().replaceAll("\\s+", " ");
            numeros = linha.split(" ");
        } 
        catch (EOFException e) {
            linha = null;
        }
        catch (IOException e) {
            System.out.println("Erro de leitiura: " + e);
        }
        finally {
            return numeros;
        }
    }

    // FECHA O ARQUIVO
    public void close () {
        try {
            buffer.close();
        } 
        catch (IOException e) {
            System.out.println("Erro ao fechar o arquivo: " + e);
        }
    }
    
}