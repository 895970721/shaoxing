package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.entity.LikeFamous;
import com.nhxy.sxs.demo.entity.LikeView;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.LikeFamousServiceImpl;
import com.nhxy.sxs.demo.service.UserLikeServiceImpl;
import com.nhxy.sxs.demo.utils.CheckToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Class: UserLikesController</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/3 10:47
 */
@RestController
@Api(tags = "用户喜欢操作接口")
@RequestMapping("/user/like")
public class UserLikesController {
    @Autowired
    UserLikeServiceImpl userLikeService;

    @Autowired
    LikeFamousServiceImpl likeFamousService;


    @GetMapping("/view/all")
    @ApiOperation("查询所有喜欢的景点")
    @CheckToken
    public BaseResponse all(@ApiParam("传入用户token") @RequestParam("token") String token) {
        List UserLikeList = userLikeService.all(token);
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        baseResponse.setData(UserLikeList);
        return baseResponse;
    }

    @PostMapping("/view/add/{view_id}")
    @ApiOperation("增加喜欢的景点接口")
    @CheckToken
    public BaseResponse add(@ApiParam("喜欢的景点id") @PathVariable("view_id") int viewId,
                            @ApiParam("传入token") @RequestParam("token") String token,
                            @ApiParam("景区名字") @RequestParam("view_title") String viewTitle,
                            @ApiParam("景区第一张图片") @RequestParam("picture_url") String pictureUrl) {
        StatusCode statusCode = userLikeService.add(viewId, token,viewTitle,pictureUrl);
        return new BaseResponse(statusCode);
    }


    @PostMapping("/view/delete/{id}")
    @ApiOperation("取消喜欢的景点")
    @CheckToken
    public BaseResponse delete(@ApiParam("表的id") @PathVariable("id") int id,
                               @ApiParam("传入token") @RequestParam("token") String token) {
        StatusCode statusCode = userLikeService.delete(id, token);
        BaseResponse baseResponse = new BaseResponse(statusCode);
        return baseResponse;
    }

    @GetMapping("/view/{view_id}")
    @ApiOperation(value = "查询某个景点是不是在用户喜欢列表中",notes = "true为有，false为没有")
    @CheckToken
    public BaseResponse query(@ApiParam("表的id") @PathVariable("view_id") int viewId,
                              @ApiParam("传入token") @RequestParam("token") String token) {
        LikeView likeView = userLikeService.selectByViewId(viewId, token);
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        Map result = new HashMap();
        if (likeView == null) {
            result.put("value", false);
            baseResponse.setData(result);
            return baseResponse;
        } else {
            result.put("value", true);
            baseResponse.setData(result);
            return baseResponse;
        }
    }

    @GetMapping("/famous/all")
    @ApiOperation("查询所有喜欢的名人")
    @CheckToken
    public BaseResponse famousAll(@RequestParam("token") String token) {
        List<LikeFamous> likeFamousList=likeFamousService.all(token);
        BaseResponse baseResponse=new BaseResponse(StatusCode.Success);
        baseResponse.setData(likeFamousList);
        return baseResponse;
    }

    @PostMapping("/famous/add/{famous_id}")
    @ApiOperation("增加喜欢的名人接口")
    @CheckToken
    public BaseResponse famousAdd(@ApiParam("喜欢的名人id") @PathVariable("famous_id") int famousId,
                            @ApiParam("传入token") @RequestParam("token") String token,
                            @ApiParam("名人名字") @RequestParam("famous_title") String famousTitle,
                            @ApiParam("名人图片") @RequestParam("picture_url") String pictureUrl) {
        likeFamousService.add(famousId,token,famousTitle,pictureUrl);
        return new BaseResponse(StatusCode.Success);
    }


    @PostMapping("/famous/delete/{id}")
    @ApiOperation("取消喜欢的名人")
    @CheckToken
    public BaseResponse famousDelete(@ApiParam("喜欢列表的id") @PathVariable("id") int id,
                               @ApiParam("传入token") @RequestParam("token") String token) {
        likeFamousService.delete(id);
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        return baseResponse;
    }

    @GetMapping("/famous/{famous_id}")
    @ApiOperation(value = "查询某个名人是不是在用户喜欢列表中",notes = "true为有，false为没有")
    @CheckToken
    public BaseResponse famousQuery(@ApiParam("名人的id") @PathVariable("famous_id") int famousId,
                              @ApiParam("传入token") @RequestParam("token") String token) {
        LikeFamous likeFamous = likeFamousService.selectByFamous(famousId, token);
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        Map result = new HashMap();
        if (likeFamous == null) {
            result.put("value", false);
            baseResponse.setData(result);
            return baseResponse;
        } else {
            result.put("value", true);
            baseResponse.setData(result);
            return baseResponse;
        }
    }
}
