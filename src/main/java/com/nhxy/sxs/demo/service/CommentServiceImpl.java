package com.nhxy.sxs.demo.service;

import com.github.pagehelper.PageHelper;
import com.nhxy.sxs.demo.dto.CommentDTO;
import com.nhxy.sxs.demo.entity.Comment;
import com.nhxy.sxs.demo.mapper.CommentMapper;
import com.nhxy.sxs.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>Class: CommentServiceImpl</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/22 14:59
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentMapper {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Comment record) {
        return 0;
    }

    @Override
    public int insertSelective(Comment record) {
        return 0;
    }

    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<Comment> selectByViewId(Integer viewId) {
        return commentMapper.selectByViewId(viewId);
    }

    @Override
    public Comment selectByUserIdAndViewId(Integer userId, Integer viewId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return 0;
    }

    public boolean add(Comment comment) throws Exception {
        if (comment.getContent().length() > 255) {
            log.debug("评论字数过长");
            throw new Exception("评论字数过长");
        }
        Comment querycomment = commentMapper.selectByUserIdAndViewId(comment.getUserId(), comment.getViewId());
        if (querycomment != null) {
            log.debug("用户已经在此景区评论过了");
            throw new Exception("用户已经在此景区评论过了");
        }
        commentMapper.insertSelective(comment);
        return true;
    }

    public List<CommentDTO> getList(int viewId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = selectByViewId(viewId);
        List<CommentDTO> commentDTOList = new LinkedList<>();
        for (Comment comment : commentList) {
            int userId=comment.getUserId();
            CommentDTO commentDTO = new CommentDTO(comment.getContent(),
                    comment.getStar(),
                    userMapper.selectByPrimaryKey(userId).getUsername(),
                    "/user/getpicture/"+userId,
                    comment.getViewId(),
                    comment.getCreateTime());
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;

    }
}
