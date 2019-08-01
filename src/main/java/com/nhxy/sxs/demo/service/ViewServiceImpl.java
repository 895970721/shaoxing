package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.dto.ParentViewDTO;
import com.nhxy.sxs.demo.dto.SubViewDTO;
import com.nhxy.sxs.demo.dto.ViewDTO;
import com.nhxy.sxs.demo.entity.Picture;
import com.nhxy.sxs.demo.entity.View;
import com.nhxy.sxs.demo.mapper.ViewMapper;
import com.nhxy.sxs.demo.utils.FileIOUtils;
import com.nhxy.sxs.demo.utils.StringUtils;
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
    public List<Integer> getAllParentId() {
        return viewMapper.getAllParentId();
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
    @Override
    public List<ParentViewDTO> getViewByParentId(Integer id){
        List<Integer> list = viewMapper.getAllParentId();
        if(list.contains(id)){
            View view = viewMapper.selectByPrimaryKey(id);
            List<ParentViewDTO> parentViewDTOList = new ArrayList<ParentViewDTO>();
            List<View> parentViewList = new ArrayList<>();
            parentViewList.add(view);
            List<ViewDTO> ViewDTOList = new ArrayList<ViewDTO>();
            int parent_view_size = parentViewList.size();
            for(int m=0;m<parent_view_size;m++){
                String parentStr = String.valueOf(parentViewList.get(m));
                Integer parentViewId = Integer.valueOf(StringUtils.StringToArrayGetContent(parentStr,0,8));
                List<Picture> parentPicturelist = pictureService.selectPictureByViewId(parentViewId);
                String parentViewTitle = StringUtils.StringToArrayGetContent(parentStr,1,7);
                String parentViewFileName = StringUtils.StringToArrayGetContent(parentStr,2,10);
                String parentFileContent = FileIOUtils.getFileContent(viewAddress,parentViewFileName);
                /* 获取子景点 */
                List<View> subViewList = viewMapper.getAllSubView(parentViewId);
                List<SubViewDTO> subViewDTOList = new ArrayList<>();
                int sub_view_size = subViewList.size();
                if(sub_view_size!=0){
                    for(int n=0;n<sub_view_size;n++){
                        String subStr = String.valueOf(subViewList.get(n));
                        Integer subViewId = Integer.valueOf(StringUtils.StringToArrayGetContent(subStr,0,8));
                        List<Picture> subPicturelist = pictureService.selectPictureByViewId(subViewId);
                        String subViewTitle = StringUtils.StringToArrayGetContent(subStr,1,7);
                        String subViewFileName = StringUtils.StringToArrayGetContent(subStr,2,10);
                        String subFileContent = FileIOUtils.getFileContent(viewAddress,subViewFileName);
                        SubViewDTO subViewDTO = new SubViewDTO();
                        subViewDTO.setId(subViewId);
                        subViewDTO.setPic_url(subPicturelist);
                        subViewDTO.setFileContent(subFileContent);
                        subViewDTO.setTitle(subViewTitle);
                        subViewDTOList.add(subViewDTO);
                    }
                }
                //PO->DTO
                ParentViewDTO parentViewDTO = new ParentViewDTO();
                parentViewDTO.setId(parentViewId);
                parentViewDTO.setTitle(parentViewTitle);
                parentViewDTO.setFileContent(parentFileContent);
                parentViewDTO.setPic_url(parentPicturelist);
                parentViewDTO.setSubViewList(subViewDTOList);
                parentViewDTOList.add(parentViewDTO);
            }
            return parentViewDTOList;
        }else{
            return null;
        }
    }
}
