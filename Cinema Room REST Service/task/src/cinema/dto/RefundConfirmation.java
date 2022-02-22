package cinema.dto;

import cinema.model.Seat;

public record RefundConfirmation(Seat returnedTicket) {
}
