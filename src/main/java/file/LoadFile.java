package file;

import file.validator.FileErrorMessage;
import product.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static util.Separator.BLANK;
import static util.Separator.REST;

public class LoadFile {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void checkFileExists() throws FileNotFoundException {
        File file = loadFile();
        if (!file.exists()) {
            throw new FileNotFoundException(FileErrorMessage.NOT_FOUND_FILE.getMessage());
        }
    }

    public void readFile() {
        File file = loadFile();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                saveProducts(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(FileErrorMessage.NOT_FOUND_FILE.getMessage());
        }
    }

    private File loadFile() {
        final String FILEPATH = "src/main/resources/products.md";
        return new File(FILEPATH);
    }

    public void writeFile(List<Product> products) {
        File file = loadFile();

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("name, price, quantity, description, category\n");

            for (Product product : products) {
                String line = String.format(
                        "%s,%d,%d,\"%s\",%s\n",
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getDescription(),
                        product.getCategory()
                );
                fileWriter.write(line);
            }
            fileWriter.close();
        }catch (IOException e){
            System.out.println(FileErrorMessage.NOT_FOUND_FILE.getMessage());
        }
    }

    private void saveProducts(String line) {
        String[] menuArr = line.split(REST.getSign());
        String name = menuArr[0];
        int price = Integer.parseInt(menuArr[1]);
        int quantity = 0;

        try {
            quantity = Integer.parseInt(menuArr[2]);
        } catch (NumberFormatException ignored) {

        }
        String description = menuArr[3].replaceAll("^\"|\"$", BLANK.getSign());
        String category = menuArr[4];

        products.add(new Product(name, price, quantity, description, category));
    }

}