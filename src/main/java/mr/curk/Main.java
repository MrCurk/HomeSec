package mr.curk;

import mr.curk.server.SocketServer;

public class Main {


    public static void main(String[] args) {

        SocketServer socketServer = new SocketServer();
        new Thread(socketServer).start();

    }
}
