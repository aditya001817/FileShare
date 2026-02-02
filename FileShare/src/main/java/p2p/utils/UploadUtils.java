package p2p.utils;

import java.util.Random;

public class UploadUtils {

    public static int generateCode(){
        int STARTING_PORT = 49153;
        int ENDING_PORT = 65535;

        Random random = new Random();
        return random.nextInt(ENDING_PORT - STARTING_PORT) + STARTING_PORT;
    }
}
