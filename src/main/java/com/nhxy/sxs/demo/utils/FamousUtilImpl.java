package com.nhxy.sxs.demo.utils;

import com.nhxy.sxs.demo.dto.FamousDTO;
import com.nhxy.sxs.demo.entity.Famous;
import com.nhxy.sxs.demo.mapper.FamousMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FamousUtilImpl {
    @Value("${file.content}")
    private  String file_content;


    public  List getFamousContent(Famous famous){
        List<Famous> famousList = new ArrayList<>();
        famousList.add(famous);
        List<FamousDTO> famousDTOList = new ArrayList<>();
        String str = String.valueOf(famousList.get(0));
        String FileName = StringUtils.StringToArrayGetContent(str, 2, 10);
        String FileContent = FileIOUtils.getFileContent(file_content, FileName);
        FamousDTO famousDTO = new FamousDTO();
        BeanUtils.copyProperties(famousList.get(0), famousDTO);
        famousDTO.setFile_content(FileContent);
        famousDTOList.add(famousDTO);
        return famousDTOList;
    }

}
