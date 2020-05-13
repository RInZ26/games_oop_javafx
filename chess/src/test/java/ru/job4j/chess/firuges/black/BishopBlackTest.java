package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopBlackTest {
    @Test
    public void getPositionTest() {
	BishopBlack bb = new BishopBlack(Cell.A1);
	assertThat(bb.position(), is(Cell.A1));
    }

    @Test
    public void copyTest() {
	BishopBlack oldBB = new BishopBlack(Cell.A1);
	BishopBlack newBB = (BishopBlack) oldBB.copy(Cell.A3);
	assertThat(newBB.position(), is(Cell.A3));
    }

    @Test
    public void wayTest() {
	BishopBlack bb = new BishopBlack(Cell.C1);
	assertThat(bb.way(bb.position(), Cell.G5), is(new Cell[]{
		Cell.D2, Cell.E3, Cell.F4, Cell.G5
	}));
    }

}
