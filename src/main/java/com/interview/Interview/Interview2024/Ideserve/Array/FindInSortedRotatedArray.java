package com.interview.Interview.Interview2024.Ideserve.Array;

public class FindInSortedRotatedArray {

    public static void main(String[] args) {
        int[] arr = {78,82,99,10,23,35,49,51,60};
        findPivot(arr);
        findInArr(arr,35);
        findInArrWithoutPivot(arr,35);
    }

    private static void findInArrWithoutPivot(int[] arr, int num) {
        int start = 0, end = arr.length-1;

        while(start<=end) {
            int mid = start + (end - start)/2;

            if(num == arr[mid]) {
                System.out.println(mid);
                break;
            }
            if (arr[start] < arr[mid]) {
                if (arr[start] <= num && arr[mid] >= num) {
                    end = mid -1;
                } else {
                    start = mid+1;
                }
            } else if (arr[mid] <= arr[end]) {
                if(arr[mid] <= num && arr[end] >= num) {
                    start = mid +1;
                } else {
                    end = mid -1;
                }
            }
        }
    }

    public static void findInArr(int[] arr, int num) {
        int n = arr.length, start = 0, end = n-1;
        int min = 0;

        while(start<=end) {
            int mid = start + (end - start)/2;

            if(arr[mid] > arr [mid+1]) {
                System.out.println(mid+1);
                min = mid+1;
                break;
            } else if(arr[start] <= arr[mid]) {
                start = mid+1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(arr[min]);


        if(num > arr[min]) {
            System.out.println("here1");
            start = min+1;
            end = n-1;

        } else {
            System.out.println("here2");
            start = 0;
            end = min;
        }

        System.out.println("start: "+start+" end: "+end);

        while(start <= end) {
            int mid = start + (end - start)/2;

            if(arr[mid] == num) {
                System.out.println(mid);
                break;
            } else if(arr[mid] < num) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }

    }

    public static void findPivot(int[] arr) {
        int n = arr.length;
        if(arr[0] < arr[n-1]) {
            System.out.println("arr is not rotated");
        }

        int start = 0;
        int end = n-1;

        while(start<=end) {
            int mid = start + (end - start)/2;

            if(arr[mid] > arr [mid+1]) {
                System.out.println(mid+1);
                break;
            } else if(arr[start] <= arr[mid]) {
                start = mid+1;
            } else {
                end = mid - 1;
            }
        }
    }
}
