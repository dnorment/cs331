/**
 * Strassen's matrix multiplication.
 * Expected time complexity O(n^2.71).
 * @author Daniel J. Norment
 */
public class Strassen
{
    public static int[][] multiply(int[][] one, int[][] two)
    {
        int[][] result;
        if (one.length == 2)
        {
            result = new int[2][2];
            result[0][0] = one[0][0]*two[0][0] + one[0][1]*two[1][0];
            result[0][1] = one[0][0]*two[0][1] + one[0][1]*two[1][1];
            result[1][0] = one[1][0]*two[0][0] + one[1][1]*two[1][0];
            result[1][1] = one[1][0]*two[0][1] + one[1][1]*two[1][1];
        }
        else
        {
            
            int[][] result_aa = add(multiply(one_aa, two_aa), multiply(one_ab, two_ba));
            int[][] result_ab = add(multiply(one_aa, two_ab), multiply(one_ab, two_bb));
            int[][] result_ba = add(multiply(one_ba, two_aa), multiply(one_bb, two_ba));
            int[][] result_bb = add(multiply(one_ba, two_ab), multiply(one_bb, two_bb));
            
            for (int row=0; row<one.length/2; row++)
            {
                for (int col=0; col<two.length/2; col++)
                {
                    result[row][col] = result_aa[row][col];
                    result[row][col+one.length/2] = result_ab[row][col];
                    result[row+one.length/2][col] = result_ba[row][col];
                    result[row+one.length/2][col+one.length/2] = result_bb[row][col];
                }
            }
        }
    }
    
    public static int[][] add(int[][] one, int[][] two)
    {
        int[][] result = new int[one.length][one.length];
        for (int row=0; row<one.length/2; row++)
        {
            for (int col=0; col<two.length/2; col++)
            {
                result[row][col] = one[row][col] + two[row][col];
            }
        }    
        return result;
    }
    
    public static int[][] subtract(int[][] one, int[][] two)
    {
        int[][] result = new int[one.length][one.length];
        for (int row=0; row<one.length/2; row++)
        {
            for (int col=0; col<two.length/2; col++)
            {
                result[row][col] = one[row][col] - two[row][col];
            }
        }    
        return result;
    }
    
    public static void main(String[] args)
    {
        final int MATRIX_SIZE = 16;
        int[][] one = new int[MATRIX_SIZE][MATRIX_SIZE];
        int[][] two = new int[MATRIX_SIZE][MATRIX_SIZE];
        
        int[][] result = Strassen.multiply(one, two);
        for (int row=0; row<MATRIX_SIZE; row++)
        {
            for (int col=0; col<MATRIX_SIZE; col++)
            {
                System.out.print(result[row][col] + " ");
            }
            System.out.println();
        }
        
    }
}