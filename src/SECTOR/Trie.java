package SECTOR;

public class Trie {

    private TrieNode root =
            new TrieNode();

    // Trie stores stock names character by character for fast prefix search.
    public void insert(String stockName) {

        TrieNode current =
                root;

        String upperName =
                stockName.toUpperCase();

        for(int i = 0; i < upperName.length(); i++) {

            char ch =
                    upperName.charAt(i);

            if(ch < 'A' || ch > 'Z') {

                continue;
            }

            int index =
                    ch - 'A';

            if(current.children[index] == null) {

                current.children[index] =
                        new TrieNode();
            }

            current =
                    current.children[index];
        }

        current.endOfWord =
                true;

        current.stockName =
                stockName;
    }

    public String[] searchByPrefix(String prefix) {

        TrieNode current =
                root;

        String upperPrefix =
                prefix.toUpperCase();

        for(int i = 0; i < upperPrefix.length(); i++) {

            char ch =
                    upperPrefix.charAt(i);

            if(ch < 'A' || ch > 'Z') {

                continue;
            }

            int index =
                    ch - 'A';

            if(current.children[index] == null) {

                return new String[0];
            }

            current =
                    current.children[index];
        }

        String result[] =
                new String[100];

        int count[] =
                new int[] { 0 };

        collect(current,
                result,
                count);

        String finalResult[] =
                new String[count[0]];

        for(int i = 0; i < count[0]; i++) {

            finalResult[i] =
                    result[i];
        }

        return finalResult;
    }

    private void collect(TrieNode node,
                         String result[],
                         int count[]) {

        if(node == null || count[0] == result.length) {

            return;
        }

        if(node.endOfWord) {

            result[count[0]] =
                    node.stockName;

            count[0]++;
        }

        for(int i = 0; i < node.children.length; i++) {

            collect(node.children[i],
                    result,
                    count);
        }
    }
}
