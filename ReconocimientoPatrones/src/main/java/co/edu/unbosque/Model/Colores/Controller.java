package co.edu.unbosque.Model.Colores;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@Component("coloresController")
@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/colors")
public class Controller {
    private final SimpleNeuronColor neuron = new SimpleNeuronColor(16385);

    public Controller() {
    initialize();


    }

    public void initialize(){
        double[] targets = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0};
        double learningRate = 1.0;
        Color white = new Color(255.0, 255.0, 255.0);
        Color black = new Color(0.0, 0.0, 0.0);
        Color lightBlue = new Color(0.0, 255.0, 255.0);
        Color darkBlue = new Color(0.0, 0.0, 255.0);
        Color red = new Color(255.0, 0.0, 0.0);
        Color green = new Color(0.0, 255.0, 0.0);
        Color lime = new Color(0.0, 255.0, 100.0);
        Color yellow = new Color(255.0, 255.0, 102.0);
        Color purple = new Color(102.0, 0.0, 255.0);
        Color orange = new Color(255.0, 165.0, 0.0);
        Color pink = new Color(255.0, 192.0, 203.0);
        Color lavender = new Color(230.0, 230.0, 250.0);
        Color maroon = new Color(128.0, 0.0, 0.0);
        Color olive = new Color(128.0, 128.0, 0.0);
        Color teal = new Color(0.0, 128.0, 128.0);
        Color cyan = new Color(0.0, 255.0, 255.0);
        Color magenta = new Color(255.0, 0.0, 255.0);
        Color brown = new Color(139.0, 69.0, 19.0);
        Color gray = new Color(128.0, 128.0, 128.0);
        Color lightGray = new Color(211.0, 211.0, 211.0);
        Color darkGray = new Color(169.0, 169.0, 169.0);
        Color navy = new Color(0.0, 0.0, 128.0);
        Color indigo = new Color(75.0, 0.0, 130.0);
        Color gold = new Color(255.0, 215.0, 0.0);
        Color sienna = new Color(160.0, 82.0, 45.0);
        Color tan = new Color(210.0, 180.0, 140.0);
        Color peach = new Color(255.0, 218.0, 185.0);
        Color plum = new Color(221.0, 160.0, 221.0);
        Color slateBlue = new Color(106.0, 90.0, 205.0);
        double[][] inputs = new double[29][3];
        inputs[0] = this.addColor(white);
        inputs[1] = this.addColor(black);
        inputs[2] = this.addColor(lightBlue);
        inputs[3] = this.addColor(darkBlue);
        inputs[4] = this.addColor(red);
        inputs[5] = this.addColor(green);
        inputs[6] = this.addColor(lime);
        inputs[7] = this.addColor(yellow);
        inputs[8] = this.addColor(purple);
        inputs[9] = this.addColor(orange);
        inputs[10] = this.addColor(pink);
        inputs[11] = this.addColor(lavender);
        inputs[12] = this.addColor(maroon);
        inputs[13] = this.addColor(olive);
        inputs[14] = this.addColor(teal);
        inputs[15] = this.addColor(brown);
        inputs[16] = this.addColor(gray);
        inputs[17] = this.addColor(cyan);
        inputs[18] = this.addColor(peach);
        inputs[19] = this.addColor(magenta);
        inputs[20] = this.addColor(lightGray);
        inputs[21] = this.addColor(darkGray);
        inputs[22] = this.addColor(navy);
        inputs[23] = this.addColor(indigo);
        inputs[24] = this.addColor(gold);
        inputs[25] = this.addColor(sienna);
        inputs[26] = this.addColor(tan);
        inputs[27] = this.addColor(plum);
        inputs[28] = this.addColor(slateBlue);
        train(inputs, targets, learningRate);

    }

    public void train(double[][] inputs, double[] targets, double learningRate) {
        this.neuron.train(inputs, targets, learningRate);
    }

    @PostMapping("/predictColor")
    public ResponseEntity<String> predecirColor(@RequestParam("color") int []color){
        double resultado = neuron.predictColor(new double[]{color[0], color[1], color[2]});
        try {
            if(resultado <= 0){
                return ResponseEntity.ok().body("Se debe usar letra de color Blanco");
            }else{
                return ResponseEntity.ok().body("Se debe usar letra de color Negro");
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Error");
        }


    }


    public double[] addColor(Color color) {
        return new double[]{color.getRed(), color.getGreen(), color.getBlue()};
    }

    public void saveWeights(String name) {
        File save = new File("models/" + name + ".txt");
        try {
            save.createNewFile();
            double[] weights = neuron.getWeights();
            FileWriter fw = new FileWriter(save);
            for (double weight : weights) {
                fw.write(weight + "#");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getWeights")
    public ResponseEntity<String> getWeights(@RequestParam("name") String name) {
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
}

