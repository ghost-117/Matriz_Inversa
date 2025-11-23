import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Versión 1.2 - Implementación del algoritmo de ordenamiento
 * Agregado método burbuja para orden descendente
 */
public class MatrizInversaFiles {
    
    private Files files;
    
    public MatrizInversaFiles() {
        this.files = new Files();
    }
    
    public void ordenarBurbujaInverso(int[] array) {
        int n = array.length;
        boolean intercambio;
        
        for (int i = 0; i < n - 1; i++) {
            intercambio = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                // Comparación para orden descendente (mayor a menor)
                if (array[j] < array[j + 1]) {
                    // Intercambiar elementos
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    intercambio = true;
                }
            }
            
            // Si no hubo intercambios, el arreglo ya está ordenado
            if (!intercambio) break;
        }
    }
    
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
        
        System.out.println("");
        System.out.println("MATRIZ ORDEN INVERSO");
        System.out.print("Ingrese el nombre del archivo: ");
        String fileName = bufer.readLine();

    }
}