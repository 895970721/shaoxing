package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.dto.ViewDTO;
import com.nhxy.sxs.demo.entity.Picture;
import com.nhxy.sxs.demo.entity.View;
import com.nhxy.sxs.demo.mapper.ViewMapper;
import com.nhxy.sxs.demo.utils.ViewUtilImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ViewServiceImpl implements ViewMapper {

    @Value("${file.viewFileAddress}")
    private String viewAddress;

    @Autowired
    private ViewMapper viewMapper;

    @Autowired
    private PictureServiceImpl pictureService;

    @Autowired
    private ViewUtilImpl viewUtil;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(View record) {
        return 0;
    }

    @Override
    public int insertSelective(View record) {
        return 0;
    }

    @Override
    public View selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<ViewDTO> getViewDTOById(Integer id) {
        View view = viewMapper.selectByPrimaryKey(id);
        List<View> Viewlist = new ArrayList<>();
        Viewlist.add(view);
        List<ViewDTO> ViewDTOList = new ArrayList<ViewDTO>();
        int view_size = Viewlist.size();
        viewUtil.getViewContent(view_size,Viewlist,ViewDTOList);
        return ViewDTOList;
    }

    @Override
    public int updateByPrimaryKeySelective(View record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(View record) {
        return 0;
    }

    @Override
    public List<Picture> selectParentPictureByPrimaryKey(Integer id) {
        return viewMapper.selectParentPictureByPrimaryKey(id);
    }

    @Override
    public List<View> getAllView() {
        return viewMapper.getAllView();
    }

    @Override
    public List<View> getAllParentView() {
        return viewMapper.getAllParentView();
    }

    @Override
    public List<View> getAllSubView(Integer id) {
        return viewMapper.getAllSubView(id);
    }

    @Override
    public List<View> getAllSubViewByParentId(Integer parent_view_id) {
        return viewMapper.getAllSubViewByParentId(parent_view_id);
    }

    @Override
    public List<ViewDTO> getAllSubViewDTOByParentId(Integer parent_view_id){
        List<View> subViewList = viewMapper.getAllSubViewByParentId(parent_view_id);
        List<ViewDTO> subViewDTOList = new ArrayList<ViewDTO>();
        int sub_view_size = subViewList.size();
        /* 获取子景点 */
        viewUtil.getViewContent(sub_view_size,subViewList,subViewDTOList);
        return subViewDTOList;
    }


    @Override
    public List<ViewDTO> getAllParentViewDTO() {
        List<View> parentViewList = viewMapper.getAllParentView();
        List<ViewDTO> parentViewDTOList = new ArrayList<ViewDTO>();
        int parent_view_size = parentViewList.size();
        /* 获取父景点详细信息 */
        viewUtil.getViewContent(parent_view_size,parentViewList,parentViewDTOList);
        return parentViewDTOList;
    }
}
