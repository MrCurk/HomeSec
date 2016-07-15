package mr.curk.piFace;


import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import mr.curk.util.CommandStatus;
import mr.curk.util.State;

import java.io.IOException;
import java.io.StringReader;

import static com.pi4j.wiringpi.Spi.CHANNEL_0;

public class PiFaceModule {
    final private PiFace piFace;

    public PiFaceModule() throws IOException {
        this.piFace = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, CHANNEL_0);
        setAllOutputOff();
        setAllListers();
    }

    //SET COMMAND FOR PIFACE MODULE
    public Boolean setCommand(CommandStatus command) {
        switch (command) {
            case OUTPUT_0_OFF:
                setOutputOff(0);
                return true;
            case OUTPUT_0_ON:
                setOutputOn(0);
                return true;
            case OUTPUT_1_OFF:
                setOutputOff(1);
                return true;
            case OUTPUT_1_ON:
                setOutputOn(1);
                return true;
            case OUTPUT_2_OFF:
                setOutputOff(2);
                return true;
            case OUTPUT_2_ON:
                setOutputOn(2);
                return true;
            case OUTPUT_3_OFF:
                setOutputOff(3);
                return true;
            case OUTPUT_3_ON:
                setOutputOn(3);
                return true;
            case OUTPUT_4_OFF:
                setOutputOff(4);
                return true;
            case OUTPUT_4_ON:
                setOutputOn(4);
                return true;
            case OUTPUT_5_OFF:
                setOutputOff(5);
                return true;
            case OUTPUT_5_ON:
                setOutputOn(5);
                return true;
            case OUTPUT_6_OFF:
                setOutputOff(6);
                return true;
            case OUTPUT_6_ON:
                setOutputOn(6);
                return true;
            case OUTPUT_7_OFF:
                setOutputOff(7);
                return true;
            case OUTPUT_7_ON:
                setOutputOn(7);
                return true;
            case OUTPUT_OFF:
                setAllOutputOff();
                return true;
            case STOP_HOMESEQ:
                removeAllListeners();
                setAllOutputOff();
                return true;
            default:
                return false;
        }
    }

    //SET ALL OUTPUT OFF
    private void setAllOutputOff() {
        for (int i = 0; i < 8; i++) {
            setOutputOff(i);
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

    //GET ALL IO STATUS
    public String getAllIoStatus() {
        return "\n" + getIoStatus(0) + "\n" + getIoStatus(1) + "\n" + getIoStatus(2) + "\n" + getIoStatus(3) + "\n" + getIoStatus(4) + "\n" + getIoStatus(5) + "\n" + getIoStatus(6) + "\n" + getIoStatus(7);
    }
    //GET IO STATUS
    public String getIoStatus(int pin) {
        return "Input " + pin + ": " + getStatusInput(pin) + "\toutput " + pin + ": " + getStatusOutput(pin);
    }

    //GET INPUT STATUS
    public State getStatusInput(int pin) {
        if (piFace.getInputPin(pin).isLow())
            return State.ON;
        else
            return State.OFF;
    }

    //GET OUTPUT STATUS
    public State getStatusOutput(int pin) {
        if (piFace.getOutputPin(pin).isHigh())
            return State.ON;
        else
            return State.OFF;
    }

    //REMOVE LISTENER FROM ALL INPUTS
    private void removeAllListeners() {
        for (int i = 0; i < 8; i++) {
            removeListener(i);
        }
    }

    //REMOVE LISTENER FOM INPUT
    private void removeListener(int pin) {
        piFace.getInputPin(pin).removeAllListeners();
    }

    //SET LISTENER FOR ALL INPUTS
    private void setAllListers() {
        for (int i = 0; i < 8; i++) {
            setListener(i);
        }
    }

    //SET LISTENER FOR INPUT
    private void setListener(final int pin) {

        //INPUT 0
        piFace.getInputPin(pin).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input " + pin + " off.");
                }

                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input " + pin + " on.");
                }
            }
        });
    }

}
