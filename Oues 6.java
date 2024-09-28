import java.util.Arrays;

public class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> Integer.compare(b[1], a[1]));

        int totalUnits = 0;
        int totalBoxes = 0;

        for (int[] boxType : boxTypes) {
            int numBoxes = boxType[0];
            int unitsPerBox = boxType[1];

            if (totalBoxes + numBoxes <= truckSize) {
                totalUnits += numBoxes * unitsPerBox;
                totalBoxes += numBoxes;
            } else {
                int remainingSpace = truckSize - totalBoxes;
                totalUnits += remainingSpace * unitsPerBox;
                break;
            }
        }

        return totalUnits;
    }
}
