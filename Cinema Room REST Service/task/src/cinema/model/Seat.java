package cinema.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public record Seat(@Min(1) @Max(9) int row, @Min(1) @Max(9) int column) {
    public int getPrice() {
        return row < 5 ? 10 : 8;
    }
}
