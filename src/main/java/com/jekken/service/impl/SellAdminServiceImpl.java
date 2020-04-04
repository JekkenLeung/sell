package com.jekken.service.impl;

import com.jekken.dto.AdminParam;
import com.jekken.dto.UpdateAdminPasswordParam;
import com.jekken.mapper.SellAdminLoginLogMapper;
import com.jekken.mapper.SellAdminMapper;
import com.jekken.mapper.SellAdminPermissionRelationMapper;
import com.jekken.mapper.SellAdminRoleRelationMapper;
import com.jekken.pojo.SellAdmin;
import com.jekken.pojo.SellAdminExample;
import com.jekken.pojo.SellResource;
import com.jekken.service.SellAdminService;
import com.jekken.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Create by Jekken
 * 2020/4/2 21:02
 */
@Service
@Slf4j
public class SellAdminServiceImpl implements SellAdminService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SellAdminMapper sellAdminMapper;

    @Autowired
    private SellAdminRoleRelationMapper adminRoleRelationMapper;

    @Autowired
    private SellAdminPermissionRelationMapper adminPermissionRelationMapper;

    @Autowired
    private SellAdminLoginLogMapper loginLogMapper;





    @Override
    public SellAdmin getAdminByUsername(String username) {
        SellAdminExample example = new SellAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SellAdmin> list = sellAdminMapper.selectByExample(example);
        if (list!=null&&list.size()>0){
            SellAdmin admin = list.get(0);
            return admin;
        }
        return null;
    }

    @Override
    public SellAdmin register(AdminParam adminParam) {
        SellAdmin sellAdmin = new SellAdmin();
        BeanUtils.copyProperties(adminParam,sellAdmin);
        sellAdmin.setCreateTime(new Date());
        sellAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        SellAdminExample example = new SellAdminExample();
        example.createCriteria().andUsernameEqualTo(sellAdmin.getUsername());
        List<SellAdmin> adminList = sellAdminMapper.selectByExample(example);
        if (adminList.size()>0){
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(sellAdmin.getPassword());
        sellAdmin.setPassword(encodePassword);
        sellAdminMapper.insert(sellAdmin);
        return sellAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);

        }catch (AuthenticationException e){
            log.warn("【登录】登录异常:{}",e.getMessage());
        }

        return null;
    }

    @Override
    public SellAdmin getAdminById(Long id) {
        return null;
    }

    @Override
    public List<SellAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public int update(Long id, SellAdmin admin) {
        return 0;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam) {
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        SellAdmin admin=getAdminByUsername(username);



        return null;
    }

    @Override
    public List<SellResource> getResourceList(long adminId) {



        return null;
    }
}
