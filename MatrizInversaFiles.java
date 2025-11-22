import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MatrizInversaFiles {
    

    // Método para imprimir arreglo
    public void imprimirArreglo(int[] array) {
        System.out.print("[ ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println(" ]");
    }
    
    public static void main(String[] args) throws IOException {
        MatrizInversaFiles programa = new MatrizInversaFiles();
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("===========================================");
        System.out.println(" Matriz Orden Inverso");
        System.out.print("Ingrese el nombre del archivo: ");
        String fileName = bufer.readLine();

    }
}