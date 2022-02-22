package cinema.controller;

import cinema.model.Cinema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
