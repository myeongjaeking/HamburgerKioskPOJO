package kiosk;

import consumer.Consumer;
import file.LoadFile;
import file.validator.FileErrorMessage;
import io.Input;
import manager.Manager;

import kiosk.validator.KioskErrorMessage;
import order.Order;
import root.Root;
import root.RootDto;

import java.io.FileNotFoundException;

public class Kiosk {

    private final Manager manager = new Manager();
    private final Consumer consumer = new Consumer();
    private final LoadFile loadFile = new LoadFile();
    private RootDto rootDto;

    public Kiosk() {
    }

    public void start() {
        try {
            loadFile.checkFileExists();
            while (true) {
                showKiosk();

                String input = Input.nextLine();

                if (!selectKiosk(input)) {
                    break;
                }
                startOrder();
            }
        } catch (FileNotFoundException e) {
            System.out.println(FileErrorMessage.NOT_FOUND_FILE.getMessage());
        }
    }

    private void showKiosk() {
        Root[] values = Root.values();

        for (Root value : values) {
            System.out.println(value.getRoot());
        }
    }

    private boolean selectKiosk(String input) {
        try {
            int index = Integer.parseInt(input);
            Root selectedRoot = Root.values()[index];

            if (selectedRoot == Root.EXIT) {
                return false;
            }

            rootDto = new RootDto(manager, consumer);
            selectedRoot.process(rootDto);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(KioskErrorMessage.NOT_FIT_FORMAT_KIOSK.getMessage());
        }

        return true;
    }

    private void startOrder() {
        if (consumer.isConnect() && manager.isConnect()) {
            Order.getInstance(rootDto).start();
        }
    }

}