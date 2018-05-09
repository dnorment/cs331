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
            result = new int[one.length][two.length];
            int[][] one_aa = new int[one.length/2][two.length/2];
            int[][] one_ab = new int[one.length/2][two.length/2];
            int[][] one_ba = new int[one.length/2][two.length/2];
            int[][] one_bb = new int[one.length/2][two.length/2];
            
            int[][] two_aa = new int[one.length/2][two.length/2];
            int[][] two_ab = new int[one.length/2][two.length/2];
            int[][] two_ba = new int[one.length/2][two.length/2];
            int[][] two_bb = new int[one.length/2][two.length/2];
            
            for (int row=0; row<one.length/2; row++)
            {
                for (int col=0; col<two.length/2; col++)
                {
                    one_aa[row][col] = one[row][col];
                    one_ab[row][col] = one[row][col+one.length/2];
                    one_ba[row][col] = one[row+one.length/2][col];
                    one_bb[row][col] = one[row+one.length/2][col+one.length/2];
                    
                    two_aa[row][col] = two[row][col];
                    two_ab[row][col] = two[row][col+two.length/2];
                    two_ba[row][col] = two[row+two.length/2][col];
                    two_bb[row][col] = two[row+two.length/2][col+two.length/2];
                }
            }
            
            int p[][] = multiply(add(one_aa, one_bb), add(two_aa, two_bb));
            int q[][] = multiply(add(one_ba, one_bb), two_aa);
            int r[][] = multiply(one_aa, subtract(two_ab, two_bb));
            int s[][] = multiply(one_bb, subtract(two_ba, two_aa));
            int t[][] = multiply(add(one_aa, one_ab), two_bb);
            int u[][] = multiply(subtract(one_ba, one_aa), add(two_aa, two_ab));
            int v[][] = multiply(subtract(one_ab, one_bb), add(two_ba, two_bb));
            
            int[][] result_aa = add(subtract(add(p, s), t), v);
            int[][] result_ab = add(r, t);
            int[][] result_ba = add(q, s);
            int[][] result_bb = add(subtract(add(p, r), q), u);
            
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
        return result;
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
        final int MATRIX_SIZE = 4;
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