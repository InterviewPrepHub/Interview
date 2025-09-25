package com.interview.Interview.LeetCodeDesign;

import java.util.*;

/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 */
public class DesignSnakeGame {

    private final int height;   //no of rows
    private final int width;    //no of columns
    private final LinkedList<int[]> snake;  // stores snake body from head to tail
    private final Queue<int[]> foodQueue;
    private final Set<String> occupied; // for fast collision detection. Tracks all cells that are currently occupied by the snake’s body

    public DesignSnakeGame(int width, int height, List<List<Integer>> food) {
        this.height = height;
        this.width = width;
        this.snake = new LinkedList<>();
        this.foodQueue = new LinkedList<>();
        this.occupied = new HashSet<>();

        // Convert food list to queue
        for (List<Integer> f : food) {
            foodQueue.add(new int[]{f.get(0), f.get(1)});
        }

        // Initialize snake at (0, 0)
        snake.addFirst(new int[]{0, 0});
        occupied.add("0,0");
    }

    public int move(String direction) {
        int[] head = snake.peekFirst();
        int row = head[0], col = head[1];

        switch (direction) {
            case "U": row--; break;
            case "D": row++; break;
            case "L": col--; break;
            case "R": col++; break;
        }

        // Check out-of-bounds
        if (row < 0 || row >= height || col < 0 || col >= width) return -1;

        // Compute new head position
        String newHeadPos = row + "," + col;

        // Remove tail before collision check (because it moves)
        int[] tail = snake.pollLast();
        occupied.remove(tail[0] + "," + tail[1]);

        // Check if snake runs into itself
        if (occupied.contains(newHeadPos)) return -1;

        // Add new head
        snake.addFirst(new int[]{row, col});
        occupied.add(newHeadPos);

        // Check if there's food at the new head
        if (!foodQueue.isEmpty()) {
            int[] nextFood = foodQueue.peek();
            if (row == nextFood[0] && col == nextFood[1]) {
                // Snake eats food → grow (re-add tail)
                snake.addLast(tail);
                occupied.add(tail[0] + "," + tail[1]);
                foodQueue.poll();
            }
        }

        return snake.size() - 1; // score = length - 1
    }

    public static void main(String[] args) {
        List<List<Integer>> food = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(0, 1)
        );
        DesignSnakeGame game = new DesignSnakeGame(3, 2, food);

        System.out.println(game.move("R")); // 0
        System.out.println(game.move("D")); // 0
        System.out.println(game.move("R")); // 1 (eats food at 1,2)
        System.out.println(game.move("U")); // 1
        System.out.println(game.move("L")); // 2 (eats food at 0,1)
        System.out.println(game.move("U")); // -1 (hits wall)
    }
}
