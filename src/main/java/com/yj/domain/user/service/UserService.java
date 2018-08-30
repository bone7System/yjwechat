package com.yj.domain.user.service;

import com.yj.domain.user.model.User;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.system.user.UserDto;
import com.yj.pojo.system.user.UserSearchDto;
import com.yj.pojo.system.user.UserUpPasswordDto;
import org.springframework.data.domain.Pageable;

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
