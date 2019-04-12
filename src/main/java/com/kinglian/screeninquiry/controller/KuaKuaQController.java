/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: KuaKuaQController
 * Author:   weiyz
 * Date:     2019/3/29 10:53
 * Description: 夸夸群
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.controller;

import cn.kinglian.spring.util.Query;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.model.Vo.UserVo1;
import com.kinglian.screeninquiry.model.entity.Complain;
import com.kinglian.screeninquiry.model.entity.Order;
import com.kinglian.screeninquiry.model.entity.Qun;
import com.kinglian.screeninquiry.model.entity.User;
import com.kinglian.screeninquiry.service.*;
import com.kinglian.screeninquiry.utils.CreateParmsCode;
import com.kinglian.screeninquiry.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈夸夸群〉
 * @author weiyz
 * @create 2019/3/29
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class KuaKuaQController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ComplainService complainService;

    @Autowired
    private GroupService groupService;
    @Autowired
    private CreateParmsCode createParmsCode;

    @Autowired
    private SysUserService userService;

    @Autowired
    private uploadService  uploadService;

    @Autowired
    private  QunService qunService;

    /**
     * 获取订单列表
     * @param params
     * @return
     */
    @GetMapping("getOrders")
    public R<Page> getOrders(@RequestParam Map<String, Object> params){
       String openId = createParmsCode.getOpenId((String) params.get("code"));
        String productId = (String) params.get("productId");
        params.put("openId",openId);
        orderService.updateOpenId(openId,productId);
        return new R<>(orderService.getOrders(new Query<Map>(params)));
    }

    /**
     * 获取订单详情
     * @param
     * @return
     */
    @GetMapping("getOrderDetail")
    public R<Order> getOrderById(@RequestParam String orderId){
        return new R<>(orderService.getOrderById(orderId));
    }

    /**
     * 获取商家列表
     */
    @GetMapping("getVendors")
    public R<Page> getVendors(
            @RequestParam(value="currentPage",defaultValue = "1") int currentPage,
            @RequestParam(value="pageSize",defaultValue = "10") int pageSize
    ){
        Map<String, Object> params = new HashMap<>();
        Query<Map> mapQuery =  new Query<Map>(params);
        mapQuery.setCurrent(currentPage);
        mapQuery.setSize(pageSize);
        return new R<>(vendorService.getVendors(mapQuery));
    }

    /**
     * 根据商家id获取商家拥有的产品
     */
    @GetMapping("getProductByVendorId")
    public R<Page> getProductByVendorId(@RequestParam Map<String, Object> params){
        return new R<>(productService.getProductByVendorId(new Query<Map>(params)));
    }

    /**
     * 投诉
     * @param complain
     * @return
     */
    @PostMapping("insertComplain")
    public R<Boolean> insertComplain(@RequestBody Complain complain){
        return new R<>(complainService.insert(complain));
    }

    /**
     *  夸夸群发布与修改
     * @param qun
     * @return
     */
    @PostMapping("insertGroup")
    public R<Boolean> insertGroup(@RequestBody Qun qun){
        return new R<> (qunService.insertOrUpdate(qun));
    }

    /**
     * 获得未完成订单
     */
    @GetMapping("getOrderUnFinished")
    public R<Page> getOrderUnFinished(@RequestParam Map<String, Object> params){
        String openId = createParmsCode.getOpenId((String) params.get("code"));
        params.put("openId",openId);
        return new R<>(orderService.getOrderUnFinished(new Query<Map>(params)));
    }

    /**
     *  保存并返回用户信息
     * @param params
     * @return
     */
    @PostMapping("saveUser")
    public R<UserVo1> saveUser(@RequestBody String params){
        JSONObject object = JSONObject.parseObject(params);
        String code =  object.getString("code");
        String rawData =  object.getString("rawData");
        JSONObject jsonObject = JSON.parseObject(rawData);
        String nickname = (String) jsonObject.get("nickName");
        String avatarUrl = (String) jsonObject.get("avatarUrl");
        String gender = jsonObject.get("gender").toString();
        Map<String, String> data = createParmsCode.getUserInfo(code);
        String openId = data.get("openid");
        //创建用户并赋值
        User user = new User();
        UserVo1 userVo1s = null;
        user.setOpenId(openId);
        user.setUsername(nickname);
        user.setNickname(nickname);
        user.setAvatar(avatarUrl);
        //sex == "1"?"1":"2"  1:男;2:女
        user.setGender(gender);
        if (openId != null && !"".equals(openId)){
            userVo1s = userService.getUserByOpenId(openId);
            if(userVo1s!=null){
               userService.deleteById(userVo1s);
            }
            userService.insert(user);
        }
        return new R<>(userVo1s);
    }

    /**
     *  登录(暂时不用)
     * @return
     * @throws Exception
     */
/*   @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable("id") String openId) throws Exception {
       log.info("id,{}",openId);
       final UserVo1 userVo1 = userService.getUserByOpenId(openId);
     return new R<> (  userService.deleteById(userVo1));
    }*/

    /**
     * 注册
     * @param
     * @return
     */
    /*@PostMapping("/register")
    @Transactional
    public R<Boolean> register(@RequestBody UserVO userDto) {

        SysUser user2 = new SysUser();
        user2.setUsername(userDto.getUsername());
        user2.setDelFlag("0");
        SysUser user1 = userService.selectOne(new EntityWrapper<>(user2));
        Assert.isNull(user1, "该账号已被使用");

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);

        sysUser.setDelFlag(CommonConstant.STATUS_NORMAL);
        Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
        String pass = passwordEncoder.encodePassword(userDto.getPassword(), null);
        sysUser.setPassword(pass);
        sysUser.setCreateTime(DateConvertUtils.dateToStr(new Date()));
        userService.insert(sysUser);*/

       /* if (userDto.getRoleId() != null) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(userDto.getRoleId());
            userRole.insert();
        }*/
//        userDto.getRoleList().forEach(role -> {
//            SysUserRole userRole = new SysUserRole();
//            userRole.setUserId(sysUser.getUserId());
//            userRole.setRoleId(role.getRoleId());
//            userRole.insert();
//        });
//        return new R<>(Boolean.TRUE);
//    }


    @PostMapping("/upload")
    public R<String> upload(@RequestParam("file") MultipartFile file){
        return new R<>( uploadService.upload(file));
    }
}
