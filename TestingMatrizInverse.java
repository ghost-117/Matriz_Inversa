import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestingMatrizInverse{

    public static void printMatrix(double[][] matrix, String titulo){
        System.out.println("\n" + titulo);
        System.out.println("----------------------------");
        int n = matrix.length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%.4f\t", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }

    public static void main(String[] args) throws IOException{
        MatrizInversaFiles mif = new MatrizInversaFiles();
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        double[][] matrix;
        double[][] inverse;
        double det;

        System.out.println("=============================================");
        System.out.println("MATRIZ INVERSA (Gauss-Jordan)");
        
        // Leer matriz desde archivo
        System.out.print("\nIngresa el nombre del archivo con la matriz: ");
        fileName = bufer.readLine();
        matrix = mif.readMatrixFromFile(fileName);
        
        if (matrix == null){
            System.out.println("Error al cargar la matriz.");
            return;
        }
        
        // Mostrar matriz original
        printMatrix(matrix, "*** MATRIZ ORIGINAL ***");
        
        // Calcular determinante
        det = mif.calculateDeterminant(matrix);
        System.out.println("\nDeterminante: " + String.format("%.6f", det));
    }
}