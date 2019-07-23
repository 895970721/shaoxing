package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.dto.SubViewDTO;
import com.nhxy.sxs.demo.dto.ViewDTO;
import com.nhxy.sxs.demo.entity.Picture;
import com.nhxy.sxs.demo.entity.View;
import com.nhxy.sxs.demo.mapper.ViewMapper;
import com.nhxy.sxs.demo.utils.FileIOUtils;
import com.nhxy.sxs.demo.utils.StringUtils;
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
    public int updateByPrimaryKeySelective(View record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(View record) {
        return 0;
    }

    @Override
    public List<Picture> selectParentPictureByPrimaryKey(Integer id){
        return viewMapper.selectParentPictureByPrimaryKey(id);
    }
    @Override
    public List<View> getAllView(){
        return viewMapper.getAllView();
    }

    @Override
    public List<View> getAllParentView(){
        return viewMapper.getAllParentView();
    }

    @Override
    public List<View> getAllSubView(Integer id){
        return viewMapper.getAllSubView(id);
    }

    @Override
    public List<ViewDTO> getALLViewDTO(){
        List<View> parentViewList = viewMapper.getAllParentView();
        List<ViewDTO> viewDTOList = new ArrayList<ViewDTO>();
        int parent_view_size = parentViewList.size();
        /* 获取父景点 */
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
            ViewDTO viewDTO = new ViewDTO();
            viewDTO.setId(parentViewId);
            viewDTO.setTitle(parentViewTitle);
            viewDTO.setFileContent(parentFileContent);
            viewDTO.setPic_url(parentPicturelist);
            viewDTO.setSubViewList(subViewDTOList);
            viewDTOList.add(viewDTO);
        }
        return viewDTOList;
    }
}
