package file;

import file.validator.FileErrorMessage;
import product.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadFile {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void readFile() throws FileNotFoundException {
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
            throw new FileNotFoundException(FileErrorMessage.NOT_FOUND_FILE.getMessage());
        }
    }

    private File loadFile() {
        final String FILEPATH = "src/main/resources/products2.md";
        return new File(FILEPATH);
    }

    private void saveProducts(String line) {
        String[] menuArr = line.split(",");
        String name = menuArr[0];
        int price = Integer.parseInt(menuArr[1]);
        int quantity = 0;

        try {
            quantity = Integer.parseInt(menuArr[2]);
        } catch (NumberFormatException ignored) {

        }
        String description = menuArr[3].replaceAll("^\"|\"$", "");
        String category = menuArr[4];

        products.add(new Product(name, price, quantity, description, category));
    }

}