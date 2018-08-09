package com.yj.domain.user.service;

import com.yj.domain.user.model.Dept;
import com.yj.domain.user.model.RolePermission;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;

import javax.validation.Valid;
import java.util.List;

public interface DeptService {
    ReSult addDept( Dept dept, UserDetail user);

    ReSult updateDept(Dept dept, UserDetail user);

    ReSult deleteDept(Long id, UserDetail user) throws YjException;

}
