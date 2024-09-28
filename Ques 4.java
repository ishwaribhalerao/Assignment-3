import java.util.*;

class MinHeap {
    private List<Node> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    private void swap(int i, int j) {
        Node temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(int key, int vertex) {
        heap.add(new Node(key, vertex));
        bubbleUp();
    }

    private void bubbleUp() {
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(parentIndex).key > heap.get(index).key) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public Node extractMin() {
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        Node min = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        bubbleDown();
        return min;
    }

    private void bubbleDown() {
        int index = 0;
        int length = heap.size();
        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int swapIndex = -1;

            if (leftChildIndex < length && heap.get(leftChildIndex).key < heap.get(index).key) {
                swapIndex = leftChildIndex;
            }

            if (rightChildIndex < length &&
                    (swapIndex == -1 && heap.get(rightChildIndex).key < heap.get(index).key) ||
                    (swapIndex != -1 && heap.get(rightChildIndex).key < heap.get(leftChildIndex).key)) {
                swapIndex = rightChildIndex;
            }

            if (swapIndex == -1) break;

            swap(index, swapIndex);
            index = swapIndex;
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    static class Node {
        int key, vertex;

        public Node(int key, int vertex) {
            this.key = key;
            this.vertex = vertex;
        }
    }
}

public class PrimMST {

    public static void primMST(List<List<int[]>> graph) {
        int n = graph.size();
        int[] key = new int[n];
        int[] parent = new int[n];
        boolean[] inMST = new boolean[n];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        MinHeap heap = new MinHeap();
        key[0] = 0;
        heap.insert(0, 0);

        while (!heap.isEmpty()) {
            MinHeap.Node node = heap.extractMin();
            int vertex = node.vertex;
            inMST[vertex] = true;

            for (int[] adj : graph.get(vertex)) {
                int adjVertex = adj[0];
                int weight = adj[1];
                if (!inMST[adjVertex] && weight < key[adjVertex]) {
                    key[adjVertex] = weight;
                    parent[adjVertex] = vertex;
                    heap.insert(key[adjVertex], adjVertex);
                }
            }
        }

        System.out.println("Edge \tWeight");
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
        }
    }

    public static void main(String[] args) {
        List<List<int[]>> graph = new ArrayList<>();
        graph.add(Arrays.asList(new int[]{1, 2}, new int[]{3, 6}));
        graph.add(Arrays.asList(new int[]{0, 2}, new int[]{2, 3}, new int[]{3, 8}, new int[]{4, 5}));
        graph.add(Arrays.asList(new int[]{1, 3}, new int[]{4, 7}));
        graph.add(Arrays.asList(new int[]{0, 6}, new int[]{1, 8}, new int[]{4, 9}));
        graph.add(Arrays.asList(new int[]{1, 5}, new int[]{2, 7}, new int[]{3, 9}));

        primMST(graph);
    }
}
