package mr.curk.util;


public enum CommandStatus {
    HELP, EXIT,
    ERROR, OK, CONNECTED,
    STOP_HOMESEQ,
    IO_STATUS, OUTPUT_OFF,
    OUTPUT_0_ON, OUTPUT_0_OFF,
    OUTPUT_1_ON, OUTPUT_1_OFF,
    OUTPUT_2_ON, OUTPUT_2_OFF,
    OUTPUT_3_ON, OUTPUT_3_OFF,
    OUTPUT_4_ON, OUTPUT_4_OFF,
    OUTPUT_5_ON, OUTPUT_5_OFF,
    OUTPUT_6_ON, OUTPUT_6_OFF,
    OUTPUT_7_ON, OUTPUT_7_OFF,
    INPUT_1_ON, INPUT_1_OFF,
    INPUT_2_ON, INPUT_2_OFF,
    INPUT_3_ON, INPUT_3_OFF,
    INPUT_4_ON, INPUT_4_OFF,
    INPUT_5_ON, INPUT_5_OFF,
    INPUT_6_ON, INPUT_6_OFF,
    INPUT_7_ON, INPUT_7_OFF;

    public static CommandStatus setCommandStatus(String commandStatusString) {
        String commandStatusStringUpper = commandStatusString.toUpperCase();
        switch (commandStatusStringUpper) {
            case "OP0 ON":
                return OUTPUT_0_ON;
            case "OP0 OFF":
                return OUTPUT_0_OFF;
            case "OP1 ON":
                return OUTPUT_1_ON;
            case "OP1 OFF":
                return OUTPUT_1_OFF;
            case "OP2 ON":
                return OUTPUT_2_ON;
            case "OP2 OFF":
                return OUTPUT_2_OFF;
            case "OP3 ON":
                return OUTPUT_3_ON;
            case "OP3 OFF":
                return OUTPUT_3_OFF;
            case "OP4 ON":
                return OUTPUT_4_ON;
            case "OP4 OFF":
                return OUTPUT_4_OFF;
            case "OP5 ON":
                return OUTPUT_5_ON;
            case "OP5 OFF":
                return OUTPUT_5_OFF;
            case "OP6 ON":
                return OUTPUT_6_ON;
            case "OP6 OFF":
                return OUTPUT_6_OFF;
            case "OP7 ON":
                return OUTPUT_7_ON;
            case "OP7 OFF":
                return OUTPUT_7_OFF;
            case "OP OFF":
                return OUTPUT_OFF;
            case "STATUS":
            case "S":
                return IO_STATUS;
            case "EXIT":
                return  EXIT;
            case "STOP":
                return  STOP_HOMESEQ;
            default:
                return HELP;
        }
    }

    public static void printHelp(){
        System.out.println("op[0-7] on - turn output n on.");
        System.out.println("op[0-7] off - turn output n off.");
        System.out.println("status or s - display status of inputs and outputs.");
        System.out.println("exit - to exit from client application.");
        System.out.println("stop - to stop HomeSeq.");
    }
}
