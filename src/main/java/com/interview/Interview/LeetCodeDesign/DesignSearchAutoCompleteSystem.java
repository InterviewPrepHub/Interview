package com.interview.Interview.LeetCodeDesign;

/*

You are designing the backend of an autocomplete feature (like in search boxes).

Given:

A list of N words (dictionary).
Each word has a frequency score (indicating how often it has been typed).

You need to implement:
A method insert(word, frequency) → adds/updates word with its frequency.

A method getTopK(prefix, k) → returns the top k words that start with prefix, ordered by frequency (desc).
If frequencies are equal, order lexicographically.

 */

public class DesignSearchAutoCompleteSystem {

    //global freq map : word -> freq
    /*private final Map<String, Integer> freqByWord = new HashMap<>();

    private final AutoCompleteSearch.TrieNode root = new AutoCompleteSearch.TrieNode();

    public static void main(String[] args) {

        AutoCompleteSearch ac = new AutoCompleteSearch();
//        ac.insert("apple", 50);
        ac.insert("app", 40);
        ac.insert("apply", 30);
        ac.insert("banana", 10);
        ac.insert("bandana", 5);

        System.out.println(ac.freqByWord);

        *//*List<String> res = ac.getTopK("app", 2);
        System.out.println(res);

        List<String> res1 = ac.getTopK("ban", 3);
        System.out.println(res1);

        List<String> res3 = ac.getTopK("xyz", 2);
        System.out.println(res3);*//*
    }

    public void insert(String word, int freq) {

        Integer oldFreq = freqByWord.get(word);

        AutoCompleteSearch.TrieNode node = root;

        // Update root's candidates too (so prefix="" works if needed)
        if(oldFreq != null || oldFreq == freq) {
            node.candidates.remove(new AutoCompleteSearch.Entry(word, oldFreq));
        }
        node.candidates.add(new AutoCompleteSearch.Entry(word, freq));

        for (char ch : word.toCharArray()) {
            node.children.computeIfAbsent(ch, k -> new AutoCompleteSearch.TrieNode());
            if (oldFreq != null) {
                node.candidates.remove(new AutoCompleteSearch.Entry(word, oldFreq));
            }
            node.candidates.add(new AutoCompleteSearch.Entry(word, freq));
        }
        freqByWord.put(word, freq);
    }


    //helper class to represent each word + its frequency.
    static class Entry {
        final String word;
        final int freq;

        public Entry(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            AutoCompleteSearch.Entry entry = (AutoCompleteSearch.Entry) o;
            return freq == entry.freq && Objects.equals(word, entry.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word, freq);
        }

        @Override
        public String toString() {
            return "Entry{" + "word='" + word + '\'' + ", freq=" + freq + '}';
        }
    }

    *//*
        children → a map of char → TrieNode
                   Each edge corresponds to one character.
                   Example: to store "apple", you create nodes a → p → p → l → e.

        candidates → a sorted set of words under this node’s subtree
                   Sorted by frequency (descending) and then lex order (ascending).
                   This allows quick retrieval of “top-k” completions for any prefix.
                   Example: if this node represents prefix "app", its candidates might be:
                   ["apple"(50), "app"(40), "apply"(30)]

        Insert “app”, freq=40
        Walk a → p → p.
        Add Entry("app", 40) into candidates at root, a, ap, app.
        At each node, add Entry("app",40)
        root.candidates = { "apple"(40) }
        a.candidates    = { "apple"(40) }

(root)
 └── 'a'
      └── 'p'
           └── 'p'
                ├── 'l'
                │     └── 'e'
                │          (candidates: { "apple"(50) })
                │
                (candidates: { "apple"(50), "app"(40) })

        Candidates at Key Nodes

        root.candidates: { "apple"(50), "app"(40) }
        "a".candidates: { "apple"(50), "app"(40) }
        "ap".candidates: { "apple"(50), "app"(40) }
        "app".candidates: { "apple"(50), "app"(40) }
        "appl".candidates: { "apple"(50) }
        "apple".candidates: { "apple"(50) }

     *//*
    static class TrieNode {
        Map<Character, AutoCompleteSearch.TrieNode> children = new HashMap<>();
        TreeSet<AutoCompleteSearch.Entry> candidates = new TreeSet<>(
                (a, b) -> {
                    if(a.freq != b.freq) return Integer.compare(b.freq, a.freq);
                    return a.word.compareTo(b.word);
                }
        );
    }*/

    /*Trie root;

    DesignSearchAutoCompleteSystem() {
        root = new Trie();
    }

    public void insert(String word) {

        Trie curr = root;

        for (char c : word.toCharArray()) {
            Trie node = curr.children.get(c);
            if (node == null) {
                node = new Trie();
                curr = curr.children.put(c, node);
            }
            curr = node;
        }
        curr.endOfWord = true;
    }

    public List<String> prefixSearch(String prefix) {

        List<String> result = new ArrayList<>();
        Trie curr = root;

        for (char c : prefix.toCharArray()) {
            curr = curr.children.get(c);
            if (curr == null) return result;
        }

        //DFS from the last prefix node
        dfsTrie(curr, new StringBuilder(prefix), result);

        return result;
    }

    private void dfsTrie(Trie curr, StringBuilder path, List<String> result) {

        if (curr.endOfWord) {
            result.add(path.toString());
        }

        for (Map.Entry<Character, Trie> entry : curr.children.entrySet()) {
            path.append(entry.getKey());
            dfsTrie(entry.getValue(), path, result);
            path.deleteCharAt(path.length()-1); //backtrack
        }
    }

    public static void main(String[] args) {

        DesignSearchAutoCompleteSystem s = new DesignSearchAutoCompleteSystem();
        s.insert("icecream");
        s.insert("ironman");
        s.insert("leetcode");

        List<String> res = s.prefixSearch("i");

        for (String s1 : res) {
            System.out.println(s1);
        }

    }*/
}


/*
class Trie{

    public Map<Character, Trie> children;
    public boolean endOfWord;

    public Trie() {
        children = new HashMap<>();
        endOfWord = false;
    }

}*/
