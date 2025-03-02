package root;

import consumer.Consumer;
import manager.Manager;

public record RootDto(Manager manager, Consumer consumer) {

}