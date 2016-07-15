package mr.curk;

import mr.curk.piFace.PiFaceModule;
import mr.curk.server.SocketServer;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        PiFaceModule piFaceModule;
        SocketServer socketServer;

        //piFaceModule = new PiFaceModule();


        if (args.length == 0) {
            System.out.println(args.length);
            socketServer = new SocketServer();
            //socketServer = new SocketServer(piFaceModule);
            new Thread(socketServer).start();
        } else if (args.length == 1) {

            //socketServer = new SocketServer(piFaceModule, Integer.parseInt(args[0]));
            //new Thread(socketServer).start();
        } else {
            System.out.println("Main - start server on defaultport 8888");
            System.out.println("Main port_number - start server on port_number ");
        }
    }
}
