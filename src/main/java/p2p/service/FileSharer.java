package p2p.service;

import p2p.utils.UploadUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class FileSharer {

    private HashMap<Integer, String> avialableFiles;

    public FileSharer(){
        avialableFiles = new HashMap<>();
    }

    public int offerFile(String filePath){
        int port;
        while(true){
            port = UploadUtils.generateCode();
            if(!avialableFiles.containsKey(port)){
                avialableFiles.put(port , filePath);
                return port;
            }
        }
    }

    public void startFileServer(int port){
        String filePath = avialableFiles.get(port);

        if(filePath == null){
            System.out.println("file not found at port " + port);
            return;
        }

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Serving File " +new File(filePath).getName() + "on port " + port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted Client connection from "+ clientSocket.getInetAddress());
            new Thread(new FileSenderHandler(clientSocket, filePath)).start();
        }
        catch(IOException ex){
            System.err.println("Error handling file server on port: "+port);
        }
    }

    private static class FileSenderHandler implements Runnable{

        private final Socket clientSocket;
        private final String filePath;
        public FileSenderHandler(Socket clientSocket, String filePath){
            this.clientSocket = clientSocket;
            this.filePath = filePath;
        }

        @Override
        public void run(){
            try(FileInputStream fis = new FileInputStream(filePath)){
                OutputStream oos = clientSocket.getOutputStream();
                String fileName = new File(filePath).getName();
                String header = "Filename: "+fileName+"\n";
                oos.write(header.getBytes());

                byte[] buffer = new byte[4096];
                int bytesRead;
                while((bytesRead = fis.read(buffer)) != -1){
                    oos.write(buffer, 0, bytesRead);

                }
                System.out.println("File "+fileName + "sent to "+ clientSocket.getInetAddress()+ " successfully");

            }catch (Exception ex) {
                System.err.println("Error sending file to the client "+ ex.getMessage());
            }finally{
                try{
                    clientSocket.close();
                }
                catch (Exception ex){
                    System.err.println("Error closing socket "+ ex.getMessage());
                }
            }
        }

    }
}
