package cinema.model;

import org.springframework.stereotype.Component;

import java.util.BitSet;
import java.util.List;
import java.util.function.IntFunction;

@Component
public class Cinema {
    private final int totalRows = 9;
    private final int totalColumns = 9;

    private final BitSet seats = new BitSet(totalRows * totalColumns);

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        IntFunction<Seat> seatBuilder = i -> new Seat(i / totalRows, i % totalColumns);
        return seats.stream().mapToObj(seatBuilder).toList();
    }
}
