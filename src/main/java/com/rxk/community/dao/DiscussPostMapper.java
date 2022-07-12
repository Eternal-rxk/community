package com.rxk.community.dao;

import com.rxk.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //查询userId的全部评论，并分页 userId为0 代表查询所有数据  为100表示查询userId为100的数据
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);//评论会分页  offset是起始行号 limit表示每一页显示多少行

    //查询某个人有多少条评论
    // @param注解用于给参数取别名
    //如果只有一个参数，并且在<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);


}
