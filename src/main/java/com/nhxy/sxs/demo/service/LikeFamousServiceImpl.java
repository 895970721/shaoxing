package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.entity.LikeFamous;
import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.exception.UserLikeException;
import com.nhxy.sxs.demo.mapper.LikeFamousMapper;
import com.nhxy.sxs.demo.utils.UserTokenUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Class: LikeFamousServiceImpl</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/13 15:24
 */
@Service
public class LikeFamousServiceImpl implements LikeFamousMapper {
    @Autowired
    private UserTokenUtilImpl tokenUtil;
    @Autowired
    private LikeFamousMapper likeFamousMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(LikeFamous record) {
        return 0;
    }

    @Override
    public int insertSelective(LikeFamous record) {
        return 0;
    }

    @Override
    public LikeFamous selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(LikeFamous record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(LikeFamous record) {
        return 0;
    }

    @Override
    public List<LikeFamous> selectByUser(User user) {
        return null;
    }

    @Override
    public LikeFamous selectByLikeFamousSelective(LikeFamous likeFamous) {
        return null;
    }

    public List<LikeFamous> all(String token) {
        User user = tokenUtil.getUser(token);
        List<LikeFamous> likeFamousList = likeFamousMapper.selectByUser(user);
        return likeFamousList;
    }

    public void add(int famousId, String token, String famousTitle, String pictureUrl) {
        LikeFamous likeFamous = new LikeFamous();
        likeFamous.setFamousId(famousId);
        likeFamous.setFamousTitile(famousTitle);
        likeFamous.setPictureUrl(pictureUrl);
        likeFamous.setUserId(tokenUtil.getUser(token).getId());

        if (likeFamousMapper.selectByLikeFamousSelective(likeFamous) != null) {//如果数据库已经有这个记录
            StatusCode statusCode = StatusCode.Fail;
            statusCode.setMsg("参数错误");
            throw new UserLikeException(statusCode);
        }
        likeFamousMapper.insertSelective(likeFamous);
    }

    public void delete(int id) {
        try {
            likeFamousMapper.deleteByPrimaryKey(id);
        }
        catch (Exception e){
            StatusCode statusCode=StatusCode.Fail;
            statusCode.setMsg("删除错误");
            throw new UserLikeException(statusCode);
        }
    }

    public LikeFamous selectByFamous(int famousId, String token) {
        User user=tokenUtil.getUser(token);
        LikeFamous likeFamous=new LikeFamous();
        likeFamous.setUserId(user.getId());
        likeFamous.setFamousId(famousId);
        return likeFamousMapper.selectByLikeFamousSelective(likeFamous);
    }
}
