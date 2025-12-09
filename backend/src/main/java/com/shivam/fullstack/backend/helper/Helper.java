package com.shivam.fullstack.backend.helper;
import com.shivam.fullstack.backend.Exception.MyException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
@Service
@Scope(scopeName = "Prototype")
public class Helper {
    public static int[] split_arr(String numstr,int len) throws Exception{
        String[] numarr=numstr.trim().split(",");
        int[] delete_arr=new int[numarr.length];
        int i=0;
        try {
            if (numarr.length <= len) {
                for (String num : numarr) {
                    delete_arr[i] = Integer.parseInt(num.trim());
                    i++;
                }
            }else {
                throw new MyException(" Rong Formet Of comma Please Enter right no of page");
            }
        } catch (Exception e) {

            throw new MyException(" Rong Formet Of comma Please Enter right no of page");
        }
        return delete_arr;
    }
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    public static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] >= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}
