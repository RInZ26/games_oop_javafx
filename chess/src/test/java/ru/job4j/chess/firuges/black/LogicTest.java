package ru.job4j.chess.firuges.black;
import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.white.PawnWhite;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {
    @Test
    public void whenFiguresOnTheWay(){
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A1));
        logic.add(new PawnWhite(Cell.B2));
        boolean actual = logic.move(Cell.A1, Cell.C3);
        assertThat(actual, is(false));
    }

    @Test
    public void whenWayIsClear() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A1));
        logic.add(new PawnWhite(Cell.D4));
        boolean actual = logic.move(Cell.A1, Cell.C3);
        assertThat(actual, is(true));
    }
}
