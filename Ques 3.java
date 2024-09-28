import java.util.Arrays;
import java.util.Comparator;

class Item {
    int value, weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

class FractionalKnapsack {

    static class ItemComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b) {
            double r1 = (double) a.value / a.weight;
            double r2 = (double) b.value / b.weight;
            return Double.compare(r2, r1);
        }
    }

    public static double getMaxValue(int W, Item[] items) {
        Arrays.sort(items, new ItemComparator());

        double totalValue = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= W) {
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                int remainingWeight = W - currentWeight;
                totalValue += ((double) item.value / item.weight) * remainingWeight;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };

        int W = 50;
        double maxValue = getMaxValue(W, items);
        System.out.println("Maximum value: " + maxValue);
    }
}
