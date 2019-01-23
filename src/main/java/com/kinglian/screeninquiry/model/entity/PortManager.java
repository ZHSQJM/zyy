package com.kinglian.screeninquiry.model.entity;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 端口管理器
 */
@Slf4j
public class PortManager {

    /**
     * 用户正在使用地址
     */
    public static List<Integer> userPorts ;
    /**
     * 所有java可使用地址
     */
    public static List<Integer> fullPorts;

    /**
     * 剩下java可用地址
     */
    List<Integer> leftPorts = new ArrayList<>();
    static {
        fullPorts = new ArrayList<>();
        userPorts = new ArrayList<>();
        String localhost = "127.0.0.1";
        for (int i = 30000; i < 50000; i++) {
            try {
                if (isPortNotUsing(localhost, i)){
                    fullPorts.add(i);
                }
            } catch (UnknownHostException e) {
                log.error("错误的本地地址，请重新设置");
            }

        }
    }

    /**
     * 获取最新可用端口
     * @return 获取可用端口
     * @throws ServerException 服务器获取可用端口异常
     */
    public static int getCanUserPorts() throws ServerException {
        if (fullPorts.size() > 0) {
            int allowPort = fullPorts.get(0);
            userPorts.add(allowPort);
            return allowPort;
        }else {
            throw new ServerException("java sever port not enough.");
        }
    }

    /**
     * 判断端口是否被暂用
     * @param host 主机
     * @param port 端口
     * @return true 为未被暂用 false 为暂用
     * @throws UnknownHostException 为知的服务器异常
     */
    public static boolean isPortNotUsing(String host, int port) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(address, port);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     *
     * @return 获取内网ip
     * @throws UnknownHostException 无法获取内网ip
     */
    public static String getInNetIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 获取外网端口
     * @return 外网端口
     * @throws UnknownHostException 无法获取内网服务器异常
     */
    public static String getInternetIp() throws UnknownHostException {
        String INTRANET_IP = getInNetIp();
        try{
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements())
            {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements())
                {
                    ip = addrs.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INTRANET_IP))
                    {
                        return ip.getHostAddress();
                    }
                }
            }

            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
