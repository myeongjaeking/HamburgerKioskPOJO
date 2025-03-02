package root;

import consumer.process.ConnectConsumer;
import consumer.process.CreateConsumer;
import manager.process.ConnectManager;
import manager.process.CreateManager;

public enum Root {

    EXIT("0. 종료", null),
    CREATE_MANGER("1. 관리자 생성", new CreateManager()),
    CONNECT_MANGER("2. 관리자 접속", new ConnectManager()),
    CREATE_CONSUMER("3. 회원 생성", new CreateConsumer()),
    CONNECT_CONSUMER("4. 회원 접속", new ConnectConsumer());

    private final String root;
    private final RootProcess rootProcess;

    Root(String root, RootProcess rootProcess) {
        this.root = root;
        this.rootProcess = rootProcess;
    }

    public String getRoot() {
        return root;
    }

    public void process(RootDto rootDto) {
        rootProcess.start(rootDto);
    }

}