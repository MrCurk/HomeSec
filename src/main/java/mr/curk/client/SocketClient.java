package mr.curk.client;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import mr.curk.util.CommandStatus;
import mr.curk.util.MyDateTime;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketClient {
    private final int portNumber;
    private final String serverIp;
    private Boolean clientRunningCondition;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private CommandStatus message;
    private Scanner keyboard;


    public SocketClient() {
        this(8888);
    }

    public SocketClient(int portNumber) {
        this("localhost", portNumber);
    }

    public SocketClient(String serverIp, int port) {
        this.clientRunningCondition = true;
        this.portNumber = port;
        this.serverIp = serverIp;
        this.keyboard = new Scanner(System.in);
    }

    private void startClient() {

        try {
            // create socket to connect to server
            socket = new Socket(serverIp, portNumber);
            System.out.println("Connected to " + serverIp + ":" + portNumber);

            //get input & output streams
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            //communication with server

            do {
                try {
                    if (message != CommandStatus.HELP) {
                        message = (CommandStatus) in.readObject();
                        System.out.println("server " + MyDateTime.getCurrentDateTime() + "> " + message.toString());

                        if (message == CommandStatus.STOP_HOMESEQ)
                            break;
                    }

                    message = null;
                    message = CommandStatus.setCommandStatus(keyboard.nextLine());

                    if (message != CommandStatus.HELP)
                        sendMessage(message);
                    else
                        CommandStatus.printHelp();

                    if (message == CommandStatus.EXIT)
                        clientRunningCondition = false;

                } catch (ClassNotFoundException e) {
                    System.err.println("data received in unknown format");
                }
            } while (clientRunningCondition);

            if (!clientRunningCondition) {
                message = (CommandStatus) in.readObject();
                System.out.println("server " + MyDateTime.getCurrentDateTime() + "> " + message.toString());
            }
        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("data received in unknown format");
        } finally {
            stopClient();
        }

    }

    private void stopClient() {
        try {
            in.close();
            out.close();
            socket.close();
            keyboard.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendMessage(CommandStatus msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("client " + MyDateTime.getCurrentDateTime() + "> " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length == 0)
            new SocketClient().startClient();
        else if (args.length == 1)
            new SocketClient(Integer.parseInt(args[0])).startClient();
        else if (args.length == 2)
            new SocketClient(args[0], Integer.parseInt(args[1])).startClient();
        else {
            System.out.println("SocketClient - connect to localhost:defaultport");
            System.out.println("SocketClient port_number - connect to localhost:port_number");
            System.out.println("SocketClient hostname port_number - connect to hostname:port_number");
            System.out.println("SocketClient ip port_number - connect to ip:port_number");
        }
    }
}
