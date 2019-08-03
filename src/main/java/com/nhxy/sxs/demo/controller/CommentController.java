package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.entity.Comment;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.CommentServiceImpl;
import com.nhxy.sxs.demo.utils.CheckToken;
import com.nhxy.sxs.demo.utils.UserTokenUtilImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>Class: CommentController</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/22 14:57
 */
@RestController
@RequestMapping(value = "/comment")
@Api(value = "comment", tags = "评论接口")
public class CommentController {

    @Autowired
    UserTokenUtilImpl tokenUtil;
    @Autowired
    CommentServiceImpl commentService;

    /**
     * @param content 评论内容
     * @param viewId  评论的景点id
     * @param star    评论星级
     * @return
     */
    @CheckToken(type = CheckToken.user_tpye)
    @PostMapping("/add")
    public BaseResponse add(@ApiParam(name = "token", value = "传入token") @RequestParam(name = "token") String token,
                            @ApiParam( value = "评论内容") @RequestParam("content") String content,
                            @ApiParam( value = "评论的景点id") @RequestParam("view_id") Integer viewId,
                            @ApiParam( value = "评论星级") @RequestParam(value = "star", required = false) Integer star) {
        Comment comment = new Comment(content,
                star,
                tokenUtil.getUser(token).getId(),
                viewId,
                new Date());
        try {
            commentService.add(comment);
        } catch (Exception e) {
            return new BaseResponse(StatusCode.Fail);
        }
        return new BaseResponse(StatusCode.Success);
    }

    @GetMapping("/get_list")
    public BaseResponse getList(@RequestParam("view_id") Integer viewId,
                                @RequestParam(name = "page_num", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "page_size", required = false, defaultValue = "10") Integer pageSize) {

        if (viewId < 0 | pageNum < 0 | pageSize < 0) {//值范围 判断
            return new BaseResponse(StatusCode.Fail);
        }
        ;
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        baseResponse.setData(commentService.getList(viewId, pageNum, pageSize));
        return baseResponse;
    }
}
