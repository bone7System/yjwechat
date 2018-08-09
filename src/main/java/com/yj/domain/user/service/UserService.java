package com.yj.domain.user.service;

import com.yj.domain.user.model.User;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.UserDto;
import com.yj.pojo.UserSearchDto;
import com.yj.pojo.UserUpPasswordDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

  /**
   * 根据用户名 查找用户
   * @param userName
   * @return
   */
  User getUserByUserName(String userName);

  /**
   * 根据userId查找用户
   * @param userId
   * @return
   */
  UserDetail findByUserId(Long userId);


  ReSult addUser( UserDto userDto) throws Exception;

  ReSult userUpPassword( UserUpPasswordDto dto,String userName,Long isMe) throws Exception;

  ReSult updateUser( UserDetail userDetail);

  ReSult searchUser(UserSearchDto dto,Pageable pageable);


}
