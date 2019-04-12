package com.kinglian.screeninquiry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/9 13:06
 * @package: com.kinglian.screeninquiry.controller
 * @description:
 */

@RestController
@RequestMapping("/api")
public class GroupController {



 /*   @Autowired
    private GroupService groupService;

    @GetMapping("getGroups")
    public R<Page> getGroups(@RequestParam(required = false) String groupName){
        Map<String, Object> params = new HashMap<>();
        params.put("groupName",groupName);
        return new R<>(groupService.getGroup(new Query<Map>(params)));
    }*/
}
