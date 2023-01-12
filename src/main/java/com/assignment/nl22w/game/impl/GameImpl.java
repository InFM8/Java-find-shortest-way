package com.assignment.nl22w.game.impl;

import com.assignment.nl22w.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
@Slf4j
public class GameImpl implements Game {
    @Override
    public int escapeFromTheWoods(Resource resource) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        Scanner sc = new Scanner(content.toString());
        int rows = 0, cols = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            rows++;
            cols = Math.max(cols, line.length());
        }

        sc.reset();
        sc = new Scanner(content.toString());
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < cols; j++) {
                if (j < line.length()) {
                    grid[i][j] = line.charAt(j);
                } else {
                    grid[i][j] = ' ';
                }
            }
        }
        return findShortestExit(grid);
    }

    private static int findShortestExit(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 'X' && grid[i][j] != ' ' && grid[i][j] != '1') {
                    return 0;
                }
            }
        }

        int startX = -1, startY = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'X') {
                    startX = i;
                    startY = j;
                    break;
                }
            }
            if (startX != -1) break;
        }
        if (startX == -1) return 0;

        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == ' ' || grid[0][i] == 'X') grid[0][i] = 'd';
            if (grid[grid.length - 1][i] == ' ' || grid[grid.length - 1][i] == 'X') grid[grid.length - 1][i] = 'd';
        }
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == ' ' || grid[i][0] == 'X') grid[i][0] = 'd';
            if (grid[i][grid[i].length - 1] == ' ' || grid[i][grid[i].length - 1] == 'X') grid[i][grid[i].length - 1] = 'd';
        }

        // BFS
        int[] xMoves = {1, -1, 0, 0};
        int[] yMoves = {0, 0, 1, -1};
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[startX][startY] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], dist = current[2];
            if (grid[x][y] == 'd') return dist;
            for (int i = 0; i < 4; i++) {
                int xNext = x + xMoves[i], yNext = y + yMoves[i];
                if (xNext >= 0 && xNext < grid.length && yNext >= 0 && yNext < grid[0].length
                        && grid[xNext][yNext] != '1' && !visited[xNext][yNext]) {
                    visited[xNext][yNext] = true;
                    queue.add(new int[] {xNext, yNext, dist + 1});
                }
            }
        }
        return 0;
    }
}


