package com.autonavi.common.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * leetcode easy code
 * Created by Administrator on 2018-06-14.
 */
public class EasyCode {


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
     * 830. 较大分组的位置
     * 所有包含大于或等于三个连续字符的分组为较大分组
     * 最终结果按照字典顺序输出
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
