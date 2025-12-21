package p2p.controller;

import com.sun.net.httpserver.HttpServer;
import p2p.service.FileSharer;

import java.util.concurrent.ExecutorService;

public class FileController {

    private final FileSharer fileSharer;
    private final HttpServer  server;
    private final String uploadDir;
    private final ExecutorService executor;
}
