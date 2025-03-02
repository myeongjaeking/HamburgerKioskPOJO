package kiosk;

import consumer.Consumer;
import io.Input;
import manager.Manager;

import kiosk.validator.KioskErrorMessage;
import root.Root;
import root.RootDto;

public class Kiosk {

    private final Manager manager = new Manager();
    private final Consumer consumer = new Consumer();

    public Kiosk() {
    }

    public void start() {
        while (true) {
            showKiosk();

            String input = Input.nextLine();

            if (!selectKiosk(input)) {
                break;
            }
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

            RootDto rootDto = new RootDto(manager, consumer);
            selectedRoot.process(rootDto);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(KioskErrorMessage.NOT_FIT_FORMAT_KIOSK.getMessage());
        }

        return true;
    }

}