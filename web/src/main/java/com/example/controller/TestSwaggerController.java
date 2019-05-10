package com.example.controller;

import com.example.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试Swagger2 〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/5/0005 14:21
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@RestController
@RequestMapping(value = "/users") //通过这里的配置使下面的映射都在/users下，可去除
public class TestSwaggerController {

    static Map<Integer,User> users = Collections.synchronizedMap(new HashMap<Integer,User>());

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<User> getUserList(){
        List<User> userList = new ArrayList<User>(users.values());
        return userList;
    }


    @ApiOperation(value = "创建用户", notes = "根据url的id 来获取用户的详细信息")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "SysUser")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        users.put(user.getUid(), user);
        return "success";
    }



    @ApiOperation(value = "获取用户详细信息", notes = "根据url 的id 来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID",  required = true, dataType = "SysUser")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable Integer id){
        return users.get(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "SysUser")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Integer id, @RequestBody User user) {
        User u = users.get(id);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        users.put(id, u);
        return "success";
    }


    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID",  required = true, dataType = "Integer")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Integer id) {
        users.remove(id);
        return "success";
    }





}
