package cinema.dto;

import cinema.model.Seat;

public record PurchaseConfirmation(int row, int column, int price) {
    public PurchaseConfirmation(Seat seat, int price) {
        this(seat.row(), seat.column(), price);
    }
}
