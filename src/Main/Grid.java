package Main;

import java.awt.*;
//import java.awt.List;
import java.beans.Transient;
//import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Random;

public class Grid extends JPanel {
    int gridLength;
    int gridWidth;
    int generationCounter;
    Cell[][] grid;

    Grid() {
        gridLength = 200;
        gridWidth = 200;
        generationCounter = 0;
        grid = new Cell[gridLength][gridWidth];

        Random r = new Random();

//        for (int i = 0; i < gridLength; i++)
//            for (int j = 0; j < gridWidth; j++)
//                if (r.nextDouble() < 0.93)
//                   grid[i][j] = new Cell(i,j,'o');
//                else
//                    grid[i][j] = new Cell(i,j,'x');
        readFromFile();


    }

    List<String[]> list = new ArrayList<String[]>(400);

    void readFromFile() {
        int count = 0;
        String[][] matrix = null;
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\liviu.clement\\Desktop\\collections\\Collections\\Workshop\\GameOfLife\\src\\Main\\initializare grid incomplet.csv"))) {
            String firstLine;
            String line;
            firstLine = br.readLine();
            String[] d = firstLine.split(firstLine.substring(0, 1));
            String t = d[1];
            String f = d[2];

            while ((line = br.readLine()) != null) {
                String[] lineItems = line.split(firstLine.substring(0, 1));
                list.add(lineItems);
            }
            String[] row = new String[list.size()];

            matrix = new String[list.size()][list.size()];

            for (int i = 0; i < list.size(); i++) {
                for(int j = 0; j < list.get(i).length; j++)
                    row[j] = list.get(i)[j];
                String [] raw = list.get(i);
                for(int j = 0; j < raw.length; j++)
                {
                    if(row[j].equals(""))
                        row[j] = "0";
                }

                if(raw.length < list.size())
                {
                    for(int j = raw.length; j < list.size(); j++)
                    {
                        row[j] = "0";
                    }
                }
                for (int j = 0; j < list.size(); j++) {

                        System.out.print(row[j] + " ");

                }
                System.out.println();
                for (int j = 0; j < list.size(); j++) {

                    matrix[i][j] = row[j];

                }

            }
            grid = new Cell[list.size()][list.size()];
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[i].length; j++) {
                    grid[i][j] = new Cell(i, j, matrix[i][j].equals(t) ? 'x' : 'o');
                }
        } catch (Exception e) {
            System.out.println("N-a vrut sa mearga :(");
            e.printStackTrace();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        return;
    }

    int checkNeighbours(Cell[][] g, int i, int j) {
        int count = 0;

        for (int m = i - 1; m <= i + 1; m++)
            for (int n = j - 1; n <= j + 1; n++) {
                int x = (m + list.size()) % list.size();
                int y = (n + list.size()) % list.size();
                if (m == i && n == j)
                    continue;
                else if (g[x][y].getValue() == 'x')

                    count++;
            }
        return count;
    }

    void newGeneration(Grid x) {
        // if(p < 5) {
        Cell[][] v = new Cell[list.size()][list.size()];
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size(); j++)
                v[i][j] = new Cell(i, j, x.grid[i][j].getValue());
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size(); j++)
                if (v[i][j].getValue() == 'x' && (checkNeighbours(v, i, j) < 2 || checkNeighbours(v, i, j) > 3))
                    x.grid[i][j].setValue('o');
                else if ((v[i][j].getValue() == 'o' && checkNeighbours(v, i, j) == 3))
                    x.grid[i][j].setValue('x');
        //print(x);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("got interrupted");
        }
        // newGeneration(x, ++p);
    }

    @Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(list.size() * 40, list.size() * 40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        g.drawString("Genereation: " + generationCounter++, 0, 10);
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size(); j++)
                if (grid[i][j].getValue() == 'x') {
                    g.setColor(Color.green);
                    g.fillRect(j * 40, i * 40, 40, 40);
                }
        g.setColor(gColor);
    }
//    void print(Grid x) {
//        char[][] ch = new char[gridLength][gridWidth];
//        for (int i = 0; i < gridLength; i++)
//            for (int j = 0; j < gridWidth; j++)
//                ch[i][j] = x.grid[i][j].getValue();
//
//        System.out.println("New generation: ");
//        for (char[] row : ch
//        ) {
//            System.out.println(Arrays.toString(row));
//        }
//    }
}