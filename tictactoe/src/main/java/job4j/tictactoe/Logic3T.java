package job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
	this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
	boolean result = true;
	for (int index = 0; index != this.table.length; index++) {
	    Figure3T cell = this.table[startX][startY];
	    startX += deltaX;
	    startY += deltaY;
	    if (!predicate.test(cell)) {
		result = false;
		break;
	    }
	}
	return result;
    }

    /**
     * Проверка в цикле всех вертикалей и горизонталей, иначе результат вычисляется из диагоналей
     *
     * @return есть/нет
     */
    public boolean isWinnerX() {
	return isWin(Figure3T::hasMarkX);
    }

    /**
     * Проверка в цикле всех вертикалей и горизонталей, иначе результат вычисляется из диагоналей
     *
     * @return есть/нет
     */
    public boolean isWinnerO() {
	return isWin(Figure3T::hasMarkO);
    }

    /**
     * Проверка на победителя
     * @return true : false
     */
    public boolean isWin(Predicate<Figure3T> isWinner) {
	for (int c = 0; c < table.length; c++) {
	    if (fillBy(isWinner, c, 0, 0, 1)
		    || fillBy(isWinner, 0, c, 1, 0)) {
		return true;
	    }
	}
	return fillBy(isWinner, 0, 0, 1, 1)
		|| fillBy(isWinner, 0, 2, 1, -1);
    }

    public boolean hasGap() {
	return (Arrays.stream(table).flatMap(Arrays::stream).anyMatch(cell -> !cell.hasMarkX() && !cell.hasMarkO()));
    }
}
