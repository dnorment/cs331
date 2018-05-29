import java.util.Random;

/**
 * A recursive implementation of the merge sort algorithm.
 *
 * @author Daniel J. Norment
 * @version 1.1
 */
public class MergeRecursive
{
    public static void sort(int[] array, int left, int right)
    {
        if (left < right) //size > 1
        {
            int mid = (left + right) / 2;
            sort(array, left, mid);
            sort(array, mid+1, right);
            merge(array, left, mid, right);
        }
    }
        
    public static void merge(int[] array, int left, int mid, int right)
    {
        int[] leftArray = new int[mid - left + 1]; //size of each array to merge
        int[] rightArray = new int[right - mid]; 
        
        for (int i=0; i<mid-left+1; i++) //copy subarrays to temp arrays
        {
            leftArray[i] = array[left + i]; 
        }
        for (int i=0; i<right-mid; i++)
        {
            rightArray[i] = array[mid+1 + i];
        }
        
        int i=0; //index of left array
        int j=0; //index of right array
        int k=left; //index of beginning of effective array
        while (i < leftArray.length && j < rightArray.length)
        {
            if (leftArray[i] < rightArray[j])
            {
                array[k++] = leftArray[i++]; //insert item from left array
            }
            else
            {
                array[k++] = rightArray[j++]; //insert item from right array
            }
        }
        //will only exit above loop if one array inserts all of its elements, so add rest of other array
        while (i < leftArray.length) //insert remaining of left array
        {
            array[k++] = leftArray[i++];
        }
        while (j < rightArray.length) //insert remaining of right array
        {
            array[k++] = rightArray[j++];
        }
    }
    
    public static void main(String[] args)
    {
        final int ARRAY_SIZE = 5000000;
        final int RUNS = 50;
        
        int[] sortArray = new int[ARRAY_SIZE];
        Random rng = new Random();
        for (int i=0; i<sortArray.length; i++)
        {
            sortArray[i] = rng.nextInt(100);
        }
        int[] ak = {1, ARRAY_SIZE/4, ARRAY_SIZE/2, 3*ARRAY_SIZE/4, ARRAY_SIZE};
        
        for (int i=0; i<ak.length; i++) //for each k
        {
            long avg = 0;
            for (int q=0; q<RUNS; q++)  //for num
            {
                int k = ak[i];
                for (int j=0; j<sortArray.length; j++)
                {
                    sortArray[j] = rng.nextInt(100);
                }
                long start = System.nanoTime();
                MergeRecursive.sort(sortArray, 0, ARRAY_SIZE-1);
                int selected = sortArray[k-1];
                long end = System.nanoTime();
                long time = end - start;
                avg += time;
            }
            avg = avg/RUNS;
            System.out.printf("%d%n", avg);
        }
        
    }
}
