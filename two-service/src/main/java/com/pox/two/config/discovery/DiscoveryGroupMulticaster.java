package com.pox.two.config.discovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class DiscoveryGroupMulticaster {

    @Value("${spring.application.name}")
    private String appName;

    private final DatagramSocket socket;

    private final ExecutorService senderPool = Executors.newFixedThreadPool(1);

    public DiscoveryGroupMulticaster() throws IOException {
        socket = new DatagramSocket();
        multicast();
    }

    public void multicast() throws IOException {
        final InetAddress group = InetAddress.getByName(MulticastDiscovery.MULTICAST_GROUP);

        // format is <join msg> <service name> <hostname>
        // hostname assumes we're in a default docker-compose network
        final byte[] msg = ("joining two http://two:8080").getBytes();

        senderPool.execute(() -> {
            for (;;) {
                try {
                    final DatagramPacket packet
                        = new DatagramPacket(msg, msg.length, group, MulticastDiscovery.MULTICAST_PORT);
                    socket.send(packet);
                    Thread.sleep(7000); // busy wait!
                } catch (IOException e) {
                    log.error("Unable to multicast join msg", e);
                    break;
                } catch (InterruptedException e) {
                    log.info("Interrupted. Shutting down.", e);
                    stop(); // again ... so optimistic
                    break;
                }
            }
        });
    }

    public void stop() {
        // so optimistic this ...
        senderPool.shutdown();
        socket.close();
    }
}
