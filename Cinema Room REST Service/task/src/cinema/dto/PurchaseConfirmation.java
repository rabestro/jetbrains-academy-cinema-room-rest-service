package cinema.dto;

import cinema.model.Seat;

public record PurchaseConfirmation(String token, Seat ticket) {
}
