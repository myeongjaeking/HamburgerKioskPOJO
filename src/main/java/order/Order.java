package order;

import file.LoadFile;
import io.Input;
import product.Product;
import root.RootDto;

import java.io.FileNotFoundException;
import java.util.List;

public class Order {
    private LoadFile loadFile = new LoadFile();
    private RootDto rootDto;
    private List<Product> products;

    public Order(RootDto rootDto) throws FileNotFoundException {
        this.rootDto = rootDto;
        showMenu();
    }

    private void intro() {
        System.out.println("\n=================================");
        System.out.println("안녕하세요. " + rootDto.consumer().getName() + "님 햄버거 가게 입니다.");
        System.out.println("현재 접속된 관리자는 " + rootDto.manager().getName() + "입니다.\n");
    }

    private void outro() {
        System.out.println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [치킨버거-3],[불고기버거세트-2])");
    }

    private void showMenu() throws FileNotFoundException {
        loadFile.readFile();
        products = loadFile.getProducts();
        intro();
        for (Product product : products) {
            System.out.println(product.toString());
        }

        outro();
    }

    private void start() throws FileNotFoundException {
        showMenu();
        String input = Input.nextLine();
    }

}