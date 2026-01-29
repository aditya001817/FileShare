package p2p;

import p2p.controller.FileController;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        FileController fileController = new FileController(8080);
        fileController.start();
    }
}
