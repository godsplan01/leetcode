package cn.eatboooo.leetcode.demo;

import cn.eatboooo.bean.ListNode;
import org.junit.Test;

import java.util.*;

/**
 * leetcode
 * https://leetcode.cn/problemset/all/?listId=2cktkvj&page=1
 *
 * @author weiZhiLin
 * @version 1.0
 * @date 2023/6/28 13:23
 */
public class Leetcode_Hot {
    // 2. 两数相加
    // 这个题目有坑，两个数相加的结果可能是无穷大，所以不能用简便方法运算
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result;
        ListNode l3 = new ListNode();
        result = l3;
        int carry = 0;
        while ((l1 != null || l2 != null)) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int i = a + b;
            if (i + carry >= 10) {
                l3.next = new ListNode((i + carry) - 10);
                carry = 1;
            } else {
                l3.next = new ListNode(i + carry);
                carry = 0;
            }
            l3 = l3.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry != 0) {
            l3.next = new ListNode(carry);
        }
        return result.next;
    }

    @Test
    public void test_01() {
        ListNode listNode = addTwoNumbers(new ListNode(9, 9, 9, 9, 9, 9, 9), new ListNode(9, 9, 9, 9));
        System.out.println("listNode = " + listNode);
    }

    // 3. 无重复字符的最长子串
    // 滑动时间窗口的简单思路
    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        if (arr.length == 1) {
            return 1;
        }
        windowTo3 win = new windowTo3();
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, win.put(arr[i]));
        }
        max = Math.max(max, win.getSize());
        return max;
    }

    // 滑动时间窗口，特供版
    public class windowTo3 {
        HashSet<Character> set = new HashSet();
        LinkedList<Character> list = new LinkedList();

        int getSize() {
            return list.size();
        }

        int put(char a) {
            int beforeSize = list.size();
            while (set.contains(a)) {
                Character character = list.removeFirst();
                set.remove(character);
            }
            set.add(a);
            list.add(a);
            return beforeSize;
        }
    }

    @Test
    public void test02_3() {
        System.out.println(lengthOfLongestSubstring("au"));
    }

    // 4, 寻找两个正序数组的中位数
    // 整几个指针完事了，挺简单
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int a = 0;
        int b = 0;
        int index = 0;
        int[] arr = new int[nums1.length + nums2.length];
        int mid = (nums1.length + nums2.length) >> 1;
        boolean need2 = (nums1.length + nums2.length) % 2 == 0;
        for (int i = a; i < nums1.length; i++, a++, index++) {
            for (int j = b; j < nums2.length && nums1[a] > nums2[b]; j++, b++, index++) {
                if (nums1[a] <= nums2[b]) {
                    break;
                }
                arr[index] = nums2[b];
            }
            arr[index] = nums1[a];
        }
        for (int j = b; j < nums2.length; j++, b++, index++) {
            arr[index] = nums2[b];
        }
        return need2 ? (double) (arr[mid] + arr[mid - 1]) / 2 : arr[mid];
    }

    @Test
    public void test04() {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 7}));
        System.out.println(findMedianSortedArrays(new int[]{2, 3}, new int[]{1}));
    }

    // 5. 最长回文子串
    public String longestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        char[] charArray = s.toCharArray();
        StringBuilder sCP = new StringBuilder();
        for (char c : charArray) {
            sCP.append(c).append("-");
        }
        charArray = sCP.toString().toCharArray();
        int max = 0;
        String reslut = "";
        for (int mid = 1; mid < charArray.length - 1; mid++) {
            int length = 1;
            StringBuilder temp = new StringBuilder(String.valueOf(charArray[mid]));
            for (int left = mid - 1, right = mid + 1; left >= 0 && right < charArray.length && charArray[left] == charArray[right]; left--, right++) {
                length = right - left + 1;
                temp = new StringBuilder(charArray[left] + temp.toString() + charArray[right]);
            }
            if (length > max) {
                max = length;
                reslut = temp.toString();
            }
        }
        return reslut.replace("-", "");
    }

    @Test
    public void test05() {
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("ab"));
        System.out.println(longestPalindrome("aa"));
        System.out.println(longestPalindrome("abcc"));
        System.out.println(longestPalindrome("abccbaaaaaaaa"));
    }

    // 10. 正则表达式匹配
    // 大刷 12题
    // TODO：DP 版本、记忆版本
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }
        return isMatch(0, 0, s.toCharArray(), p.toCharArray());
    }

    public boolean isMatch(int sIndex, int pIndex, char[] sArr, char[] pArr) {
        if (pIndex >= pArr.length) {
            // 字符串匹配结束
            // 同时正则也结束
            return sIndex == sArr.length;
        }
        // ⚠️这里不能提前判断，因为 s 遍历完成后，可能pArr只剩下了 * ，也是可以忽略的
/*        if (sIndex == sArr.length) {
            return false;
        }*/
        // 下一个不是 *
        if (pIndex + 1 == pArr.length || pArr[pIndex + 1] != '*') {
            return (sIndex != sArr.length && (sArr[sIndex] == pArr[pIndex] || pArr[pIndex] == '.')) && isMatch(sIndex + 1, pIndex + 1, sArr, pArr);
        }

        // 下一个是 *
        // 错误版 ⚠️       while (pArr[pIndex + 1] == '*' && sIndex < sArr.length) {
        // 原因： 首先，下一个不是*的情况已经被过滤，所以，下一个必定是*。其次，即便下一个是*，也需要判断 pArr[pIndex]能否变身
        while (sIndex < sArr.length && (pArr[pIndex] == sArr[sIndex] || pArr[pIndex] == '.')) {
            if (isMatch(sIndex, pIndex + 2, sArr, pArr)) {
                return true;
            }
            sIndex++;
        }
        // ⚠️，有可能下一个是*，但是不相等，只能变成0个
        // 还有可能上述的 while 结束了还没返回，此时就是：pArr 已经变身为可以变身的最长字符了
        return isMatch(sIndex, pIndex + 2, sArr, pArr);
    }

    @Test
    public void test06() {
        System.out.println(isMatch("a", "ab*"));
        System.out.println(isMatch("mississippi", "mis*is*ip*."));
        System.out.println(isMatch("abccc", ".*"));
        System.out.println(isMatch("abccc", ".*a"));
        System.out.println(isMatch("abccc", ".*c"));
        System.out.println(isMatch("abccc", ".*bccc"));
        System.out.println(isMatch("abccc", ".*b*cc"));
        System.out.println(isMatch("abccc", ".*ccc"));
    }

    // 11. 盛最多水的容器 - 根据数据量猜测解法
    // 大刷8
    // n == height.length
    //  2 <= n <= 105
    // 至少是 O（log n） 自然想到双指针
    // TODO 单调栈版本

    // 思想先进
    // 左右各一个指针，计算面积后，向中间移动值比较小的指针
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            max = Math.max(Math.min(height[l], height[r]) * (r - l), max);
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return max;
    }

    // 15. 三数之和 - 大刷 25 - 两数之和加强版
    // 两数之和加强版？ 目标 k，把数组排好序
    // l，r 两个指针，
    // 相加等于 k 存放，l++ r --
    // 相加小于 k l++
    // 相加大于 k r --
    // 直到 l 与 r 相遇
    // 三数之和，即为：遍历数组，除去当前遍历位置的数，两数之和有没有等于它的相反数的
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> result = new LinkedList<>();
        for (int i = nums.length - 1; i > 0; i--) {
            int num = nums[i];
            if (i == nums.length - 1 || num != nums[i + 1]) {
                List<List<Integer>> lists = twoSum(nums, i - 1, -num);
                for (List<Integer> list : lists) {
                    list.add(num);
                }
                result.addAll(lists);
            }
        }
        return result;
    }

    // 0 ~ r 找出两个数相加等于 k 的
    public List<List<Integer>> twoSum(int[] nums, int r, int k) {
        List<List<Integer>> result = new LinkedList<>();
        int l = 0;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum < k) {
                l++;
                continue;
            } else if (sum > k) {
                r--;
                continue;
            } else if (l == 0 || nums[l] != nums[l - 1]) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[l]);
                temp.add(nums[r]);
                result.add(temp);
            }
            l++;
            r--;
        }
        return result;
    }

    // 80. 删除有序数组中的重复项 II
    // https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&envId=top-interview-150
    public int removeDuplicates(int[] nums) {
        int size = nums.length;
        int l = 2;
        int minL = 0;
        while (l <= size - 1) {
            if (nums[minL] == nums[l]) {
                delete(nums, l);
                size--;
            } else {
                minL++;
                l++;
            }
        }
        return size;
    }

    private static void delete(int[] arr, int l) {
        for (int i = l; i < arr.length - 1; i++) {
            swap(arr, i, i + 1);
        }
    }

    private static void swap(int[] arr, int l, int r) {
        arr[l] = arr[r] ^ arr[l];
        arr[r] = arr[r] ^ arr[l];
        arr[l] = arr[r] ^ arr[l];
    }

    // 189. 轮转数组，不申请额外空间
    // https://leetcode.cn/problems/rotate-array/submissions/521824465/?envType=study-plan-v2&envId=top-interview-150
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        for (int i = 0; i < k; i++) {
            rightGoLeft(nums);
        }
    }

    private static void rightGoLeft(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, i - 1);
        }
    }

    // 189. 轮转数组，不申请额外空间
    // 搞个新数组，没啥技术含量
    // 1.先整体反转，再分步反转
    // 2.分步根据 k 位置，反转两次
    // reverse(nums, 0, k-1);
    // reverse(nums, k, nums.length-1);
    // 高端：翻转数组
    public void rotate2(int[] nums, int k) {
        k = nums.length - (k % nums.length);
        int[] ints = new int[nums.length];
        int zeroIndex = 0;
        for (int i = k; i < ints.length; i++) {
            ints[zeroIndex++] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            ints[zeroIndex++] = nums[i];
        }

        for (int i = 0; i < ints.length; i++) {
            nums[i] = ints[i];
        }
    }

    // 55. 跳跃游戏
    // https://leetcode.cn/problems/jump-game/solutions/203549/tiao-yue-you-xi-by-leetcode-solution/?envType=study-plan-v2&envId=top-interview-150
    // 暴力递归改动态规划，很舒服
    // todo:后续尝试单调栈，每到达一个格子， 刷新能跳到的最远距离
    public boolean canJump(int[] nums) {
        int[] arr = new int[nums.length];
        arr[arr.length - 1] = 1;
        int target = nums.length - 1;
        for (int index = arr.length - 2; index >= 0; index--) {
            int times = nums[index];
            if (times >= (target - index)) {
                arr[index] = 1;
                continue;
            }
            int result = 0;
            for (int i = 1; i <= times; i++) {
                result |= arr[index + i];
            }
            arr[index] = result;
        }

        return arr[0] == 1;
    }

    // 暴力递归，但是超时了
    private boolean doJump(int[] nums, int index, int target) {
        if (index >= target) {
            return true;
        }
        int times = nums[index];
        if (times >= (target - index)) {
            return true;
        }
        boolean result = false;

        for (int i = 1; i <= times; i++) {
            result |= doJump(nums, index + i, target);
        }
        return result;
    }

    // 45. 跳跃游戏 II
    // https://leetcode.cn/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top-interview-150
    public int jump(int[] nums) {
        int[] arr = new int[nums.length];
        int target = nums.length - 1;
        for (int index = arr.length - 2; index >= 0; index--) {
            int times = nums[index];
            if (times >= (target - index)) {
                arr[index] = 1;
                continue;
            }
            int result = Integer.MAX_VALUE;
            for (int i = 1; i <= times; i++) {
                int i1 = arr[index + i];
                if (i1 == Integer.MAX_VALUE) {
                    continue;
                }
                result = Math.min(i1 + 1, result);
            }
            arr[index] = result;
        }
        return arr[0];
    }

    // 暴力递归，但是超时了
    // very good
    private int doJump02(int[] nums, int index, int target) {
        if (index >= target) {
            return 0;
        }
        int times = nums[index];
        if (times >= (target - index)) {
            return 1;
        }

        int result = Integer.MAX_VALUE;

        for (int i = 1; i <= times; i++) {
            int i1 = doJump02(nums, index + i, target);

            if (i1 == Integer.MAX_VALUE) {
                continue;
            }
            result = Math.min(i1 + 1, result);
        }

        return result;
    }

}
