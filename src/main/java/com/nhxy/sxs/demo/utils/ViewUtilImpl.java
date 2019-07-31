package com.nhxy.sxs.demo.utils;

import com.nhxy.sxs.demo.dto.ViewDTO;
import com.nhxy.sxs.demo.entity.Picture;
import com.nhxy.sxs.demo.service.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewUtilImpl {

    @Autowired
    private PictureServiceImpl pictureService;

    @Value("${file.viewFileAddress}")
    private String viewAddress;

    public List getViewContent(Integer size, List ViewList, List ViewDTOList){
        for (int m = 0; m < size; m++) {
            String parentStr = String.valueOf(ViewList.get(m));
            Integer parentViewId = Integer.valueOf(StringUtils.StringToArrayGetContent(parentStr, 0, 8));
            List<Picture> parentPicturelist = pictureService.selectPictureByViewId(parentViewId);
            String parentViewTitle = StringUtils.StringToArrayGetContent(parentStr, 1, 7);
            String parentViewFileName = StringUtils.StringToArrayGetContent(parentStr, 2, 10);
            String parentFileContent = FileIOUtils.getFileContent(viewAddress, parentViewFileName);
            ViewDTO parentViewDTO = new ViewDTO();
            parentViewDTO.setId(parentViewId);
            parentViewDTO.setFileContent(parentFileContent);
            parentViewDTO.setPic_url(parentPicturelist);
            parentViewDTO.setTitle(parentViewTitle);
            ViewDTOList.add(parentViewDTO);
        }
        return ViewDTOList;
    }

}
