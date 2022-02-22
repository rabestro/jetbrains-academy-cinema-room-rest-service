package cinema.controller;

import cinema.model.Cinema;
import cinema.model.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class CinemaController {
    private static final System.Logger LOGGER = System.getLogger(CinemaController.class.getName());

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
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (cinema.isFree(seat)) {
            cinema.buyTicket(seat);
            return new ResponseEntity<>(seat, HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"),
                HttpStatus.BAD_REQUEST);
    }
}
