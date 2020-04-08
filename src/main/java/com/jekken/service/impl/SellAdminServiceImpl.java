package com.jekken.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.jekken.bo.AdminUserDetails;
import com.jekken.dao.SellAdminRoleRelationDao;
import com.jekken.dto.AdminParam;
import com.jekken.dto.UpdateAdminPasswordParam;
import com.jekken.mapper.SellAdminLoginLogMapper;
import com.jekken.mapper.SellAdminMapper;
import com.jekken.mapper.SellAdminPermissionRelationMapper;
import com.jekken.mapper.SellAdminRoleRelationMapper;
import com.jekken.pojo.SellAdmin;
import com.jekken.pojo.SellAdminExample;
import com.jekken.pojo.SellAdminLoginLog;
import com.jekken.pojo.SellResource;
import com.jekken.service.SellAdminService;
import com.jekken.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private SellAdminRoleRelationDao adminRoleRelationDao;





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
            if (!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        }catch (AuthenticationException e){
            log.warn("【登录】登录异常:{}",e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username
     */
    private void insertLoginLog(String username){
        SellAdmin admin = getAdminByUsername(username);
        if (admin==null){
            return;
        }
        SellAdminLoginLog loginLog = new SellAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);

    }



    @Override
    public List<SellAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        SellAdminExample example = new SellAdminExample();
        SellAdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)){
            criteria.andUsernameLike("%"+keyword+"%");
            example.or(example.createCriteria().andNickNameLike("%"+keyword+"%"));
        }
        return sellAdminMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, SellAdmin admin) {
       admin.setId(id);
       SellAdmin rawAdmin = sellAdminMapper.selectByPrimaryKey(id);
       if (rawAdmin.getPassword().equals(admin.getPassword())){
           //与原密码相同不需要修改
           admin.setPassword(null);
       }else {
           //与原密码不同需要加密修改
           if (StrUtil.isEmpty(admin.getPassword())){
               admin.setPassword(null);
           }else {
               admin.setPassword(passwordEncoder.encode(admin.getPassword()));
           }
       }

       int count = sellAdminMapper.updateByPrimaryKeySelective(admin);

        return count;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam) {
        if (StrUtil.isEmpty(updateAdminPasswordParam.getUsername())||StrUtil.isEmpty(updateAdminPasswordParam.getOldPassword())||StrUtil.isEmpty(updateAdminPasswordParam.getNewPassword())){

            return -1;
        }
        SellAdminExample example = new SellAdminExample();
        example.createCriteria().andUsernameEqualTo(updateAdminPasswordParam.getUsername());
        List<SellAdmin> adminList = sellAdminMapper.selectByExample(example);
        if (CollUtil.isEmpty(adminList)){
            return -2;
        }
        SellAdmin sellAdmin = adminList.get(0);
        if (!passwordEncoder.matches(updateAdminPasswordParam.getOldPassword(),sellAdmin.getPassword())){
            return -3;
        }
        sellAdmin.setPassword(passwordEncoder.encode(updateAdminPasswordParam.getNewPassword()));
        sellAdminMapper.updateByPrimaryKey(sellAdmin);
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        SellAdmin admin=getAdminByUsername(username);
        if (admin !=null){
            List<SellResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public List<SellResource> getResourceList(long adminId) {

        List<SellResource> resourceList = adminRoleRelationDao.getResourceList(adminId);

        return resourceList;
    }
}
