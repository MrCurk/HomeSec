package mr.curk.server;


import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.impl.PiFaceDevice;
import mr.curk.util.CommandStatus;

import java.io.IOException;

import static com.pi4j.wiringpi.Spi.CHANNEL_0;

public class PiFaceModule {
    final private PiFace piFace;

    public PiFaceModule() throws IOException {
        this.piFace = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, CHANNEL_0);
    }

    //SET COMMAND FOR PIFACE MODULE
    public void setCommand(CommandStatus command) {
        switch (command) {
            case OUTPUT_0_OFF:
                setOutputOff(0);
                break;
            case OUTPUT_0_ON:
                setOutputOn(0);
                break;
            case OUTPUT_1_OFF:
                setOutputOff(0);
                break;
            case OUTPUT_1_ON:
                setOutputOn(0);
                break;
            case OUTPUT_2_OFF:
                setOutputOff(0);
                break;
            case OUTPUT_2_ON:
                setOutputOn(0);
                break;
            case OUTPUT_3_OFF:
                setOutputOff(0);
                break;
            case OUTPUT_3_ON:
                setOutputOn(0);
                break;
            case OUTPUT_4_OFF:
                setOutputOff(0);
                break;
            case OUTPUT_4_ON:
                setOutputOn(0);
                break;
            case OUTPUT_5_OFF:
                setOutputOff(0);
                break;
            case OUTPUT_5_ON:
                setOutputOn(0);
                break;
            case OUTPUT_6_OFF:
                setOutputOff(0);
                break;
            case OUTPUT_6_ON:
                setOutputOn(0);
                break;
            case OUTPUT_7_OFF:
                setOutputOff(0);
                break;
            case OUTPUT_7_ON:
                setOutputOn(0);
                break;
            default:
                break;
        }
    }

    //SET OUTPUT OFF
    private void setOutputOff(int pin) {
        piFace.getOutputPin(pin).low();
    }

    //SET OUTPUT ON
    private void setOutputOn(int pin) {
        piFace.getOutputPin(pin).high();
    }
}
