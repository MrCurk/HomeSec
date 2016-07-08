package mr.curk.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateTime {
    private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static String getCurrentDateTime() {
        return dateFormat.format(new Date());
    }
}
