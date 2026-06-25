package STOCK;

public class BST {

    StockNode root;

    public StockNode insert(StockNode root,
                            String name,
                            int price) {

        if(root == null) {

            return new StockNode(name, price);
        }

        if(name.compareToIgnoreCase(root.stockName) < 0) {

            root.left =
                    insert(root.left, name, price);
        }

        else if(name.compareToIgnoreCase(root.stockName) > 0) {

            root.right =
                    insert(root.right, name, price);
        }

        return root;
    }

    public void inorder(StockNode root) {

        if(root != null) {

            inorder(root.left);

            System.out.println(
                    root.stockName
                    + " -> "
                    + root.stockPrice);

            inorder(root.right);
        }
    }

    public StockNode search(StockNode root,
                            String name) {

        if(root == null
                || root.stockName.equalsIgnoreCase(name)) {

            return root;
        }

        if(name.compareToIgnoreCase(root.stockName) < 0) {

            return search(root.left, name);
        }

        return search(root.right, name);
    }
}