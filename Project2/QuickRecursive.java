import java.util.Random;

/**
 * @author Daniel J. Norment
 * @version 1.0
 */
public class QuickRecursive
{
    public static int sort(int[] array, int first, int last, int k)
    {
        int pivot = -1;
        while (k != pivot)
        {
            pivot = partition(array, first, last);
            if (k == pivot) //k is pivot, found kth smallest
            {
                return array[k];
            }
            else if (k < pivot) //k less than pivot, find k in left
            {
                last = k;
            }
            else //k greater than pivot, find k in right 
            {
                first = k;
            }
        }
        return array[k];
    }
    
    public static int partition(int[] array, int first, int last)
    {
        int pivot = array[last]; //last element as pivot
        int i = first - 1;
        for (int j=first; j<last; j++) //effective subarray
        {
            if (array[j] <= pivot)
            {
                int temp = array[++i]; //swap if elements to right of pivot are less than pivot
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i+1]; //swap i+1 element and pivot then return its index
        array[i+1] = array[last];
        array[last] = temp;
        
        return i+1;
    }
    
    public static void main(String[] args)
    {
        final int ARRAY_SIZE = 5000000;
        final int RUNS = 50;
        
        int[] sortArray = new int[ARRAY_SIZE];
        Random rng = new Random();
        int[] ak = {1, ARRAY_SIZE/4, ARRAY_SIZE/2, 3*ARRAY_SIZE/4, ARRAY_SIZE};
        
        for (int i=0; i<ak.length; i++) //for each k
        {
            long avg = 0;
            for (int q=0; q<RUNS; q++)  //for num of runs 
            {
                int k = ak[i];
                for (int j=0; j<sortArray.length; j++)
                {
                    sortArray[j] = rng.nextInt(100);
                }
                long start = System.nanoTime();
                int selected = QuickRecursive.sort(sortArray, 0, ARRAY_SIZE-1, k-1);
                long end = System.nanoTime();
                long time = end - start;
                avg += time;
            }
            avg = avg/RUNS;
            System.out.printf("%d%n", avg);
        }
    }
}
