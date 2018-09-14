package com.yj.domain.user.service;

import com.yj.domain.user.model.Dept;
import com.yj.domain.user.model.User;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.model.UserRole;
import com.yj.domain.user.repository.*;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptRepository deptRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailRepository userDetailRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    /**
     * 超级管理员添加门店
     * @param dept
     * @param user
     * @return
     */
    @Override
    public synchronized  ReSult  addDept(Dept dept, UserDetail user) {
        //生成client客户端
        Long client= deptRepository.findbyMaxClient();
        if(client==null){
            client=1000L;
        }
        client++;
        dept.setClient(client);
        dept.setIsSystem(1L);//系统注册
        dept.setCreateTime(new Date());
        dept.setCreateUser(user.getId());

        //创建门店
        deptRepository.save(dept);

        //设置path路径
        dept.setPath("/"+dept.getId());
        deptRepository.save(dept);


        //创建老板
        User login = new User();
        int rand=(int)((Math.random()*9+1)*100000);
        login.setPassWord(new BCryptPasswordEncoder().encode(rand+""));
        login.setTimeb(new Date());
        login.setTimee(new Date(253402185600000L));
        userRepository.save(login);

        //用户详情
        UserDetail detail = new UserDetail();
        detail.setUserId(login.getId());
        detail.setClient(dept.getClient());
        detail.setName(dept.getDeptName());
        detail.setDeptId(dept.getId());
        userDetailRepository.saveAndFlush(detail);

        //配置老板角色
        UserRole ur=new UserRole();
        ur.setClient(dept.getClient());
        ur.setRoleId(5L);//"boss"角色门店最高权限
        ur.setUserId(login.getId());
        userRoleRepository.save(ur);

        return ReSult.success(login);
    }

    /**
     * 门店人员添加部门
     * @param dept
     * @param user
     * @return
     */
    public   ReSult  addNewDept(Dept dept, UserDetail user){

        dept.setClient(user.getClient());
        dept.setCreateTime(new Date());
        dept.setCreateUser(user.getId());

        Long pid = dept.getParentId();

        //创建部门
        deptRepository.save(dept);

        Dept pdept= deptRepository.findById(pid).get();
        //设置path路径
        dept.setPath(pdept.getPath()+"/"+dept.getId());

        deptRepository.save(dept);

        return ReSult.success();

    }

    @Override
    public ReSult updateDept(Dept dept, UserDetail user) {
        Dept dp=deptRepository.findById(dept.getId()).get();
        if(!StringUtils.isEmpty(dept.getDeptName())){
            dp.setDeptName(dept.getDeptName());
        }
        deptRepository.save(dp);
        return ReSult.success();
    }

    /**
     * 超级管理员权限 删除门店
     * @param id
     * @param user
     * @return
     * @throws YjException
     */
    @Override
    public ReSult deleteDept(Long id, UserDetail user) throws YjException {
        Dept dept=deptRepository.findById(id).get();
        if(dept==null){
            throw  new YjException("门店未找到");
        }
        dept.setStatus(-1L);//删除

        List<UserDetail> list =userDetailRepository.findByClient(dept.getClient());
        if(list!=null){
            list.stream().forEach(detail->{
               User u= userRepository.findById(detail.getUserId()).get();
               if(u!=null){
                   u.setStatus(-1L);
                   userRepository.save(u);
               }
            });
        }

        return ReSult.success();
    }

    /**
     * 普通人员权限 删除一个部门
     * @param id
     * @param user
     * @return
     * @throws YjException
     */
    public ReSult deleteNormarDept(Long id, UserDetail user) throws YjException {
        Dept dept=  deptRepository.findById(id).get();
        if(dept.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
       List<Dept> list= deptRepository.findByParentId(id);
       if(list!=null && list.size()>0){
           throw  new YjException("该部门存在子部门，不允许删除");
       }
        deptRepository.deleteById(id);
        return ReSult.success();
    }
}
