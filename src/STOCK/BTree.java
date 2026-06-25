package STOCK;

public class BTree {

    private static final int MIN_DEGREE = 2;

    private BTreeNode root;

    private int size;

    public void insert(StockRecord record) {

        if(root == null) {

            root =
                    new BTreeNode(true);

            root.records[0] = record;

            root.count = 1;

            size = 1;

            return;
        }

        StockRecord existingRecord =
                search(record.stockName);

        if(existingRecord != null) {

            existingRecord.oldPrice =
                    record.oldPrice;

            existingRecord.currentPrice =
                    record.currentPrice;

            existingRecord.highestPrice =
                    record.highestPrice;

            existingRecord.sector =
                    record.sector;

            return;
        }

        if(root.count == (2 * MIN_DEGREE) - 1) {

            BTreeNode newRoot =
                    new BTreeNode(false);

            newRoot.children[0] = root;

            splitChild(newRoot, 0, root);

            int index = 0;

            if(compare(record.stockName,
                    newRoot.records[0].stockName) > 0) {

                index++;
            }

            insertNonFull(newRoot.children[index],
                    record);

            root = newRoot;
        }

        else {

            insertNonFull(root, record);
        }

        size++;
    }

    public StockRecord search(String stockName) {

        return search(root,
                stockName);
    }

    public void inorder() {

        int serialNumber[] =
                new int[] { 1 };

        inorder(root,
                serialNumber);
    }

    public boolean delete(String stockName) {

        StockRecord records[] =
                toArray();

        boolean deleted =
                false;

        root = null;

        size = 0;

        for(int i = 0; i < records.length; i++) {

            if(records[i].stockName.equalsIgnoreCase(stockName)) {

                deleted = true;
            }

            else {

                insert(records[i]);
            }
        }

        return deleted;
    }

    public StockRecord[] toArray() {

        StockRecord records[] =
                new StockRecord[size];

        int index[] =
                new int[] { 0 };

        fillArray(root,
                records,
                index);

        return records;
    }

    public int size() {

        return size;
    }

    private StockRecord search(BTreeNode node,
                               String stockName) {

        if(node == null) {

            return null;
        }

        int index = 0;

        while(index < node.count
                && compare(stockName,
                node.records[index].stockName) > 0) {

            index++;
        }

        if(index < node.count
                && node.records[index].stockName.equalsIgnoreCase(stockName)) {

            return node.records[index];
        }

        if(node.leaf) {

            return null;
        }

        return search(node.children[index],
                stockName);
    }

    private void inorder(BTreeNode node,
                         int serialNumber[]) {

        if(node == null) {

            return;
        }

        for(int i = 0; i < node.count; i++) {

            if(!node.leaf) {

                inorder(node.children[i],
                        serialNumber);
            }

            System.out.printf(
                    "%-10s %-25s Rs.%-12d %-15s\n",
                    serialNumber[0],
                    node.records[i].stockName,
                    node.records[i].currentPrice,
                    node.records[i].sector);

            serialNumber[0]++;
        }

        if(!node.leaf) {

            inorder(node.children[node.count],
                    serialNumber);
        }
    }

    private void fillArray(BTreeNode node,
                           StockRecord records[],
                           int index[]) {

        if(node == null) {

            return;
        }

        for(int i = 0; i < node.count; i++) {

            if(!node.leaf) {

                fillArray(node.children[i],
                        records,
                        index);
            }

            records[index[0]] =
                    node.records[i];

            index[0]++;
        }

        if(!node.leaf) {

            fillArray(node.children[node.count],
                    records,
                    index);
        }
    }

    private void insertNonFull(BTreeNode node,
                               StockRecord record) {

        int index =
                node.count - 1;

        if(node.leaf) {

            while(index >= 0
                    && compare(record.stockName,
                    node.records[index].stockName) < 0) {

                node.records[index + 1] =
                        node.records[index];

                index--;
            }

            if(index >= 0
                    && node.records[index].stockName.equalsIgnoreCase(record.stockName)) {

                node.records[index] = record;

                return;
            }

            node.records[index + 1] = record;

            node.count++;
        }

        else {

            while(index >= 0
                    && compare(record.stockName,
                    node.records[index].stockName) < 0) {

                index--;
            }

            index++;

            if(node.children[index].count == (2 * MIN_DEGREE) - 1) {

                splitChild(node,
                        index,
                        node.children[index]);

                if(compare(record.stockName,
                        node.records[index].stockName) > 0) {

                    index++;
                }
            }

            insertNonFull(node.children[index],
                    record);
        }
    }

    private void splitChild(BTreeNode parent,
                            int childIndex,
                            BTreeNode fullChild) {

        BTreeNode newChild =
                new BTreeNode(fullChild.leaf);

        newChild.count =
                MIN_DEGREE - 1;

        for(int j = 0; j < MIN_DEGREE - 1; j++) {

            newChild.records[j] =
                    fullChild.records[j + MIN_DEGREE];
        }

        if(!fullChild.leaf) {

            for(int j = 0; j < MIN_DEGREE; j++) {

                newChild.children[j] =
                        fullChild.children[j + MIN_DEGREE];
            }
        }

        fullChild.count =
                MIN_DEGREE - 1;

        for(int j = parent.count; j >= childIndex + 1; j--) {

            parent.children[j + 1] =
                    parent.children[j];
        }

        parent.children[childIndex + 1] =
                newChild;

        for(int j = parent.count - 1; j >= childIndex; j--) {

            parent.records[j + 1] =
                    parent.records[j];
        }

        parent.records[childIndex] =
                fullChild.records[MIN_DEGREE - 1];

        parent.count++;
    }

    private int compare(String first,
                        String second) {

        return first.compareToIgnoreCase(second);
    }

    private static class BTreeNode {

        StockRecord records[] =
                new StockRecord[(2 * MIN_DEGREE) - 1];

        BTreeNode children[] =
                new BTreeNode[2 * MIN_DEGREE];

        int count;

        boolean leaf;

        BTreeNode(boolean leaf) {

            this.leaf = leaf;
        }
    }
}
