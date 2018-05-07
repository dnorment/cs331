/**
 * Classical matrix multiplication, using three for loops.
 * Expected time complexity O(n^3).
 * @author Daniel J. Norment
 */
public class Classical 
{
    public static void multiply(int[][] one, int[][] two) 
    {
        int[][] result = new int[one.length][two.length];
        
        for (int i=0; i<one.length; i++) //for each row
        {
            for (int j=0; j<two.length; j++) //for each column
            {
                for (int k=0; k<two.length; k++) //inner product
                {
                    result[i][j] += one[i][k] * two[k][j];
                }
            }
        }
        
        for (int i=0; i<result.length; i++)
        {
            for (int j=0; j<result.length; j++)
            {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args)
    {
        int[][] one = {{1, 0, 0},{0, 1, 0}, {0, 0, 1}};
        int[][] two = {{1, 0, 0},{0, 1, 0}, {0, 0, 1}};
        Classical.multiply(one, two);
    }
}