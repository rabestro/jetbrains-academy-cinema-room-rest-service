package cinema.model;

import org.springframework.stereotype.Component;

import java.util.BitSet;
import java.util.List;
import java.util.function.IntFunction;

import static java.lang.System.Logger.Level.INFO;

@Component
public class Cinema {
    private static final System.Logger LOGGER = System.getLogger(Cinema.class.getName());

    private final int totalRows = 9;
    private final int totalColumns = 9;

    private final BitSet seats = new BitSet(totalRows * totalColumns);

    {
        seats.flip(0, totalRows * totalColumns);
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        IntFunction<Seat> seatBuilder = i -> new Seat(1 + i / totalRows, 1 + i % totalColumns);
        return seats.stream().filter(seats::get).mapToObj(seatBuilder).toList();
    }

    private int getIndex(Seat seat) {
        return seat.row() * totalColumns + seat.column() - totalColumns - 1;
    }

    public void buyTicket(Seat seat) {
        LOGGER.log(INFO, "{0} {1}", seat, getIndex(seat));
        seats.clear(getIndex(seat));
        LOGGER.log(INFO, seats.get(getIndex(seat)));
    }

    public boolean isFree(Seat value) {
        return seats.get(getIndex(value));
    }
}
