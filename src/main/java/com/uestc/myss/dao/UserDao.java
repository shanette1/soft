package com.uestc.myss.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.uestc.myss.domain.User; 

@Mapper
public interface UserDao {
     @Select("select * from User where id=#{id}")
	public User  getById(@Param("id") int id);
}
