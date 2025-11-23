
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

}