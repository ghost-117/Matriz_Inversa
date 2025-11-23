import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MatrizInversaFiles{

    // Crear matriz identidad
    private double[][] createIdentityMatrix(int n){
        double[][] identity = new double[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i == j)
                    identity[i][j] = 1.0;
                else
                    identity[i][j] = 0.0;
            }
        }
        return identity;
    }

    // Copiar matriz
    private double[][] copyMatrix(double[][] matrix){
        int n = matrix.length;
        double[][] copy = new double[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                copy[i][j] = matrix[i][j];
            }
        }
        return copy;
    }

    // Intercambiar filas
    private void swapRows(double[][] matrix, int row1, int row2){
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    // Calcular determinante (para verificar si es invertible)
    public double calculateDeterminant(double[][] matrix){
        int n = matrix.length;
        double[][] temp = copyMatrix(matrix);
        double det = 1.0;
        
        for (int i = 0; i < n; i++){
            // Buscar pivote
            int pivotRow = i;
            for (int k = i + 1; k < n; k++){
                if (Math.abs(temp[k][i]) > Math.abs(temp[pivotRow][i])){
                    pivotRow = k;
                }
            }
            
            // Si el pivote es cero, el determinante es cero
            if (Math.abs(temp[pivotRow][i]) < 1e-10){
                return 0.0;
            }
            
            // Intercambiar filas si es necesario
            if (pivotRow != i){
                swapRows(temp, i, pivotRow);
                det *= -1; // Cambio de signo por intercambio
            }
            
            // Multiplicar determinante por el elemento diagonal
            det *= temp[i][i];
            
            // Hacer ceros debajo del pivote
            for (int k = i + 1; k < n; k++){
                double factor = temp[k][i] / temp[i][i];
                for (int j = i; j < n; j++){
                    temp[k][j] -= factor * temp[i][j];
                }
            }
        }
        
        return det;
    }

    // Calcular matriz inversa usando método de Gauss-Jordan
    public double[][] inverseMatrix(double[][] matrix){
        int n = matrix.length;
        
        // Verificar que sea cuadrada
        if (n != matrix[0].length){
            System.out.println("Error: La matriz debe ser cuadrada.");
            return null;
        }
        
        // Verificar que el determinante no sea cero
        double det = calculateDeterminant(matrix);
        if (Math.abs(det) < 1e-10){
            System.out.println("Error: La matriz no es invertible (determinante = 0).");
            return null;
        }
        
        // Crear copia de la matriz original
        double[][] A = copyMatrix(matrix);
        
        // Crear matriz identidad (será la inversa al final)
        double[][] inverse = createIdentityMatrix(n);
        
        // Aplicar Gauss-Jordan
        for (int i = 0; i < n; i++){
            // Buscar el mejor pivote
            int pivotRow = i;
            for (int k = i + 1; k < n; k++){
                if (Math.abs(A[k][i]) > Math.abs(A[pivotRow][i])){
                    pivotRow = k;
                }
            }
            
            // Intercambiar filas si es necesario
            if (pivotRow != i){
                swapRows(A, i, pivotRow);
                swapRows(inverse, i, pivotRow);
            }
            
            // Dividir la fila por el pivote
            double pivot = A[i][i];
            for (int j = 0; j < n; j++){
                A[i][j] /= pivot;
                inverse[i][j] /= pivot;
            }
            
            // Hacer ceros en toda la columna excepto el pivote
            for (int k = 0; k < n; k++){
                if (k != i){
                    double factor = A[k][i];
                    for (int j = 0; j < n; j++){
                        A[k][j] -= factor * A[i][j];
                        inverse[k][j] -= factor * inverse[i][j];
                    }
                }
            }
        }
        
        return inverse;
    }

    // Multiplicar matrices (para verificar A * A^-1 = I)
    public double[][] multiplyMatrices(double[][] A, double[][] B){
        int n = A.length;
        double[][] result = new double[n][n];
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                result[i][j] = 0;
                for (int k = 0; k < n; k++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        
        return result;
    }

    // Leer matriz desde archivo
    public double[][] readMatrixFromFile(String fileName){
        File file;
        FileReader reader;
        BufferedReader bufer;
        String linea;
        double[][] matrix = null;
        
        try{
            file = new File("C:\\Archivos\\" + fileName);
            reader = new FileReader(file);
            bufer = new BufferedReader(reader);
            
            // Primera línea: tamaño de la matriz
            linea = bufer.readLine();
            int n = Integer.parseInt(linea.trim());
            matrix = new double[n][n];
            
            // Leer cada fila de la matriz
            for (int i = 0; i < n; i++){
                linea = bufer.readLine();
                String[] valores = linea.trim().split("\\s+");
                for (int j = 0; j < n; j++){
                    matrix[i][j] = Double.parseDouble(valores[j]);
                }
            }
            
            reader.close();
        } catch(Exception e){
            System.out.println("Error al leer el archivo: " + e.toString());
        }
        
        return matrix;
    }

    // Escribir matriz en archivo
    public void writeMatrixToFile(String fileName, double[][] matrix, String titulo){
        FileWriter file;
        PrintWriter writer;
        
        try{
            file = new FileWriter("c:\\Archivos\\" + fileName);
            writer = new PrintWriter(file);
            
            writer.println("========================================");
            writer.println(titulo);
            writer.println("========================================");
            
            int n = matrix.length;
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    writer.printf("%.4f\t", matrix[i][j]);
                }
                writer.println();
            }
            file.close();
        } catch(Exception e){
            System.out.println("Error al crear el archivo: " + e.toString());
        }
    }

    // Escribir reporte completo
    public void writeCompleteReport(String fileName, double[][] original, 
                                double[][] inverse, double det, double[][] verification){
        FileWriter file;
        PrintWriter writer;
        
        try{
            file = new FileWriter("c:\\Archivos\\" + fileName);
            writer = new PrintWriter(file);
            int n = original.length;
            
            writer.println("=============================================");
            writer.println("     REPORTE DE MATRIZ INVERSA");
            writer.println("=============================================\n");
            
            writer.println("--- MATRIZ ORIGINAL ---");
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    writer.printf("%.4f\t", original[i][j]);
                }
                writer.println();
            }
            
            writer.println("\n--- DETERMINANTE ---");
            writer.printf("det(A) = %.6f\n", det);
            
            if (inverse != null){
                writer.println("\n--- MATRIZ INVERSA ---");
                for (int i = 0; i < n; i++){
                    for (int j = 0; j < n; j++){
                        writer.printf("%.4f\t", inverse[i][j]);
                    }
                    writer.println();
                }
                
                writer.println("\n--- VERIFICACIÓN: A × A⁻¹ ---");
                for (int i = 0; i < n; i++){
                    for (int j = 0; j < n; j++){
                        writer.printf("%.4f\t", verification[i][j]);
                    }
                    writer.println();
                }
                writer.println("\n(Debe ser la matriz identidad)");
            } else {
                writer.println("\nLa matriz NO es invertible.");
            }
            
            writer.println("\n=============================================");
            file.close();
        } catch(Exception e){
            System.out.println("Error al crear el archivo: " + e.toString());
        }
    }

    // Mostrar matriz en consola
    public void displayMatrix(double[][] matrix, String titulo){
        System.out.println("\n" + titulo);
        int n = matrix.length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%.4f\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

}