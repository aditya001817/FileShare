package p2p;

import p2p.controller.FileController;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            FileController fileController = new FileController(8080);
            fileController.start();
            System.out.println("FileSharer server started on port 8080");
            System.out.println("UI avialable at http://localhost:3000");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down...");
                fileController.stop();
            }));
        } catch (IOException e) {
            System.err.println("Failed to start the server at port 8080");
            e.printStackTrace();
        }
    }
}
