package co.edu.unbosque.Model.Numeros;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component("numbersController")
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/numbers")
public class Controller {
    private NumeroV1 firstNeuron;
    private NumeroV2 secondNeuron;

    public Controller() {
        firstNeuron = new NumeroV1();
        secondNeuron = new NumeroV2();
    }


}
