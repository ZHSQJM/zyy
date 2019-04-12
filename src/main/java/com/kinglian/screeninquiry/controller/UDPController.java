package com.kinglian.screeninquiry.controller;

import com.kinglian.screeninquiry.model.entity.PortManager;
import com.kinglian.screeninquiry.model.entity.UdpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/udp")
public class UDPController {

    private static List<UdpEntity> udpEntites = new ArrayList<>();

    private static Map<String ,UdpEntity> udpEntityMap = new HashMap<>();

    /**
     * 获取已经打开的UDP端口
     * @return
     * @throws IOException
     */
    @GetMapping("/getUdpAddress")
    public Object getUdpAddress() throws IOException {
        // 判断是否已经开放端口给客户端
        int port = PortManager.getCanUserPorts();
        UdpEntity udpEntity = new UdpEntity(1, port);
        udpEntites.add(udpEntity);
        udpEntityMap.put(String.valueOf(port), udpEntity);
        return PortManager.getInternetIp() + port;
    }

    /**
     * 关闭UDP指定端口
     * @param port
     * @return
     */
    @GetMapping("/closeUdpPort")
    public int closeUdpPort(int port) {
        UdpEntity udpEntity = udpEntityMap.get(String.valueOf(port));
        udpEntity.closePort();
        return 1;
    }
}
