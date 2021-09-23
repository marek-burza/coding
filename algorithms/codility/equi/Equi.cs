// http://blog.codility.com/2011/03/solutions-for-task-equi.html
using System.Numerics;

namespace AlgorithmDesign.codility.equi
{
    public class Solution
    {
        public int FindEquilibriumIndex(int[] array)
        {
            int n = array.Length;

            if (n == 0)
            {
                return -1;
            }
            BigInteger[] after = new BigInteger[n];
            for (int i = 0; i < n; i++)
            {
                after[i] = new BigInteger();
            }
            after[n - 1] = 0;
            for (int i = 1; i < n; i++)
            {
                after[n - 1 - i] = after[n - i] + array[n - i];
            }
            BigInteger summed = new BigInteger(0);
            for (int i = 0; i < n; i++)
            {
                if (summed == after[i])
                {
                    return i;
                }
                summed += array[i];
            }
            return -1;
        }
    }
}
