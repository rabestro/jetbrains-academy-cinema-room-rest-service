package cinema.controller;

import cinema.dto.PurchaseConfirmation;
import cinema.dto.RefundConfirmation;
import cinema.dto.RefundRequest;
import cinema.model.Cinema;
import cinema.model.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class CinemaController {
    private final Cinema cinema;

    public CinemaController(Cinema cinema) {
        this.cinema = cinema;
    }

    @GetMapping("seats")
    public Cinema seats() {
        return cinema;
    }

    @PostMapping("purchase")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> purchase(@RequestBody Seat seat) {
        if (!cinema.isValid(seat)) {
            return new ResponseEntity<>(error("The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (cinema.isFree(seat)) {
            var token = cinema.buyTicket(seat);
            return new ResponseEntity<>(new PurchaseConfirmation(token, seat), HttpStatus.OK);
        }
        return new ResponseEntity<>(error("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("return")
    public ResponseEntity<Object> refund(@RequestBody RefundRequest request) {
        return cinema.refund(request.token())
                .map(RefundConfirmation::new)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body(error("Wrong token!")));
    }

    @PostMapping("stats")
    public ResponseEntity stats(@RequestParam(value = "password", required = false) String password) {
        if ("super_secret".equals(password)) {
            return ResponseEntity.ok(cinema.getReport());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error("The password is wrong!"));
    }

    private Map<String, String> error(String message) {
        return Map.of("error", message);
    }
}
