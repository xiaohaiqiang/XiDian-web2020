package cn.edu.xidian.community1.controller;

import cn.edu.xidian.community1.dto.CommentCreateDTO;
import cn.edu.xidian.community1.dto.CommentDTO;
import cn.edu.xidian.community1.dto.ResultDTO;
import cn.edu.xidian.community1.enums.CommentTypeEnum;
import cn.edu.xidian.community1.exception.CustomizeErrorCode;
import cn.edu.xidian.community1.model.Comment;
import cn.edu.xidian.community1.model.User;
import cn.edu.xidian.community1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if(commentCreateDTO == null || commentCreateDTO.getContent() == null || commentCreateDTO.getContent() == ""){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        commentService.insert(comment);

        return ResultDTO.okOf();
    }


    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
