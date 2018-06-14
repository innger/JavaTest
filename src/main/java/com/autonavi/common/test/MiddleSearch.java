package com.autonavi.common.test;

import org.junit.Test;

/**
 * Created by Administrator on 2018-05-22.
 */
public class MiddleSearch {


    @Test
    public void arrayTest() {
        System.out.println(binarySearch(new int [] {1, 2, 3, 4}));
        System.out.println(binarySearch(new int [] {1, 2, 3, 2}));
        System.out.println(binarySearch(new int [] {1, 5, 3, 2}));
        System.out.println(binarySearch(new int [] {1, 2, 6, 4}));
        System.out.println(binarySearch(new int [] {2, 1, 0}));


    }

    //数组递增再递减，求最大值
    private int binarySearch(int[] arr) {
        if (arr == null) return -1;
        if (arr.length == 1) return arr[0];

        int len = arr.length;
        if (arr[0] > arr[1]) return arr[0];
        if (arr[len -1] > arr[len - 2]) return arr[len - 1];

        int i = 0;
        int j = arr.length - 1;
        int mid ;
        while (i < j) {
            mid = i + (j - i) / 2;
            if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                return arr[mid];
            } else if (arr[mid] < arr[mid + 1]) {
                i = mid + 1;
            } else if (arr[mid] > arr[mid + 1]){
                j = mid - 1;
            }
        }
        return -1;
    }

    @Test
    public void reverseTest() {
        System.out.println(reverseWord("this is a student."));
        System.out.println(reverseWord("my   name  is lilei   "));
    }

    private String reverseWord(String words) {
        int len = words.length();
        char[] arr = new char[len];
        for (int i = len -1; i >= 0; i--) {
            arr[len -1 - i] = words.charAt(i);
        }
        System.out.println(new String(arr));
        int i = 0;
        while(i < len) {
            int b = i;
            int e = i;
            while (e < len && arr[e] != ' ') {
                e++;
            }
            reverseOne(arr, b, e - 1);
            while (e < len && arr[e] == ' ') {
                e++;
            }
            i = e;
        }
        return new String(arr);
    }

    private void reverseOne(char[] arr, int i , int j) {
        while (i < j) {
            char t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            i++;
            j--;
        }
    }


}
