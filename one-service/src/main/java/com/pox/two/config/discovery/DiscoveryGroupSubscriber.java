package com.pox.two.config.discovery;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.pox.two.config.discovery.MulticastDiscovery.*;

@Slf4j
@Component
@NotThreadSafe
public class DiscoveryGroupSubscriber {

    private final MulticastSocket socket;
    private final InetAddress group;
    private final NetworkInterface networkInterface;

    private final byte[] packet_buf = new byte[256];

    private final Executor listenerPool = Executors.newFixedThreadPool(1);

    // !! STATE !!
    private final Map<String, String> discoveredServices;

    public DiscoveryGroupSubscriber() throws IOException {
        super();
        this.socket = new MulticastSocket(MULTICAST_PORT);
        this.group = InetAddress.getByName(MULTICAST_GROUP);
        this.networkInterface = NetworkInterface.getByName(MULTICAST_INTERFACE);
        this.discoveredServices = new HashMap<>();

        listen();
    }

    public void listen() {
        listenerPool.execute(() -> {
            try {
                socket.joinGroup(new InetSocketAddress(group, MULTICAST_PORT), networkInterface);
                while (true) {
                    final DatagramPacket packet = new DatagramPacket(packet_buf, packet_buf.length);
                    socket.receive(packet);

                    final String received = new String(packet.getData(), 0, packet.getLength());
                    log.info("Received: " + received);

                    if ("stop".equals(received)) {
                        log.info("Received stop, stopping.");
                        break;
                    }
                    else if (received.startsWith("joining")) {

                        // if anyone ever mentions me in relation to this code
                        // I will deny I wrote this. You can't prove anything.
                        final String[] joinMessageWords = received.split(" ");
                        final String serviceName = joinMessageWords[1];
                        final String serviceHost = joinMessageWords[2];

                        log.info("Adding service name {} with host {}, to discovered services", serviceName, serviceHost);

                        discoveredServices.put(serviceName, serviceHost);
                    }

                    Thread.sleep(50); // TODO can replace busy wait with notify
                }
            } catch (IOException | InterruptedException e) {
                // TODO
                e.printStackTrace();
            }
            finally {
                try {
                    socket.leaveGroup(new InetSocketAddress(group, MULTICAST_PORT), networkInterface);
                } catch (IOException e) {
                    log.error("Unable to leave multicast group: " + group, e);
                }
                socket.close();
            }
        });
    }

    public ImmutableMap<String, String> getDiscoveredServices() {
        return ImmutableMap.copyOf(this.discoveredServices);
    }
}
