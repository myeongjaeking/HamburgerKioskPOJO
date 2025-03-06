package kiosk;

import consumer.Consumer;
import file.LoadFile;
import file.validator.FileErrorMessage;
import io.Input;
import manager.Manager;

import kiosk.validator.KioskErrorMessage;
import order.Order;
import root.Option;
import root.ManagerConsumerDto;

import java.io.FileNotFoundException;

public class Kiosk {

    private final Manager manager = new Manager();
    private final Consumer consumer = new Consumer();
    private final LoadFile loadFile = new LoadFile();
    private ManagerConsumerDto managerConsumerDto;

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
        Option[] options = Option.values();

        for (Option option : options) {
            System.out.println(option.getRoot());
        }
    }

    private boolean selectKiosk(String input) {
        try {
            int index = Integer.parseInt(input);
            Option selectedOption = Option.values()[index];

            if (selectedOption == Option.EXIT) {
                return false;
            }

            managerConsumerDto = new ManagerConsumerDto(manager, consumer);
            selectedOption.process(managerConsumerDto);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(KioskErrorMessage.NOT_FIT_FORMAT_KIOSK.getMessage());
        }

        return true;
    }

    private void startOrder() {
        if (consumer.isConnect() && manager.isConnect()) {
            Order.getInstance(managerConsumerDto, loadFile).start();
        }
    }

}