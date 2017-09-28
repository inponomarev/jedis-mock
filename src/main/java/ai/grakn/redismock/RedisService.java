package ai.grakn.redismock;

import ai.grakn.redismock.commands.RedisOperationExecutor;
import com.google.common.base.Preconditions;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Xiaolu on 2015/4/21.
 */
public class RedisService implements Runnable {

    private final ServerSocket server;
    private final RedisOperationExecutor executor;
    private final ServiceOptions options;

    public RedisService(ServerSocket server, RedisOperationExecutor executor, ServiceOptions options) {
        Preconditions.checkNotNull(server);
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(options);

        this.server = server;
        this.executor = executor;
        this.options = options;
    }

    public void run() {
        while (!server.isClosed()) {
            try {
                Socket socket = server.accept();
                Thread t = new Thread(new RedisWorker(executor, socket, options));
                t.start();
            } catch (IOException e) {
                // Do noting
            }
        }
    }
}
