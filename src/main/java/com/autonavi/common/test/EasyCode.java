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
        result.forEach(
                l -> System.out.println(l)
        );
    }


    public List<List<Integer>> largeGroupPositions(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        if (S.length() <= 1) {
            List<Integer> sub = new ArrayList<>();
            sub.add(0);
            result.add(sub);
            return result;
        }

        class Node implements Comparable<Node> {
            char ch;
            int i;
            int j;

            public Node(char ch) {
                this.ch = ch;
            }

            public Node(char ch, int i, int j) {
                this.ch = ch;
                this.i = i;
                this.j = j;
            }

            @Override
            public int compareTo(Node o) {
                return this.ch - o.ch;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "ch=" + ch +
                        ", i=" + i +
                        ", j=" + j +
                        '}';
            }
        }

        List<Node> list = new ArrayList<>();

        int i = 0;
        while (i < S.length()) {
            char ch = S.charAt(0);
            int j = 1;

            list.add(new Node(S.charAt(i)));
        }
        Collections.sort(list);
        list.forEach(
                node -> {
                    List<Integer> sub = new ArrayList<>();
                    sub.add(node.i);sub.add(node.j);
                    result.add(sub);
                }
        );
        return result;
    }
}
