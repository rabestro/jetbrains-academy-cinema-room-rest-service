package cinema.controller;

import cinema.model.Cinema;
import cinema.model.Seat;
import cinema.validation.FreeSeat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
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
    public Seat purchase(@Valid @FreeSeat @RequestBody Seat seat) {
        cinema.buyTicket(seat);
        return seat;
    }
}
