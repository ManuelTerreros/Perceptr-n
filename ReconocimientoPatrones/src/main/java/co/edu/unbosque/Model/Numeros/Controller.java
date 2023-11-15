package co.edu.unbosque.Model.Numeros;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component("numbersController")
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/numbers")
public class Controller {
    private SimpleNeuronNumber firstNeuron;
    private SimpleNumberNeuronV2 secondNeuron;
    private NumeroV1 firstNumber;
    private NumeroV2 secondNumber;
    private List<Double> numbersListV1;
    private List<Double> numbersListV2;
    private List<Double> targetListsV1;
    private List<Double> targetListsV2;

    public Controller() {
        firstNeuron = new SimpleNeuronNumber(26);
        secondNeuron = new SimpleNumberNeuronV2(26);
        initializeV1();
        initializeV2();
    }

    public void initializeV1(){
        firstNumber = new NumeroV1();

    }

    public void initializeV2(){
        secondNumber = new NumeroV2();
    }

    @GetMapping("/getWeightsV1")
    public ResponseEntity<String> getWeightsV1(@RequestParam("name") String name) {
        File file = new File("models/" + name + ".txt");
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo no encontrado");
        }
        return ResponseEntity.ok().body(sb.toString());
    }

    @GetMapping("/getWeightsV2")
    public ResponseEntity<String> getWeightsV2(@RequestParam("name") String name) {
        File file = new File("models/" + name + ".txt");
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo no encontrado");
        }
        return ResponseEntity.ok().body(sb.toString());
    }

    public void saveWeightsV1(String name) {
        File save = new File("models/" + name + ".txt");
        try {
            save.createNewFile();
            double[] weights = firstNeuron.getWeights();
            FileWriter fw = new FileWriter(save);
            for (double weight : weights) {
                fw.write(weight + "#");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWeightsV2(String name) {
        File save = new File("models/" + name + ".txt");
        try {
            save.createNewFile();
            double[] weights = secondNeuron.getWeights();
            FileWriter fw = new FileWriter(save);
            for (double weight : weights) {
                fw.write(weight + "#");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/trainNumber/V1")
    public ResponseEntity<String> trainNumberV1 (@RequestParam("matriz")double[][]newMatriz){
        return ResponseEntity.ok().body("Entrenamiento completado");

    }

    @PostMapping("/trainNumber/V2")
    public ResponseEntity<String> trainNumberV2 (@RequestParam("matriz")double[][]newMatriz){
        return ResponseEntity.ok().body("Entrenamiento completado");

    }


}
