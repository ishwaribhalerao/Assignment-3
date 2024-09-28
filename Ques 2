public class MaxMin {

    static class Pair {
        int min;
        int max;
    }

    static Pair findMaxMin(int[] arr, int low, int high) {
        Pair result = new Pair();
        Pair left;
        Pair right;

        if (low == high) {
            result.max = arr[low];
            result.min = arr[low];
            return result;
        }

        if (high == low + 1) {
            if (arr[low] > arr[high]) {
                result.max = arr[low];
                result.min = arr[high];
            } else {
                result.max = arr[high];
                result.min = arr[low];
            }
            return result;
        }

        int mid = (low + high) / 2;

        left = findMaxMin(arr, low, mid);
        right = findMaxMin(arr, mid + 1, high);

        result.min = Math.min(left.min, right.min);
        result.max = Math.max(left.max, right.max);

        return result;
    }
    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 8, 7, 2, 6};

        Pair result = findMaxMin(arr, 0, arr.length - 1);

        System.out.println("Minimum element: " + result.min);
        System.out.println("Maximum element: " + result.max);
    }
}
