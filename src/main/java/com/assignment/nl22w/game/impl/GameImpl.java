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
        ForestMap map = new ForestMap(0, 0, 0);
        char northBorder, southBorder, westBorder, eastBorder;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == 'X') { // Find X
                    map.row = i;
                    map.col = j;

                    for (int k = 0; k < grid[i].length; k++) { // Find destinations from Rows
                        northBorder = grid[0][k];
                        if (northBorder == ' ' || northBorder == 'X') {
                            grid[0][k] = 'd';
                        }
                        southBorder = grid[grid.length - 1][k];
                        if (southBorder == ' ' || southBorder == 'X') {
                            grid[grid.length - 1][k] = 'd';
                        }
                    }

                    for (int k = 0; k < grid.length; k++) { // Find destinations from Columns
                        eastBorder = grid[k][grid[j].length - 1];
                        if (eastBorder == ' ' || eastBorder == 'X') {
                            grid[k][grid[j].length - 1] = 'd';
                        }

                        westBorder = grid[k][0];
                        if (westBorder == ' ' || westBorder == 'X') {
                            grid[k][0] = 'd';
                        }
                    }
                }
            }
        }

        Queue<ForestMap> queue = new LinkedList<>();
        queue.add(new ForestMap(map.row, map.col, 0));

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[map.row][map.col] = true;

        while (queue.isEmpty() == false) {
            ForestMap f = queue.remove();

            if (grid[f.row][f.col] == 'd')
                return f.dist;

            // Moving up
            if (isValidWay(f.row - 1, f.col, grid, visited)) {
                queue.add(new ForestMap(f.row - 1, f.col, f.dist + 1));
                visited[f.row - 1][f.col] = true;
            }

            // Moving down
            if (isValidWay(f.row + 1, f.col, grid, visited)) {
                queue.add(new ForestMap(f.row + 1, f.col, f.dist + 1));
                visited[f.row + 1][f.col] = true;
            }

            // Moving left
            if (isValidWay(f.row, f.col - 1, grid, visited)) {
                queue.add(new ForestMap(f.row, f.col - 1, f.dist + 1));
                visited[f.row][f.col - 1] = true;
            }

            // Moving right
            if (isValidWay(f.row, f.col + 1, grid, visited)) {
                queue.add(new ForestMap(f.row, f.col + 1, f.dist + 1));
                visited[f.row][f.col + 1] = true;
            }
        }
        return 0;
    }

    private static boolean isValidWay(int x, int y, char[][] grid, boolean[][] visited) {
        if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length
                && grid[x][y] != '1' && visited[x][y] == false) {
            return true;
        }
        return false;
    }
}


