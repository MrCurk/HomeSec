package mr.curk.server;

import mr.curk.piFace.PiFaceModule;
import mr.curk.util.CommandStatus;
import mr.curk.util.MyDateTime;
import mr.curk.util.State;

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
    private PiFaceModule piFaceModule;

    public SocketServer() {
        this(null, 8888);
    }

    public SocketServer(PiFaceModule piFaceModule) {
        this(piFaceModule, 8888);
    }

    public SocketServer(PiFaceModule piFaceModule, int port) {
        this.serverRunningCondition = true;
        this.connectionRunningCondition = true;
        this.connection = null;
        this.portNumber = port;
        this.piFaceModule = piFaceModule;
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
                            if(piFaceModule.setCommand(message)) {
                                serverRunningCondition = false;
                                sendMessage(CommandStatus.STOP_HOMESEQ);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;

                        case EXIT:
                            connectionRunningCondition = false;
                            sendMessage(CommandStatus.EXIT);
                            break;
                        case IO_STATUS:
                            sendMessage(piFaceModule.getAllIoStatus());
                            break;
                        case OUTPUT_OFF:
                            if(piFaceModule.setCommand(message))
                                sendMessage(piFaceModule.getAllIoStatus());
                            else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_0_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(0) == State.ON)
                                    sendMessage(CommandStatus.OUTPUT_0_ON);
                                else
                                    sendMessage(CommandStatus.OUTPUT_0_OFF);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_0_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(0) == State.OFF)
                                    sendMessage(CommandStatus.OUTPUT_0_OFF);
                                else
                                    sendMessage(CommandStatus.OUTPUT_0_ON);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_1_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(1) == State.ON)
                                    sendMessage(CommandStatus.OUTPUT_1_ON);
                                else
                                    sendMessage(CommandStatus.OUTPUT_1_OFF);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_1_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(1) == State.OFF)
                                    sendMessage(CommandStatus.OUTPUT_1_OFF);
                                else
                                    sendMessage(CommandStatus.OUTPUT_1_ON);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_2_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(2) == State.ON)
                                    sendMessage(CommandStatus.OUTPUT_2_ON);
                                else
                                    sendMessage(CommandStatus.OUTPUT_2_OFF);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_2_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(2) == State.OFF)
                                    sendMessage(CommandStatus.OUTPUT_2_OFF);
                                else
                                    sendMessage(CommandStatus.OUTPUT_2_ON);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_3_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(3) == State.ON)
                                    sendMessage(CommandStatus.OUTPUT_3_ON);
                                else
                                    sendMessage(CommandStatus.OUTPUT_3_OFF);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_3_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(3) == State.OFF)
                                    sendMessage(CommandStatus.OUTPUT_3_OFF);
                                else
                                    sendMessage(CommandStatus.OUTPUT_3_ON);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_4_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(4) == State.ON)
                                    sendMessage(CommandStatus.OUTPUT_4_ON);
                                else
                                    sendMessage(CommandStatus.OUTPUT_4_OFF);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_4_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(4) == State.OFF)
                                    sendMessage(CommandStatus.OUTPUT_4_OFF);
                                else
                                    sendMessage(CommandStatus.OUTPUT_4_ON);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_5_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(5) == State.ON)
                                    sendMessage(CommandStatus.OUTPUT_5_ON);
                                else
                                    sendMessage(CommandStatus.OUTPUT_5_OFF);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_5_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(5) == State.OFF)
                                    sendMessage(CommandStatus.OUTPUT_5_OFF);
                                else
                                    sendMessage(CommandStatus.OUTPUT_5_ON);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_6_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(6) == State.ON)
                                    sendMessage(CommandStatus.OUTPUT_6_ON);
                                else
                                    sendMessage(CommandStatus.OUTPUT_6_OFF);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_6_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(6) == State.OFF)
                                    sendMessage(CommandStatus.OUTPUT_6_OFF);
                                else
                                    sendMessage(CommandStatus.OUTPUT_6_ON);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_7_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(7) == State.ON)
                                    sendMessage(CommandStatus.OUTPUT_7_ON);
                                else
                                    sendMessage(CommandStatus.OUTPUT_7_OFF);
                            }else
                                sendMessage(CommandStatus.ERROR);
                            break;
                        case OUTPUT_7_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(7) == State.OFF)
                                    sendMessage(CommandStatus.OUTPUT_7_OFF);
                                else
                                    sendMessage(CommandStatus.OUTPUT_7_ON);
                            }else
                                sendMessage(CommandStatus.ERROR);
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

    private void sendMessage(Object msg) {
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

