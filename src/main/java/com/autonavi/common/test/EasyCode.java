package com.autonavi.common.test;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * leetcode easy code Created by Administrator on 2018-06-14.
 */
public class EasyCode {

    @Test
    public void tree2strTest() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        System.out.println(tree2str(root));

        root.left.right = null;
        root.left.left = new TreeNode(4);
        System.out.println(tree2str(root));

        TreeNode root2 = new TreeNode(1);
        System.out.println(tree2str(root2));
    }

    /**
     * 606. 根据二叉树创建字符串 采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串
     * 上周五想了半天没写出来，今天周一AC，树递归遍历 2018-06-25 19：52
     *
     * @param t TreeNode
     * @return str
     */
    public String tree2str(TreeNode t) {
        StringBuilder builder = new StringBuilder();
        preOrder(t, builder);
        String res = builder.toString();
        if (res.length() < 1) {
            return res;
        }
        return res.substring(1, res.length() - 1);
    }

    private void preOrder(TreeNode root, StringBuilder builder) {
        if (root == null) {
            return;
        }
        builder.append("(").append(root.val);
        if (root.left != null) {
            preOrder(root.left, builder);
        } else if (root.right != null) {
            builder.append("()");
        }
        if (root.right != null) {
            preOrder(root.right, builder);
        }
        builder.append(")");
    }

    public class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    @Test
    public void mostCommonWordTest() {
        System.out.println(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"}));
        System.out.println(mostCommonWord("Bob", new String[] {}));
    }

    /**
     * 819. 最常见的单词 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。 返回出现次数最多，同时不在禁用列表中的单词。
     * 题目保证至少有一个词不在禁用列表中，而且答案唯一
     *
     * @param paragraph 段落字符串
     * @param banned    被禁单词
     * @return 最常见单词出现的次数
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> banSet = Stream.of(banned).collect(Collectors.toSet());
        Map<String, Integer> stat = new HashMap<>();
        Set<Character> set = new HashSet<>();
        set.add('!');
        set.add('?');
        set.add('\'');
        set.add(',');
        set.add(';');
        set.add('.');
        set.add(' ');
        int i = 0;
        while (i < paragraph.length()) {
            int j = i;
            char ch = paragraph.charAt(j);
            while ((ch >= 'a' && ch <= 'z') ||
                    (ch >= 'A' && ch <= 'Z')) {
                j++;
                if (j > paragraph.length() - 1) {
                    break;
                }
                ch = paragraph.charAt(j);
            }

            String word = paragraph.substring(i, j).toLowerCase();
            if (!banSet.contains(word)) {
                Integer cnt = stat.get(word);
                if (cnt == null) {
                    cnt = 0;
                }
                stat.put(word, ++cnt);
            }
            i = j;
            while (i < paragraph.length()) {
                ch = paragraph.charAt(i);
                if (!set.contains(ch)) {
                    break;
                }
                i++;
            }
        }
        //System.out.println(stat);
        int max = 0;
        String most = null;
        for (Map.Entry<String, Integer> entry : stat.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                most = entry.getKey();
            }
        }
        return most;
    }

    @Test
    public void rotatedDigitsTest() {
        System.out.println(rotatedDigits(10));
    }

    /**
     * 788. 旋转数字 一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转 0，1，8旋转后仍然是它们自己 2 5 / 6 9 可以互相旋转成对方 其他数字旋转以后不是有效数字
     *
     * @param N 正整数
     * @return 计算从 1 到 N 中有多少个数 X 是好数
     */
    public int rotatedDigits(int N) {
        Map<Integer, Integer> rotateMap = new HashMap<>();
        rotateMap.put(0, 0);
        rotateMap.put(1, 1);
        rotateMap.put(8, 8);
        rotateMap.put(2, 5);
        rotateMap.put(5, 2);
        rotateMap.put(6, 9);
        rotateMap.put(9, 6);
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            int n = i;
            int rotatedNum = 0;
            int multi = 1;
            while (n != 0) {
                int m = n % 10;
                Integer mm = rotateMap.get(m);
                if (mm == null) {
                    rotatedNum = 0;
                    break;
                }
                rotatedNum = mm * multi + rotatedNum;
                n /= 10;
                multi *= 10;
            }
            //System.out.println(i + " " + rotatedNum);
            if (rotatedNum != 0 && rotatedNum != i) {
                cnt++;
            }

        }

        return cnt;
    }

    @Test
    public void countBinarySubstringsTest() {
        System.out.println(countBinarySubstrings("00110011"));
        System.out.println(countBinarySubstrings("10101"));
        System.out.println(countBinarySubstrings("1010001"));
        System.out.println(countBinarySubstrings("11110000"));
    }

    /**
     * 696. 计数二进制子串 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。 重复出现的子串要计算它们出现的次数 找出01 或者10 ，从中心向外扩
     *
     * @param s 字符串
     * @return 出现的次数
     */
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int cnt = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                i++;
            }
            if (i == s.length() - 1) {
                return cnt;
            }
            cnt += countExtend(s, i, i + 1);

        }

        return cnt;
    }

    private int countExtend(String s, int i, int j) {
        int cnt = 0;
        char begin = s.charAt(i);
        char end = s.charAt(j);
        while (i >= 0 && j < s.length()) {
            if (begin == s.charAt(i) && s.charAt(i) != s.charAt(j) && end == s.charAt(j)) {
                cnt++;
            } else {
                break;
            }
            i--;
            j++;
        }
        return cnt;
    }

    @Test
    public void toGoatLatinTest() {
        System.out.println(toGoatLatin("apple"));
        System.out.println(toGoatLatin("goat"));
        System.out.println(toGoatLatin("I speak Goat Latin"));
        System.out.println(toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }

    /**
     * 824. 山羊拉丁文 给定一个由空格分割单词的句子 S。每个单词只包含大写或小写字母。
     *
     * @param S 空格分割的句子字符串
     * @return string
     */
    public String toGoatLatin(String S) {
        String[] words = S.split("\\s");
        //System.out.println(Arrays.toString(words));
        StringBuilder builder = new StringBuilder();
        Set<Character> ae = new HashSet<>();
        ae.add('a');
        ae.add('A');
        ae.add('e');
        ae.add('E');
        ae.add('i');
        ae.add('I');
        ae.add('o');
        ae.add('O');
        ae.add('u');
        ae.add('U');
        StringBuilder abuilder = new StringBuilder("a");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char head = word.charAt(0);
            if (ae.contains(head)) {
                builder.append(word);
            } else {
                builder.append(word.substring(1)).append(head);
            }
            builder.append("ma");
            builder.append(abuilder);
            if (i < words.length - 1) {
                builder.append(" ");
            }
            abuilder.append("a");
        }

        return builder.toString();
    }

    @Test
    public void compressTest() {
        System.out.println(compress(new char[] {'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        System.out.println(compress(new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
        System.out.println(compress(new char[] {'a', 'b', 'c'}));
    }

    /**
     * 443. 压缩字符串 给定一组字符，使用原地算法将其压缩。 压缩后的长度必须始终小于或等于原数组长度
     *
     * @param chars 输入字符数组
     * @return 在完成原地修改输入数组后，返回数组的新长度
     */
    public int compress(char[] chars) {
        if (chars == null) {
            return 0;
        }
        if (chars.length <= 1) {
            return chars.length;
        }

        int len = 0;
        int i = 0;
        int num = 0;
        while (i < chars.length) {
            char ch = chars[i];
            int j = i;
            while (j < chars.length && ch == chars[j]) {
                j++;
                num++;
            }
            i = j;

            chars[len] = ch;
            if (num == 1) {
                len++;
            } else {
                len++;

                String numStr = String.valueOf(num);
                for (int n = 0; n < numStr.length(); n++) {
                    chars[len] = numStr.charAt(n);
                    len++;
                }
            }
            num = 0;

        }
        //System.out.println(Arrays.toString(chars));
        return len;
    }

    @Test
    public void validPalindromeTest() {
        System.out.println(validPalindrome("aba"));
        System.out.println(validPalindrome("abca"));
        System.out.println(
                validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));

        System.out.println(isPalindrome("aba", 0, 2));
        System.out.println(isPalindrome("abba", 0, 3));
    }

    /**
     * 680. 验证回文字符串 Ⅱ 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     *
     * @param s 字符串
     * @return 是否回文
     */
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        int del = 0;
        while (i < j) {
            char chi = s.charAt(i);
            char chj = s.charAt(j);
            if (chi != chj) {
                del++;
                if (del > 1) {
                    return false;
                }
                if (isPalindrome(s, i + 1, j)) {
                    return true;
                } else { return isPalindrome(s, i, j - 1); }
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        return i >= j;
    }

    @Test
    public void repeatedStringMatchTest() {
        System.out.println(repeatedStringMatch("abcd", "cdabcdab"));
        System.out.println(repeatedStringMatch("abababaaba", "aabaaba"));
        System.out.println(repeatedStringMatch("baa", "abaab"));
        System.out.println(repeatedStringMatch("a", "aa"));
    }

    /**
     * 686. 重复叠加字符串匹配 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1 模式匹配问题
     *
     * @param A 重复叠加字符串
     * @param B 目标字符串
     * @return A重复次数
     */
    public int repeatedStringMatch(String A, String B) {
        if (A.length() >= B.length() && A.contains(B)) {
            return 1;
        }
        Map<Character, List<Integer>> dic = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);
            List<Integer> list = dic.get(A.charAt(i));
            if (list == null) {
                list = new ArrayList<>();
                dic.put(ch, list);
            }
            list.add(i);
        }

        List<Integer> indexs = dic.get(B.charAt(0));
        if (indexs == null) {
            return -1;
        }
        for (Integer index : indexs) {
            int cnt = checkContains(A, B, index);
            if (cnt > 0) {
                return cnt;
            }
        }
        return -1;
    }

    /**
     * 判断A以begin开始，循环若干次是否包含B字符串
     */
    private int checkContains(String A, String B, int begin) {
        int i = 0;
        int cnt = 1;
        while (begin < A.length() && i < B.length()) {
            char chA = A.charAt(begin);
            char chB = B.charAt(i);
            if (chA != chB) {
                break;
            }
            //边界条件判断 begin 和 i都需要，B后续还有字符，并且begin遍历到A末尾
            if (begin == A.length() - 1 && i < B.length() - 1) {
                begin = 0;
                cnt++;
            } else {
                begin++;
            }
            i++;

        }
        return i == B.length() ? cnt : -1;
    }

    @Test
    public void maxAreaOfIslandTest() {

    }

    /**
     * 695. 岛屿的最大面积
     *
     * @param grid 包含0/1数组
     * @return 最大岛屿的面积
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = 1;
                    grid[i][j] = 0;
                    // TODO: 2018/6/19

                }
            }
        }
        return max;
    }

    @Test
    public void judgeCircleTest() {
        System.out.println(judgeCircle("RLUD"));
        System.out.println(judgeCircle("RRUD"));
    }

    /**
     * 657. 判断路线成圈 机器人有效的动作有 R（右），L（左），U（上）和 D（下）。输出应为 true 或 false，表示机器人移动路线是否成圈
     *
     * @param moves 移动动作
     * @return true/false
     */
    public boolean judgeCircle(String moves) {
        int v = 0;
        int h = 0;
        for (int i = 0; i < moves.length(); i++) {
            Character c = moves.charAt(i);
            switch (c) {
                case 'R':
                    h++;
                    break;
                case 'L':
                    h--;
                    break;
                case 'U':
                    v++;
                    break;
                case 'D':
                    v--;
                    break;
                default:
                    break;
            }
        }
        return v == 0 && h == 0;
    }

    @Test
    public void imageSmootherTest() {
        int[][] result = imageSmoother(new int[][] {
                new int[] {1, 1, 1},
                new int[] {1, 0, 1},
                new int[] {1, 1, 1},
        });
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }

    /**
     * 661. 图片平滑器
     *
     * @param M 包含整数的二维矩阵 M 表示一个图片的灰度
     * @return 平均灰度矩阵
     */
    public int[][] imageSmoother(int[][] M) {
        int row = M.length;
        int col = M[0].length;
        int[][] result = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int sum = M[i][j];
                int cnt = 1;
                if (i > 0) {
                    sum += M[i - 1][j];
                    cnt++;
                }
                if (i < row - 1) {
                    sum += M[i + 1][j];
                    cnt++;
                }

                if (j > 0) {
                    sum += M[i][j - 1];
                    cnt++;
                }

                if (j < col - 1) {
                    sum += M[i][j + 1];
                    cnt++;
                }
                //四个斜角数字
                if (i > 0 && j > 0) {
                    sum += M[i - 1][j - 1];
                    cnt++;
                }
                if (i > 0 && j < col - 1) {
                    sum += M[i - 1][j + 1];
                    cnt++;
                }
                if (i < row - 1 && j > 0) {
                    sum += M[i + 1][j - 1];
                    cnt++;
                }
                if (i < row - 1 && j < col - 1) {
                    sum += M[i + 1][j + 1];
                    cnt++;
                }

                result[i][j] = sum / cnt;
            }
        }

        return result;
    }

    @Test
    public void isOneBitCharacterTest() {
        System.out.println(isOneBitCharacter(new int[] {1, 0, 0}));
        System.out.println(isOneBitCharacter(new int[] {1, 1, 1, 0}));
    }

    /**
     * 717. 1比特与2比特字符 第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示 给定的字符串总是由0结束
     *
     * @param bits 1/0
     * @return 最后一个字符是否必定为一个一比特字符
     */
    public boolean isOneBitCharacter(int[] bits) {
        if (bits.length == 1) {
            return true;
        }
        int len = bits.length;
        int i = 0;
        boolean result = false;
        while (i < len) {
            if (bits[i] == 0) {
                i++;
                result = true;
            } else {
                i += 2;
                result = false;
            }
        }
        return result;

    }

    @Test
    public void dominantIndexTest() {
        System.out.println(dominantIndex(new int[] {3, 6, 1, 0}));
        System.out.println(dominantIndex(new int[] {1, 2, 3, 4}));
    }

    /**
     * 747. 至少是其他数字两倍的最大数 查找数组中的最大元素是否至少是数组中每个其他数字的两倍
     *
     * @param nums 数组
     * @return index or -1
     */
    public int dominantIndex(int[] nums) {
        int index = 0;
        int max = nums[index];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != max && nums[i] << 1 > max) {
                return -1;
            }
        }
        return index;
    }

    @Test
    public void minCostClimbingStairsTest() {
        System.out.println(minCostClimbingStairs(new int[] {10, 15, 20}));
        System.out.println(minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));

    }

    /**
     * 746. 使用最小花费爬楼梯 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯
     *
     * @param cost 非负数的体力花费值
     * @return 最低花费体力
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        if (len == 2) {
            return Math.min(cost[0], cost[1]);
        }

        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], cost[i - 1] + dp[i - 1]);
        }
        return dp[len];
    }

    public int minCostClimbingStairs2(int[] cost) {
        int dp1 = cost[0];
        int dp2 = cost[1];
        int minSum = 0;

        for (int i = 2; i < cost.length + 1; i++) {
            minSum = Math.min(dp1, dp2);
            if (i == cost.length) { break; }
            dp1 = dp2;
            dp2 = minSum + cost[i];
        }

        return minSum;
    }

    @Test
    public void findShortestSubArrayTest() {
        System.out.println(findShortestSubArray(new int[] {1, 2, 2, 3, 1}));
        System.out.println(findShortestSubArray(new int[] {1, 2, 2, 3, 1, 4, 2}));
    }

    /**
     * 697. 数组的度 数组的度的定义是指数组里任一元素出现频数的最大值
     * <p>
     * 找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度
     *
     * @param nums 非空且只包含非负数的整数数组
     * @return length
     */
    public int findShortestSubArray(int[] nums) {
        class Element {
            int count;
            int i;
            int j;

            public Element(int count, int i, int j) {
                this.count = count;
                this.i = i;
                this.j = j;
            }

            @Override
            public String toString() {
                return "Element{" +
                        "count=" + count +
                        ", i=" + i +
                        ", j=" + j +
                        '}';
            }

        }
        Map<Integer, Element> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Element element = map.get(num);
            if (element == null) {
                element = new Element(1, i, i);
                map.put(num, element);
            } else {
                element.j = i;
                element.count++;
            }
        }
        Optional<Element> max = map.values().stream().max(Comparator.comparingInt(o -> o.count));
        if (!max.isPresent()) {
            return 0;
        }

        final int count = max.get().count;
        List<Element> list = map.values().stream().filter(e -> e.count == count).collect(Collectors.toList());
        int min = nums.length;
        for (Element e : list) {
            int diff = e.j - e.i + 1;
            if (diff < min) {
                min = diff;
            }
        }

        return min;
    }

    @Test
    public void largeGroupPositionsTest() {
        List<List<Integer>> result = largeGroupPositions("abbxxxxzzy");
        printListList(result);

        result = largeGroupPositions("abc");
        printListList(result);

        result = largeGroupPositions("abcdddeeeeaabbbcd");
        printListList(result);

        result = largeGroupPositions("a");
        printListList(result);
    }

    private void printListList(List<List<Integer>> list) {
        list.forEach(System.out::print);
        System.out.println();
    }

    /**
     * 830. 较大分组的位置 所有包含大于或等于三个连续字符的分组为较大分组 最终结果按照字典顺序输出
     *
     * @param S String 1 <= S.length <= 1000
     * @return 分组
     */
    public List<List<Integer>> largeGroupPositions(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        if (S.length() <= 1) {
            return result;
        }

        int i = 0;
        int len = S.length();
        while (i < len) {
            int s = i;
            char ch = S.charAt(i);
            while (i < len && ch == S.charAt(i)) {
                i++;
            }
            if (i - s >= 3) {
                List<Integer> sub = new ArrayList<>();
                sub.add(s);
                sub.add(i - 1);
                result.add(sub);
            }
        }
        return result;
    }
}
