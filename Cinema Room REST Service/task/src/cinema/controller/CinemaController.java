package cinema.controller;

import cinema.dto.PurchaseConfirmation;
import cinema.model.Cinema;
import cinema.model.Seat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PurchaseConfirmation purchase(@RequestBody Seat seat) {
        LOGGER.log(System.Logger.Level.INFO, seat);
        var price = cinema.buyTicket(seat);
        return new PurchaseConfirmation(seat, price);
    }
}
