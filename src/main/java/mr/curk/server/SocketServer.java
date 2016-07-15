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

            sendMessageToClient(CommandStatus.CONNECTED);

            // two way communication via input and output stream
            do {
                try {
                    message = (CommandStatus) in.readObject();
                    System.out.println("client " + MyDateTime.getCurrentDateTime() + "> " + message.toString());

                    switch (message) {
                        case STOP_HOMESEQ:
                            if(piFaceModule.setCommand(message)) {
                                serverRunningCondition = false;
                                sendMessageToClient(message);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;

                        case EXIT:
                            connectionRunningCondition = false;
                            sendMessageToClient(CommandStatus.EXIT);
                            break;

                        case IO_STATUS:
                            sendMessageToClient(piFaceModule.getAllIoStatus());
                            break;

                        case OUTPUT_OFF:
                            if(piFaceModule.setCommand(message))
                                sendMessageToClient(piFaceModule.getAllIoStatus());
                            else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;

                        case OUTPUT_0_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(0) == State.ON)
                                    sendMessageToClient(CommandStatus.OUTPUT_0_ON);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_0_OFF);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;
                        case OUTPUT_0_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(0) == State.OFF)
                                    sendMessageToClient(CommandStatus.OUTPUT_0_OFF);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_0_ON);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;

                        case OUTPUT_1_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(1) == State.ON)
                                    sendMessageToClient(CommandStatus.OUTPUT_1_ON);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_1_OFF);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;
                        case OUTPUT_1_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(1) == State.OFF)
                                    sendMessageToClient(CommandStatus.OUTPUT_1_OFF);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_1_ON);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;

                        case OUTPUT_2_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(2) == State.ON)
                                    sendMessageToClient(CommandStatus.OUTPUT_2_ON);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_2_OFF);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;
                        case OUTPUT_2_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(2) == State.OFF)
                                    sendMessageToClient(CommandStatus.OUTPUT_2_OFF);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_2_ON);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;

                        case OUTPUT_3_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(3) == State.ON)
                                    sendMessageToClient(CommandStatus.OUTPUT_3_ON);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_3_OFF);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;
                        case OUTPUT_3_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(3) == State.OFF)
                                    sendMessageToClient(CommandStatus.OUTPUT_3_OFF);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_3_ON);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;

                        case OUTPUT_4_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(4) == State.ON)
                                    sendMessageToClient(CommandStatus.OUTPUT_4_ON);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_4_OFF);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;
                        case OUTPUT_4_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(4) == State.OFF)
                                    sendMessageToClient(CommandStatus.OUTPUT_4_OFF);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_4_ON);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;

                        case OUTPUT_5_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(5) == State.ON)
                                    sendMessageToClient(CommandStatus.OUTPUT_5_ON);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_5_OFF);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;
                        case OUTPUT_5_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(5) == State.OFF)
                                    sendMessageToClient(CommandStatus.OUTPUT_5_OFF);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_5_ON);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;

                        case OUTPUT_6_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(6) == State.ON)
                                    sendMessageToClient(CommandStatus.OUTPUT_6_ON);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_6_OFF);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;
                        case OUTPUT_6_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(6) == State.OFF)
                                    sendMessageToClient(CommandStatus.OUTPUT_6_OFF);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_6_ON);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;

                        case OUTPUT_7_ON:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(7) == State.ON)
                                    sendMessageToClient(CommandStatus.OUTPUT_7_ON);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_7_OFF);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;
                        case OUTPUT_7_OFF:
                            if(piFaceModule.setCommand(message)) {
                                if(piFaceModule.getStatusInput(7) == State.OFF)
                                    sendMessageToClient(CommandStatus.OUTPUT_7_OFF);
                                else
                                    sendMessageToClient(CommandStatus.OUTPUT_7_ON);
                            }else
                                sendMessageToClient(CommandStatus.ERROR);
                            break;
                        default:
                            sendMessageToClient(CommandStatus.HELP);
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

    private void sendMessageToClient(Object msg) {
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

