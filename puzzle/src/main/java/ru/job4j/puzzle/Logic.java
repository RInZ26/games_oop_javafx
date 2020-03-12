package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;

import java.util.Arrays;


/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final int size;
    private final Figure[] figures;
    private int index = 0;

    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    public boolean isFree(Cell ... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
               result = false;
               break;
            }
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public boolean isWin() {
        int[][] table = this.convert();
        boolean result = false;
        int [] diagArray = getDiagonal(table);
        int winSymbol = 1;
            for (int c = 0; c < table.length; c++) {
                if (diagArray[c] == winSymbol && (checkHorizontal(table, c, winSymbol) || checkVertical(table, c, winSymbol))) {
                    result = true;
                    break;
                }
            }
        return result;
    }

    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }

    /**
     * Мой нагло украденный Matrix.checkHorizontal
     * @param matrix проверяемая матрица
     * @param index индекс горизонтали
     * @param symbol символ на который проверяем
     * @return true - array заполнен symbol, false - нет
     */
    private boolean checkHorizontal(int[][] matrix, int index, int symbol) {
        boolean isRight = true;
            for (int i : matrix[index]) {
                if (i!= symbol) {
                    isRight = false;
                    break;
                }
            }
        return isRight;
    }

    /**
     * Мой еще наглее украденный Matrix.checkVertical
     * @param matrix проверяемая матрица
     * @param index индекс вертикали
     * @param symbol символ на который проверяем
     * @return true - array заполнен symbol, false - нет
     */
    private boolean checkVertical(int[][] matrix, int index, int symbol) {
        boolean isRight = true;
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[c][index] != symbol) {
                    isRight = false;
                    break;
                }
            }
        return isRight;
    }

    /**
     * Берем диагональ как и раньше
     * @param matrix КВАДРАТНАЯ матрица
     * @return матрица из элементов главной диагонали матрицы
     */
    private int[] getDiagonal (int[][] matrix) {
        int[] array = new int[matrix.length];
            for (int c = 0; c < matrix.length; c++) {
                array[c] = matrix[c][c];
            }
        return array;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.convert());
    }
}
