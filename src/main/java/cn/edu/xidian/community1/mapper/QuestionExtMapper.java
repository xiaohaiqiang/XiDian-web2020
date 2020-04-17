package cn.edu.xidian.community1.mapper;

import cn.edu.xidian.community1.dto.QuestionQueryDTO;
import cn.edu.xidian.community1.model.Question;
import cn.edu.xidian.community1.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}