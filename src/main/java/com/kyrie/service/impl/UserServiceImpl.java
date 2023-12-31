package com.kyrie.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyrie.mapper.UserMapper;
import com.kyrie.pojo.User;
import com.kyrie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@DS("#header.schemaName")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String queryUser(Long id) {

        User user = userMapper.findById(id);
        if (!Objects.isNull(user)) {
            return "查询到用户：" + user.toString();
        }
        return "没找到用户";
    }

    @Override
    public String add(User user) {
        int insert = userMapper.insert(user);
        if (insert > 0) {
            return "添加成功！";
        }
        return "添加失败！";
    }
}
