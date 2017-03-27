package com.mygaienko.common.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enda1n on 27.03.2017.
 */
public class Permutations {

    public static void main(String[] args){
        int[] list = {1,2,3,4,5};
        List<List<Integer>> output = permute(list);
        for(List result: output){
            System.out.println(result);
        }

    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int size = factorial(nums.length);

        // add the original one to the list
        List<Integer> seq = new ArrayList<>();
        for(int a:nums){
            seq.add(a);
        }
        list.add(seq);

        // generate the next and next permutation and add them to list
        for(int i = 0;i < size - 1;i++){
            seq = new ArrayList<>();
            nextPermutation(nums);
            for(int a:nums){
                seq.add(a);
            }
            list.add(seq);
        }
        return list;
    }


    static int factorial(int n){
        return (n==1) ? 1 : n * factorial(n-1);
    }

    static void nextPermutation(int[] nums){
        int i = nums.length -1; // start from the end

        while(i > 0 && nums[i-1] >= nums[i]){
            i--;
        }

        if(i==0){
            reverse(nums,0,nums.length -1 );
        }else{

            // found the first one not in order
            int j = i;

            // found just bigger one
            while(j < nums.length && nums[j] > nums[i-1]){
                j++;
            }
            //swap(nums[i-1],nums[j-1]);
            int tmp = nums[i-1];
            nums[i-1] = nums[j-1];
            nums[j-1] = tmp;
            reverse(nums,i,nums.length-1);
        }
    }

    // reverse the sequence
    static void reverse(int[] arr,int start, int end){
        int tmp;
        for(int i = 0; i <= (end - start)/2; i++ ){
            tmp = arr[start + i];
            arr[start + i] = arr[end - i];
            arr[end - i ] = tmp;
        }
    }
}
