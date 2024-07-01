using Xunit;

namespace AlgorithmDesign.code.leetcode.lc002_add_two_numbers
{
    public class SolutionTests
    {
        private ListNode Thaw(int[] array)
        {
            ListNode handle = new ListNode();
            ListNode tail = handle;
            foreach (int value in array)
            {
                tail.next = new ListNode(value);
                tail = tail.next;
            }
            return handle.next;
        }

        private int[] Freeze(ListNode list)
        {
            int count = 0;
            ListNode copy = list;
            while (copy != null)
            {
                count++;
                copy = copy.next;
            }
            int[] frozen = new int[count];
            copy = list;
            for (int i = 0; i < count; i++)
            {
                frozen[i] = copy.val;
                copy = copy.next;
            }
            return frozen;
        }

        [Fact]
        public void TestExample()
        {
            int[] array1 = { 2, 4, 3 };
            int[] array2 = { 5, 6, 4 };
            ListNode list = new Solution().AddTwoNumbers(Thaw(array1), Thaw(array2));
            int[] expected = { 7, 0, 8 };
            Assert.Equal(expected, Freeze(list));
        }



        [Fact]
        public void TestUneven()
        {
            int[] array1 = { 2, 4 };
            int[] array2 = { 5, 6, 4 };
            ListNode list = new Solution().AddTwoNumbers(Thaw(array1), Thaw(array2));
            int[] expected = { 7, 0, 5 };
            Assert.Equal(expected, Freeze(list));
        }

        [Fact]
        public void TestCarry()
        {
            int[] array1 = { 2, 4 };
            int[] array2 = { 5, 6 };
            ListNode list = new Solution().AddTwoNumbers(thaw(array1), thaw(array2));
            int[] expected = { 7, 0, 1 };
            Assert.Equal(expected, Freeze(list));
        }
    }
}
