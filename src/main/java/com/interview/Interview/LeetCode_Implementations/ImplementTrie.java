package com.interview.Interview.LeetCode_Implementations;

import com.interview.Interview.Pepcoding.Node.TrieNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*

Operations:

insert
search
delete
 */
public class ImplementTrie {

    private final TrieNode root;

    ImplementTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            TrieNode node = curr.children.get(c);
            if (node == null) {
                node = new TrieNode();
                curr.children.put(c, node);
            }

            curr = node;
        }
        curr.endOfWord = true;
    }

    public boolean search(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            TrieNode node = curr.children.get(c);

            if (node == null) {
                return false;
            }
            curr = node;
        }
        return curr.endOfWord;
    }

    public void delete(String s) {

        delete(root, s, 0);
    }

    public boolean delete(TrieNode curr, String s, int index) {

        if (s.length() == index) {
            //Base case: end of word
            if (!curr.endOfWord) {
                return false;   //word doesn't exist
            }
            curr.endOfWord = false;

            //if curr node has no children it can be deleted
            return curr.children.size() == 0;
        }

        char c = s.charAt(index);

        TrieNode child = curr.children.get(c);

        if(child == null) {
            return false;   //word not found
        }

        boolean shouldDeleteCurrentNode = delete(child, s, index+1);

        if(shouldDeleteCurrentNode) {
            curr.children.remove(c);
            //return true if no more children and not end of another word
            return curr.children.isEmpty() && !curr.endOfWord;
        }

        return false;
    }

    public List<String> prefixSearch(String prefix) {

        List<String> result = new ArrayList<>();
        TrieNode curr = root;

        for (char c : prefix.toCharArray()) {
            curr = curr.children.get(c);
            if (curr == null) return result;
        }

        //DFS from the last prefix node
        dfsTrie(curr, new StringBuilder(prefix), result);

        return result;
    }

    private void dfsTrie(TrieNode curr, StringBuilder path, List<String> result) {

        if (curr.endOfWord) {
            result.add(path.toString());
        }

        for (Map.Entry<Character, TrieNode> entry : curr.children.entrySet()) {
            path.append(entry.getKey());
            dfsTrie(entry.getValue(), path, result);
            path.deleteCharAt(path.length()-1); //backtrack
        }
    }



    public static void main(String[] args) {

        ImplementTrie t = new ImplementTrie();
        t.insert("abc");
        t.insert("abgl");
        t.insert("cdf");
        t.insert("abcd");

        System.out.println("Search abc: " + t.search("abc"));   // true
        System.out.println("Search abgl: " + t.search("abgl")); // true

        System.out.println(t.prefixSearch("abc"));

        t.delete("abc");
        System.out.println("Search abc after delete: " + t.search("abc"));   // false
        System.out.println("Search abcd still exists: " + t.search("abcd")); // true


    }

}


