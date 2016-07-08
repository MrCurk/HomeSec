package mr.curk.server;

import mr.curk.util.CommandStatus;
import mr.curk.util.MyDateTime;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private Boolean serverRunningCondition;
    private Boolean connectionRunningCondition;
    private ServerSocket serverSocket;
    private Socket connection;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private CommandStatus message;
    private final int portNumber;

    public SocketServer() {
        this(true);
    }

    public SocketServer(Boolean socketServerRunningCondition) {
        this.serverRunningCondition = socketServerRunningCondition;
        this.connectionRunningCondition = true;
        this.connection = null;
        this.portNumber = 8888;
    }


    private void startServer() {
        try {
            connectionRunningCondition = true;

            // create server socket
            serverSocket = new ServerSocket(portNumber, 1);

            //Wait for connection
            System.out.println("Waiting for connection on port " + portNumber);
            connection = serverSocket.accept();
            System.out.println("Connection received from " + " " + connection.getInetAddress().getHostName() + " " + MyDateTime.getCurrentDateTime());

            //get input and output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();

            in = new ObjectInputStream(connection.getInputStream());

            sendMessage(CommandStatus.CONNECTED);

            // two way communication via input and output stream
            do {
                try {
                    message = (CommandStatus) in.readObject();
                    System.out.println("client " + MyDateTime.getCurrentDateTime() + "> " + message.toString());

                    switch (message) {
                        case STOP_HOMESEQ:
                            serverRunningCondition = false;
                            sendMessage(CommandStatus.STOP_HOMESEQ);
                            break;
                        case EXIT:
                            connectionRunningCondition = false;
                            sendMessage(CommandStatus.EXIT);
                            break;
                        case IO_STATUS:
                            sendMessage(CommandStatus.IO_STATUS);
                            break;
                        case OUTPUT_OFF:
                            sendMessage(CommandStatus.OUTPUT_OFF);
                            break;
                        case OUTPUT_0_ON:
                            sendMessage(CommandStatus.OUTPUT_0_ON);
                            break;
                        case OUTPUT_0_OFF:
                            sendMessage(CommandStatus.OUTPUT_0_OFF);
                            break;
                        case OUTPUT_1_ON:
                            sendMessage(CommandStatus.OUTPUT_1_ON);
                            break;
                        case OUTPUT_1_OFF:
                            sendMessage(CommandStatus.OUTPUT_1_OFF);
                            break;
                        case OUTPUT_2_ON:
                            sendMessage(CommandStatus.OUTPUT_2_ON);
                            break;
                        case OUTPUT_2_OFF:
                            sendMessage(CommandStatus.OUTPUT_2_OFF);
                            break;
                        case OUTPUT_3_ON:
                            sendMessage(CommandStatus.OUTPUT_3_ON);
                            break;
                        case OUTPUT_3_OFF:
                            sendMessage(CommandStatus.OUTPUT_3_OFF);
                            break;
                        case OUTPUT_4_ON:
                            sendMessage(CommandStatus.OUTPUT_4_ON);
                            break;
                        case OUTPUT_4_OFF:
                            sendMessage(CommandStatus.OUTPUT_4_OFF);
                            break;
                        case OUTPUT_5_ON:
                            sendMessage(CommandStatus.OUTPUT_5_ON);
                            break;
                        case OUTPUT_5_OFF:
                            sendMessage(CommandStatus.OUTPUT_5_OFF);
                            break;
                        case OUTPUT_6_ON:
                            sendMessage(CommandStatus.OUTPUT_6_ON);
                            break;
                        case OUTPUT_6_OFF:
                            sendMessage(CommandStatus.OUTPUT_6_OFF);
                            break;
                        case OUTPUT_7_ON:
                            sendMessage(CommandStatus.OUTPUT_7_ON);
                            break;
                        case OUTPUT_7_OFF:
                            sendMessage(CommandStatus.OUTPUT_7_OFF);
                            break;
                        default:
                            sendMessage(CommandStatus.HELP);
                    }
                    message = null;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } while (connectionRunningCondition && serverRunningCondition);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //closing connection
            stopServer();
        }
    }


    private void stopServer() {
        try {
            in.close();
            out.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(CommandStatus msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("server " + MyDateTime.getCurrentDateTime() + "> " + msg.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (serverRunningCondition) {
            startServer();
        }
    }
}

