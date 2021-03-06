package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.IntFunction;

import static java.lang.System.Logger.Level.DEBUG;

@Component
public class Cinema {
    private static final System.Logger LOGGER = System.getLogger(Cinema.class.getName());

    private final int totalRows = 9;
    private final int totalColumns = 9;
    private final Map<String, Seat> reserved = new HashMap<>();
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

    public String buyTicket(Seat seat) {
        LOGGER.log(DEBUG, "{0} {1} {2}", seat, getIndex(seat), seats.get(getIndex(seat)));
        seats.clear(getIndex(seat));
        var token = UUID.randomUUID().toString();
        reserved.put(token, seat);
        return token;
    }

    @JsonIgnore
    public Report getReport() {
        var income = reserved.values().stream().mapToInt(Seat::getPrice).sum();
        return new Report(income, seats.cardinality(), reserved.size());
    }

    public Optional<Seat> refund(String token) {
        var seat = reserved.remove(token);
        if (seat != null) {
            seats.set(getIndex(seat));
        }
        return Optional.ofNullable(seat);
    }

    public boolean isFree(Seat value) {
        return seats.get(getIndex(value));
    }

    public boolean isValid(Seat seat) {
        return seat.column() > 0 && seat.column() < 10 && seat.row() > 0 && seat.row() < 10;
    }
}
