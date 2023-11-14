package co.edu.unbosque.Model.Animales;

import java.io.File;
import java.util.Random;

//No usar si no es necesario
public class RenameImages {
    /**
     * Generates a random number and renames the files in the specified folder.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String folderPath = "src/main/resources/static";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String extension = fileName.substring(fileName.lastIndexOf("."));
                String newFileName = generateRandomNumber() + extension;
                File newFile = new File(folderPath + "/" + newFileName);
                file.renameTo(newFile);
            }
        }
        System.out.println("Nombres de archivos modificados con éxito.");
    }

    /**
     * Generates a random number between 0 and 999999.
     *
     * @return The randomly generated number as a 6-digit string.
     */
    private static String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);
        return String.format("%06d", randomNumber);
    }
}