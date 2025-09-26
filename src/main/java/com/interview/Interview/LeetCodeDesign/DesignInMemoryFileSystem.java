package com.interview.Interview.LeetCodeDesign;

import java.util.*;

/*
Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

FileSystem() Initializes the object of the system.
List<String> ls(String path)
If path is a file path, returns a list that only contains this file's name.
If path is a directory path, returns the list of file and directory names in this directory.
The answer should in lexicographic order.
void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist.
If the middle directories in the path do not exist, you should create them as well.
void addContentToFile(String filePath, String content)
If filePath does not exist, creates that file containing given content.
If filePath already exists, appends the given content to original content.
String readContentFromFile(String filePath) Returns the content in the file at filePath.


Example 1:

Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/"); // return []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/"); // return ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"

Design a file system that allow search by file name
 */
public class DesignInMemoryFileSystem {

    private final TrieNode root;

    public DesignInMemoryFileSystem() {
        root = new TrieNode("/", false);
    }

    // -------- API --------

    public List<String> ls(String path) {
        TrieNode node = search(path);
        List<String> out = new ArrayList<>();
        if (node == null) return out;

        if (node.isFile) {
            // return only the file name
            out.add(lastToken(path));
        } else {
            out.addAll(node.children.keySet());
            Collections.sort(out);
        }
        return out;
    }

    public void mkdir(String path) {
        insert(path, false); // create directories along the path
    }

    public void addContentToFile(String filePath, String content) {
        TrieNode node = insert(filePath, true); // create if not present, mark file
        node.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        TrieNode node = search(filePath);
        return (node != null && node.isFile) ? node.content.toString() : "";
    }

    // -------- Internals --------

    // Create nodes along path; mark last node as file/dir per isFile
    private TrieNode insert(String path, boolean isFile) {
        String[] parts = tokens(path);
        TrieNode cur = root;
        for (String p : parts) {
            cur.children.putIfAbsent(p, new TrieNode(p, false));
            cur = cur.children.get(p);
        }
        // mark the terminal node appropriately
        cur.isFile = cur.isFile || isFile; // if ever marked file, keep it a file
        return cur;
    }

    // Traverse; return null if any component missing
    private TrieNode search(String path) {
        if ("/".equals(path)) return root;
        String[] parts = tokens(path);
        TrieNode cur = root;
        for (String p : parts) {
            cur = cur.children.get(p);
            if (cur == null) return null;
        }
        return cur;
    }

    // Normalize path to components, skipping empty tokens (handles "/", "//", "/a/b/", etc.)
    private static String[] tokens(String path) {
        if (path == null || path.isEmpty() || "/".equals(path)) return new String[0];
        return Arrays.stream(path.split("/"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    private static String lastToken(String path) {
        String[] t = tokens(path);
        return t.length == 0 ? "/" : t[t.length - 1];
    }

    // -------- Node --------
    static class TrieNode {
        final String name;                    // directory/file name at this level
        boolean isFile;                       // true if file
        final StringBuilder content;          // only used if isFile
        final Map<String, TrieNode> children; // name -> child

        TrieNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            this.content = new StringBuilder();
            this.children = new HashMap<>();
        }
    }

    // -------- Demo --------
    public static void main(String[] args) {
        DesignInMemoryFileSystem fs = new DesignInMemoryFileSystem();
        System.out.println(fs.ls("/"));                // []

        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");

        System.out.println(fs.ls("/"));                // ["a"]
        System.out.println(fs.ls("/a/b/c"));           // ["d"]
        System.out.println(fs.ls("/a/b/c/d"));         // ["d"]
        System.out.println(fs.readContentFromFile("/a/b/c/d")); // "hello"

        // trailing slash & double slash are handled:
        System.out.println(fs.ls("/a/b/c/"));          // ["d"]
        fs.mkdir("//x///y//z/");
        System.out.println(fs.ls("/x"));               // ["y"]
    }
}
