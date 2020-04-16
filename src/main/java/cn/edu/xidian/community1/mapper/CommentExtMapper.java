package cn.edu.xidian.community1.mapper;

import cn.edu.xidian.community1.model.Comment;
import cn.edu.xidian.community1.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}