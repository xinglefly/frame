package com.bytetime.algorithm;



/**
 * 缺陷：
 * 二分查找，是通过升序的方式查询速度比较快。
 * 降序的方式存储数据，查找的时候刚好相反。而且速度比较慢。
 * 无序的话，要通过Arrays.sort(arr);进行升序排序，然后在使用查找。
 */

@SuppressWarnings("unused")
public class BinarySearch {

    private int arr[] = null;
    private int cnt = 0;

    public static void main(String[] args)throws Exception {
        BinarySearch binary = new BinarySearch();
        binary.createArr();
        binary.forSearch(99);
        binary.binarySearch(99);
    }


    public void createArr(){
        arr = new int[100];
        for (int i=0;i<arr.length;i++){
            arr[i] = i+1;
        }
    }

    public void forSearch(int num){
        for (int i=0;i<arr.length;i++){
            cnt++;
            if (arr.length-1 != num && num !=arr[i]){
                System.out.println("没有找到");
                return;
            } else if(arr[i] == num){
                System.out.println(arr[i]+"找到了，在角标为"+i+"的地方，查找次数->"+cnt);
                return;
            }
        }
    }

    public void binarySearch(int num){
        int index = 0;
        int start = 0;
        int end = arr.length-1;
        cnt = 0;

        for (int i=0;i<arr.length;i++){
            cnt++;
            index = (start + end)/2;
            if (arr.length-1 != num){
                System.out.println("没有找到");
                return;
            } else if (arr[index] < num) start = index;
            else if (arr[index] > num) end = index;
            else{
                System.out.println(arr[index]+"找到了，在数组下标为"+i+"的地方，查找次数->"+cnt);
                return;
            }
        }
    }
}
