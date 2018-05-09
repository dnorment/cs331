import java.util.Random;
/**
 * Classical matrix multiplication, using three for loops.
 * Expected time complexity O(n^3).
 * @author Daniel J. Norment
 */
public class Classical 
{
    public static void main(String[] args)
    {
        final int MATRIX_SIZE = 4;
        int[][] one = new int[MATRIX_SIZE][MATRIX_SIZE];
        int[][] two = new int[MATRIX_SIZE][MATRIX_SIZE];
        int[][] result = new int[MATRIX_SIZE][MATRIX_SIZE];
        
        Random rng = new Random();
        for (int row=0; row<MATRIX_SIZE; row++)
        {
            for (int col=0; col<MATRIX_SIZE; col++)
            {
                one[row][col] = rng.nextInt(100);
                two[row][col] = rng.nextInt(100);
            }
        }
        
        for (int row=0; row<one.length; row++) //for each row
        {
            for (int col=0; col<two.length; col++) //for each column
            {
                for (int k=0; k<two.length; k++) //inner product
                {
                    result[row][col] += one[row][k] * two[k][col];
                }
            }
        }
        
        for (int row=0; row<one.length; row++) //for each row
        {
            for (int col=0; col<two.length; col++) //for each column
            {
                System.out.print(result[row][col] + " ");
            }
            System.out.println();
        }
    }
}