package com.dome.springboot.mybatis.mapper;

import com.dome.springboot.mybatis.bean.RoleVO;
import com.dome.springboot.mybatis.bean.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface OtherUserMapper {


    List<Map> selectUser(String cuserid);

    int userinsert(UserVO vo);

    int insertBatchRole(List<RoleVO> list);

    int insertRole(RoleVO vo);

    List<UserVO> selectUserAndRole(int pk);

}
