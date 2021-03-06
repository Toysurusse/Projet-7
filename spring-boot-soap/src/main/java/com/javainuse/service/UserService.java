package com.javainuse.service;

import com.javainuse.User;
import com.javainuse.mapper.UserMapper;
import com.javainuse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<com.javainuse.User> findAll() {
        List<com.javainuse.User> obj = userMapper.userListDtoE((List<com.javainuse.entity.User>) userRepository.findAll());
        return obj;
    }

    @Override
    public com.javainuse.User control(com.javainuse.User user){
        List<com.javainuse.User> userList = userMapper.userListDtoE((List<com.javainuse.entity.User>) userRepository.findAll());

        User test= new User();

        for (int i=0;i<userList.size();i++){
            if(user.getPseudo().equals(userList.get(i).getPseudo())){
                test=userList.get(i);
            }
        }
        return test;
    }

    @Override
    public String add(com.javainuse.User user) {
        String result="Ok";

            userRepository.save(userMapper.convertEtoD(user));

        return result;
    }

    @Override
    public void delete(com.javainuse.User user) {
        userRepository.delete(userMapper.convertEtoD(user));
    }

    @Override
    public com.javainuse.User findById(int id) {
        com.javainuse.User obj = userMapper.convertDtoE(userRepository.findOne(id));
        return obj;
    }
}
