package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
	this.position = position;
    }

    @Override
    public Cell position() {
	return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
	if (!isDiagonal(source, dest)) {
	    throw new IllegalStateException(
		    String.format("Could not way by diagonal from %s to %s", source, dest)
	    );
	}
        List<Cell> tempList = new ArrayList<>();
	int deltaX = source.x > dest.x ? -1 : +1;
	int deltaY = source.y > dest.y ? -1 : +1;

	int currentX = source.x;
	int currentY = source.y;
	while (dest.x != currentX && dest.y != currentY) {
	    currentX += deltaX;
	    currentY += deltaY;
            tempList.add(Cell.findBy(currentX, currentY));
	}
	return tempList.toArray(new Cell[tempList.size()]);
    }

    public boolean isDiagonal(Cell source, Cell dest) {
	return Math.abs(dest.y - source.y) == Math.abs(dest.x - source.x);
    }

    @Override
    public Figure copy(Cell dest) {
	return new BishopBlack(dest);
    }
}
