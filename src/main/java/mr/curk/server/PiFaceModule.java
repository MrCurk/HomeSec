package mr.curk.server;


import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.impl.PiFaceDevice;

import java.io.IOException;

import static com.pi4j.wiringpi.Spi.CHANNEL_0;

public class PiFaceModule {
    final private PiFace piFace;

    public PiFaceModule() throws IOException {
        this.piFace = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, CHANNEL_0);
    }

    //SET COMMAND FOR PIFACE MODULE
}
