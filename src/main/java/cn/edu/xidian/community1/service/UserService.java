package cn.edu.xidian.community1.service;

import cn.edu.xidian.community1.mapper.UserMapper;
import cn.edu.xidian.community1.model.User;
import cn.edu.xidian.community1.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);

        if(users.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //更新
            User dbUser = users.get(0);

            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setToken(user.getToken());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setId(users.get(0).getId());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo((long)dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
}
