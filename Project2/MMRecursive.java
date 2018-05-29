import java.util.Random;

/**
 * @author Daniel J. Norment
 * @version 1.0
 */
public class MMRecursive
{
    public static int getMedian(int[] array, int first, int last)
    {
        //System.out.printf("%d %d%n", first, last);
        if (last-first+1 <= 5)
        {
            MergeRecursive.sort(array, first, last);
            return array[(last+first)/2];
        }
        //split into 5 partitions
        int[] medians = new int[5];
        for (int i=0; i<4; i++) //get the median of the partitions
        {
            int length = (last-first)/5;
            //System.out.printf("Sorting %d to %d, %d%n", first, last, i);
            first += i*length;
            last = first + (i+1)*length;
            MergeRecursive.sort(array, first, Math.min(array.length-1, last));
            //System.out.printf("Finding median of subarray %d to %d%n", first, last);
            medians[i] = array[(first+last-1)/2];
        }
        return getMedian(medians, 0, 4);
    }
    
    public static int sort(int[] array, int first, int last, int k) //gets kth smallest
    {
        if (first < last)
        {
            int pivot = partition(array, first, last); //leave partition alone, sort about it
            //elements to each side of pivot will be less than and greater than, so look on each side
            if (k == pivot)
            {
                return array[k];
            }
            else if (k < pivot)
            {
                sort(array, first, pivot - 1, k);
                //System.out.println("sorting left half");
            }
            else //k > pivot
            {
                sort(array, pivot + 1, last, k);
                //System.out.println("sorting right half");
            }
        }
        return 0;
    }
    
    public static int partition(int[] array, int first, int last)
    {
        int pivot = getMedian(array, first, last); //MM as pivot
        int tmp = array[last];
        array[last] = pivot;
        array[(first+last)/2] = tmp;
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
        final int ARRAY_SIZE = 1000000;
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
                int selected = MMRecursive.sort(sortArray, 0, ARRAY_SIZE-1, k-1);
                long end = System.nanoTime();
                long time = end - start;
                avg += time;
            }
            avg = avg/RUNS;
            System.out.printf("%d%n", avg);
        }
    }
}
