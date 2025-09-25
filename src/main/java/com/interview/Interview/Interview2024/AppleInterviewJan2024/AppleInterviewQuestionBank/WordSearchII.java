package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearchII {

    public static void main(String[] args) {
        WordSearchII solution = new WordSearchII();
        char[][] board = {
                {'d', 'o', 'a', 'f'},
                {'a', 'g', 'a', 'i'},
                {'d', 'c', 'a', 'n'}
        };
        List<String> words = new ArrayList<>();
        words.add("dog");
        words.add("dad");
        words.add("dgdg");
        words.add("can");
        words.add("again");

        int result = solution.wordSearch(board, words);
        System.out.println(result);
    }
    
    public int wordSearch(char[][] board, List<String> words) {
        TNode root = buildTrie(words);
        int[] result = {0};
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, result, root, visited);
            }
        }

        return result[0];
    }

    private TNode buildTrie(List<String> words) {
        TNode root = new TNode();
        for (String word : words) {
            TNode node = root;
            for (char ch : word.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TNode());
                }
                node = node.children.get(ch);
            }
            node.endOfWord = true;
        }
        return root;
    }


    public void dfs(char[][] board, int i, int j, int[] result, TNode node, boolean[][] visited) {
        char ch = board[i][j];

        if (ch == '#' || !node.children.containsKey(ch) || visited[i][j]) {
            return;
        }

        node = node.children.get(ch);
        if(node.endOfWord) {
            result[0]++;
            node.endOfWord = false;
        }
        visited[i][j] = true;

        int n = board.length;
        int m = board[0].length;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int[] dir : directions) {
            int ni = i + dir[0];
            int mi = j + dir[1];

            if (ni >=0 && mi >= 0 && ni < n && mi < m) {
                dfs(board, ni, mi, result, node, visited);
            }
        }
        visited[i][j] = false;
    }
}

class TNode {
    Map<Character, TNode> children;
    boolean endOfWord;

    TNode() {
        children = new HashMap<>();
        endOfWord = false;
    }
}
