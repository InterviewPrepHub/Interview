package com.interview.Interview.LeetCodeDesign;

import com.interview.Interview.Interview2025.Walmart.Round1.AutoCompleteSearch;

import java.util.*;

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
public class DesignAutoCompleteSearch {

    //global freq map : word -> freq
    private final Map<String, Integer> freqByWord = new HashMap<>();

    private final TrieNode root = new TrieNode();

    public static void main(String[] args) {

        DesignAutoCompleteSearch ac = new DesignAutoCompleteSearch();
//        ac.insert("apple", 50);
        ac.insert("app", 40);
        ac.insert("apply", 30);
        ac.insert("banana", 10);
        ac.insert("bandana", 5);

        System.out.println(ac.freqByWord);

        /*List<String> res = ac.getTopK("app", 2);
        System.out.println(res);

        List<String> res1 = ac.getTopK("ban", 3);
        System.out.println(res1);

        List<String> res3 = ac.getTopK("xyz", 2);
        System.out.println(res3);*/
    }

    public void insert(String word, int freq) {

        Integer oldFreq = freqByWord.get(word);

        TrieNode node = root;

        // Update root's candidates too (so prefix="" works if needed)
        if(oldFreq != null || oldFreq == freq) {
            node.candidates.remove(new Entry(word, oldFreq));
        }
        node.candidates.add(new Entry(word, freq));

        for (char ch : word.toCharArray()) {
            node.children.computeIfAbsent(ch, k -> new TrieNode());
            if (oldFreq != null) {
                node.candidates.remove(new Entry(word, oldFreq));
            }
            node.candidates.add(new Entry(word, freq));
        }
        freqByWord.put(word, freq);
    }

    /** Return top K words for a given prefix, ordered by (freq desc, word asc). */
    public List<String> getTopK(String prefix, int k) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;

        // empty prefix ("") -> root
        if (prefix != null && !prefix.isEmpty()) {
            for (char ch : prefix.toCharArray()) {
                node = node.children.get(ch);
                if (node == null) return result; // no such prefix
            }
        }

        // iterate the TreeSet from the beginning (best first) and take k
        int count = 0;
        for (Entry e : node.candidates) {
            result.add(e.word);
            if (++count == k) break;
        }
        return result;
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
            Entry entry = (Entry) o;
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

    /*
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

     */

    /*
    Each Trie node represents a prefix (e.g., root = "", then 'a', "ap", "app", …).
    Each node keeps a sorted set of candidate words that start with that prefix, sorted by frequency (highest first),
    and then by word (lexicographically) to break ties.
     */
    public static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        TreeSet<Entry> candidates = new TreeSet<>(
                (a, b) -> {
                    if(a.freq != b.freq) return Integer.compare(b.freq, a.freq);
                    return a.word.compareTo(b.word);
                }
        );
    }
}
