package com.uestc.myss.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.uestc.myss.domain.MiaoshaGoods;
import com.uestc.myss.loginVo.GoodsVo;

@Mapper
public interface GoodsDao {
	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
	public List<GoodsVo> listGoodsVo();
	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id limit #{start},#{pagesize}")
	public List<GoodsVo> listGoodsVoByPage(@Param("start")int start,@Param("pagesize")int pagesize);
	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{Id}")
	public GoodsVo getGoodsVoByGoodsId(@Param("Id")Long Id);
	@Update("update miaosha_goods set stock_count=stock_count-1 where goods_id = #{Id}")
	public int reduceStock(@Param("Id")Long Id);
}
