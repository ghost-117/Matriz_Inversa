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
        String fileNameOut;
        double[][] matrix;
        double[][] inverse;
        double[][] verification;
        double det;
        String opcion;

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
        printMatrix(matrix, "* MATRIZ ORIGINAL *");
        
        // Calcular determinante
        System.out.println("\n>>> Calculando determinante...");
        det = mif.calculateDeterminant(matrix);
        System.out.println("Determinante: " + String.format("%.6f", det));
        
        // Verificar si es invertible
        if (Math.abs(det) < 1e-10){
            System.out.println("\n✗ La matriz NO es invertible (determinante = 0)");
            System.out.println("No se puede calcular la matriz inversa.");
            return;
        }
        
        System.out.println(" La matriz es invertible (det ≠ 0)");
        
        // Calcular matriz inversa
        System.out.println("\n>>> Aplicando método de Gauss-Jordan...");
        inverse = mif.inverseMatrix(matrix);
        
        if (inverse == null){
            System.out.println("Error al calcular la inversa.");
            return;
        }
        
        // Mostrar matriz inversa
        printMatrix(inverse, "*** MATRIZ INVERSA ***");
        
        // Verificar: A × A^-1 = I
        System.out.println("\n>>> Verificando: A × A⁻¹ = I ...");
        verification = mif.multiplyMatrices(matrix, inverse);
        printMatrix(verification, "* VERIFICACIÓN: A × A⁻¹ *");
        System.out.println("(Debe ser la matriz identidad)");
        
        // Guardar resultados
        System.out.print("\n¿Deseas guardar el reporte completo? (s/n): ");
        opcion = bufer.readLine();
        
        if (opcion.equalsIgnoreCase("s")){
            System.out.print("Nombre del archivo de salida: ");
            fileNameOut = bufer.readLine();
            mif.writeCompleteReport(fileNameOut, matrix, inverse, det, verification);
            System.out.println("\n Reporte guardado exitosamente!");
            System.out.println("Ubicación: C:\\Archivos\\" + fileNameOut);
        }
        
        // Opción de guardar solo la inversa
        System.out.print("\n¿Deseas guardar solo la matriz inversa? (s/n): ");
        opcion = bufer.readLine();
        
        if (opcion.equalsIgnoreCase("s")){
            System.out.print("Nombre del archivo: ");
            fileNameOut = bufer.readLine();
            mif.writeMatrixToFile(fileNameOut, inverse, "MATRIZ INVERSA");
            System.out.println("\n✓ Matriz inversa guardada!");
            System.out.println("Ubicación: C:\\Archivos\\" + fileNameOut);
        }
    }
}