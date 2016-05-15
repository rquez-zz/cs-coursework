import java.util.Scanner;
import java.util.jar.Pack200;

public class avl {

    static boolean IS_REMOVED_FLAG = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {

            int numInserts = scanner.nextInt();

            node node = null;

            for (int j = 0; j < numInserts; j++) {

                int data = scanner.nextInt();

                node = insert(node, data, i);

                if (IS_REMOVED_FLAG) {
                    while (j < numInserts - 1) {
                        scanner.nextInt();
                        j++;
                    }
                }

            }

            if (!IS_REMOVED_FLAG) {
                System.out.println("Tree #" + (i + 1) + ": KEEP");
            } else
                IS_REMOVED_FLAG = false;
        }
    }

    private static boolean isBalanced(node node) {

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        int diff = Math.abs(leftHeight - rightHeight);
        return diff < 2;
    }

    public static node insert(node node, int data, int i) {

        if (node == null) {
            node = new node(data, null, null);
        } else if (node.value < data) {
            node.right = insert(node.right, data, i);
            if (IS_REMOVED_FLAG) return null;
            if(!isBalanced(node)) {
                IS_REMOVED_FLAG = true;
                System.out.println("Tree #" + (i+1) + ": REMOVE");
                return null;
            }
        } else {
            node.left = insert(node.left, data, i);
            if (IS_REMOVED_FLAG) return null;
            if(!isBalanced(node)) {
                IS_REMOVED_FLAG = true;
                System.out.println("Tree #" + (i+1) + ": REMOVE");
                return null;
            }
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        return node;
    }

    private static int getHeight(node node) {
        if (node == null)
            return 0;
        return node.height;
    }
}

class node {

    int height;
    int value;
    node left;
    node right;

    public node (int value, node left, node right) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.height = 0;
    }
}
