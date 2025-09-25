package com.interview.Interview.Interview2024.Arcesium;

import java.util.HashMap;

/*
You are asked to design a file system that allows you to create new paths and
associate them with different values.

The format of a path is one or more concatenated strings of the form: /
followed by one or more lowercase English letters.
For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.

Implement the FileSystem class:

bool createPath(string path, int value) Creates a new path and associates a value to it if possible and
returns true. Returns false if the path already exists or its parent path doesn't exist.

int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.

Example 1:

Input:
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output:
[null,true,1]
Explanation:
FileSystem fileSystem = new FileSystem();

d.createPath("/a", 1); // return true
d.get("/a"); // return 1

Example 2:

Input:
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output:
[null,true,true,2,false,-1]
Explanation:
FileSystem fileSystem = new FileSystem();

d.createPath("/leet", 1); // return true
d.createPath("/leet/code", 2); // return true
d.get("/leet/code"); // return 2
d.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
d.get("/c"); // return -1 because this path doesn't exist.

Constraints:
    2 <= path.length <= 100
    1 <= value <= 109
    Each path is valid and consists of lowercase English letters and '/'.
    At most 104 calls in total will be made to createPath and get.

 */
public class DesignFileSystem {

    HashMap<String, Integer> fileValues;

    DesignFileSystem() {
        fileValues = new HashMap<>();
    }

    /**
     * Creates a new path and associates a value to it if possible.
     * @param path: the path to be created
     * @param val: path associated value
     * @return: the result of create
     */
    public boolean createPath(String path, int val) {

        if(fileValues.isEmpty()) {
            fileValues.put(path,val);
            return true;
        }

        // Check if the path already exists or its parent path doesn't exist
        if (fileValues.containsKey(path) || isValidPath(path)) {
            return false;
        }

        // Associate the value with the path
        fileValues.put(path, val);
        return true;
    }

    /**
     * Returns the value associated with path or returns -1 if the path doesn't exist.
     * @param path: the path to retrieve
     * @return: path associated value
     */
    public int get(String path) {
        // Check if the path exists in the fileValues map
        return fileValues.getOrDefault(path, -1);
    }

    // Helper method to check if the parent path of a given path exists
    private boolean isValidPath(String path) {
        int lastSlashIndex = path.lastIndexOf('/');
        if (lastSlashIndex == -1) {
            // No parent path, it is the root
            return true;
        }

        // Check if the parent path exists
        String parentPath = path.substring(0, lastSlashIndex);
        return fileValues.containsKey(parentPath);
    }

    public static void main(String[] args) {
        DesignFileSystem fileSystem = new DesignFileSystem();

        System.out.println(fileSystem.createPath("/a", 1)); // return true
        System.out.println(fileSystem.get("/a")); // return 1

        System.out.println(fileSystem.createPath("/leet", 1)); // return true
        System.out.println(fileSystem.createPath("/leet/code", 2)); // return true
        System.out.println(fileSystem.get("/leet/code")); // return 2
        System.out.println(fileSystem.createPath("/c/d", 1)); // return false because the parent path "/c" doesn't exist.
        System.out.println(fileSystem.get("/c")); // return -1 because this path doesn't exist.
    }
}
