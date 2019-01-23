package com.kinglian.screeninquiry.model.entity;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Data
public class UdpEntity {
    String clientAddress1;
    int clientPort1;
    String clientAddress2;
    int clientPort2;
    int udpServerPort;
    /**
     * 0 等待
     * 1 收到client1 的信息，但是没有收到client2信息
     * 2 收到client1 信息，也收到client2 信息，并且完成互相通信；
     */
    int status;

    DatagramSocket socket;

    public UdpEntity() throws IOException {
        openUdpPort(udpServerPort);
    }

    public UdpEntity(int status, int udpServerPort) throws IOException {
        this.status = status;
        this.udpServerPort = udpServerPort;
        openUdpPort(udpServerPort);
    }

    public UdpEntity(String clientAddress1, int clientPort1, String clientAddress2, int clientPort2, int udpServerPort, int status) throws IOException {
        this.clientAddress1 = clientAddress1;
        this.clientPort1 = clientPort1;
        this.clientAddress2 = clientAddress2;
        this.clientPort2 = clientPort2;
        this.udpServerPort = udpServerPort;
        this.status = status;
        openUdpPort(udpServerPort);
    }

    /**
     * 打开udp端口
     * @param port
     * @throws IOException
     */
    public void openUdpPort(int port) throws IOException {
        socket = new DatagramSocket(port);
        DatagramSocket socket = new DatagramSocket(port);
        while(!socket.isClosed()) {
            DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
            socket.receive(request);
            if (StringUtils.isEmpty(clientAddress1)){
                clientAddress1 = request.getAddress().getHostAddress();
                clientPort1 = request.getPort();
            }else if (StringUtils.isEmpty(clientAddress2)){
                clientAddress2 = request.getAddress().getHostAddress();
                clientPort2 = request.getPort();
            }
            if (!StringUtils.isEmpty(clientAddress1) && StringUtils.isEmpty(clientAddress2) && !clientAddress1.equals(request.getAddress().getHostAddress())) {
                DatagramPacket response = new DatagramPacket(request.getData(), request.getData().length, InetAddress.getByName(clientAddress2), request.getPort());
                socket.send(response);
            }else if (!StringUtils.isEmpty(clientAddress1) && StringUtils.isEmpty(clientAddress2) && !clientAddress2.equals(request.getAddress().getHostAddress())){
                DatagramPacket response = new DatagramPacket(request.getData(), request.getData().length, InetAddress.getByName(clientAddress1), request.getPort());
                socket.send(response);
            }
        }
    }

    /**
     * 打开udp端口
     * @param
     * @throws IOException
     */
    public void closePort(){
        socket.close();
    }




}
