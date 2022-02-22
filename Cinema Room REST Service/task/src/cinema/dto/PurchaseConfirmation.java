package cinema.dto;

import cinema.model.Seat;

import java.util.UUID;

public record PurchaseConfirmation(UUID token, Seat ticket) {
}
