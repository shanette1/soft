package com.uestc.myss.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.uestc.myss.domain.MiaoshaUser;

@Mapper
public interface MiaoshaUserDao {
	
	@Select("select * from user where id = #{id}")
	public MiaoshaUser getById(@Param("id")long id);
	@Insert("insert into user(id,nickname,password,salt,register_date)values(#{id},#{nickname},#{password},#{salt},#{registerDate})")
	public void save(MiaoshaUser newUser);
}
