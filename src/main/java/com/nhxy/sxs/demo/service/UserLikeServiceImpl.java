package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.entity.LikeView;
import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.mapper.LikeViewMapper;
import com.nhxy.sxs.demo.utils.UserTokenUtilImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Class: UserLikeServiceImpl</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/3 11:16
 */
@Service
@Slf4j
public class UserLikeServiceImpl implements LikeViewMapper {

    @Autowired
    LikeViewMapper likeViewMapper;

    @Autowired
    UserTokenUtilImpl tokenUtil;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(LikeView record) {
        return 0;
    }

    @Override
    public int insertSelective(LikeView record) {
        return 0;
    }

    @Override
    public LikeView selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(LikeView record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(LikeView record) {
        return 0;
    }

    @Override
    public List<LikeView> selectByUser(User record) {
        return null;
    }

    @Override
    public List<LikeView> selectByLikeViewSelective(LikeView record) {
        return null;
    }


    public StatusCode add(int viewId, String token,String viewTitle,String pictureUrl) {
        User user = tokenUtil.getUser(token);
        LikeView likeView = new LikeView();
        likeView.setUserId(user.getId());
        likeView.setViewId(viewId);
        likeView.setPictureUrl(pictureUrl);
        likeView.setViewTitile(viewTitle);
        //首先判断数据库有没有相同记录
        if (likeViewMapper.selectByLikeViewSelective(likeView) .size()!=0 ) {
            Object o=likeViewMapper.selectByLikeViewSelective(likeView);
            StatusCode statusCode = StatusCode.Fail;
            statusCode.setMsg("此条记录已经存在于数据库");
            return statusCode;
        }
        //再插入
        int i = likeViewMapper.insertSelective(likeView);
        if (i == 1) {
            return StatusCode.Success;
        }
        return StatusCode.Fail;
    }

    public List all(String token) {
        List likeViewsList = likeViewMapper.selectByUser(tokenUtil.getUser(token));
        return likeViewsList;
    }

    public StatusCode delete(int id, String token) {
        User user = tokenUtil.getUser(token);
        LikeView likeView = new LikeView();
        likeView.setUserId(user.getId());
        likeView.setId(id);
        //先判断此条记录是否存在
        LikeView likeViewFormDB = likeViewMapper.selectByPrimaryKey(likeView.getId());
        if (likeViewFormDB == null) {
            StatusCode statusCode = StatusCode.Fail;
            statusCode.setMsg("数据不存在，删除失败");
            return statusCode;
        }
        //如果存在则删除
        int i = likeViewMapper.deleteByPrimaryKey(likeView.getId());
        if (i == 1) {
            StatusCode statusCode = StatusCode.Success;
            statusCode.setMsg("删除成功");
            return statusCode;
        } else {
            return StatusCode.Fail;
        }

    }

    public LikeView selectByViewId(int viewId,String token){
        LikeView likeView=new LikeView();
        likeView.setUserId(tokenUtil.getUser(token).getId());
        likeView.setViewId(viewId);
        List<LikeView> likeViewList=likeViewMapper.selectByLikeViewSelective(likeView);
        if(likeViewList.size()==1){
            return likeViewList.get(0);
        }else if(likeViewList.size()==0){
            return null;
        }
        log.warn("数据库有重复数据");
        return null;
    }
}
