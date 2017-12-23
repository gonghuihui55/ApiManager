package cn.crap.adapter;

import cn.crap.dto.SearchDto;
import cn.crap.dto.SourceDto;
import cn.crap.enumeration.ProjectType;
import cn.crap.model.mybatis.Source;
import cn.crap.service.ICacheService;
import cn.crap.utils.GetTextFromFile;
import cn.crap.utils.MyString;

import java.util.ArrayList;
import java.util.List;


/**
 * Automatic generation by tools
 * model adapter: convert model to dto
 * Avoid exposing sensitive data and modifying data that is not allowed to be modified
 */
public class SourceAdapter {
    public static SourceDto getDto(Source model){
        if (model == null){
            return null;
        }

        SourceDto dto = new SourceDto();
        dto.setId(model.getId());
		dto.setCreateTime(model.getCreateTime());
		dto.setSequence(model.getSequence());
		dto.setStatus(model.getStatus());
		dto.setName(model.getName());
		dto.setUpdateTime(model.getUpdateTime());
		dto.setModuleId(model.getModuleId());
		dto.setRemark(model.getRemark());
		dto.setFilePath(model.getFilePath());
		dto.setProjectId(model.getProjectId());
		
        return dto;
    }

    public static Source getModel(SourceDto dto){
        if (dto == null){
            return null;
        }
        Source model = new Source();
        model.setId(dto.getId());
		model.setCreateTime(dto.getCreateTime());
		model.setSequence(dto.getSequence());
		model.setStatus(dto.getStatus());
		model.setName(dto.getName());
		model.setUpdateTime(dto.getUpdateTime());
		model.setModuleId(dto.getModuleId());
		model.setRemark(dto.getRemark());
		model.setFilePath(dto.getFilePath());
		model.setProjectId(dto.getProjectId());
		
        return model;
    }

    public static List<SourceDto> getDto(List<Source> models){
        if (models == null){
            return new ArrayList<>();
        }
        List<SourceDto> dtos = new ArrayList<>();
        for (Source model : models){
            dtos.add(getDto(model));
        }
        return dtos;
    }

    public static List<SearchDto> getSearchDto(ICacheService cacheService,List<Source> models){
        if (models == null){
            return new ArrayList<>();
        }
        List<SearchDto> dtos = new ArrayList<>();
        for (Source model : models){
            dtos.add(getSearchDto(cacheService, model));
        }
        return dtos;
    }

    public static SearchDto getSearchDto(ICacheService cacheService, Source source){
        SearchDto dto = new SearchDto();
        dto.setId(source.getId());
        dto.setCreateTime(source.getCreateTime());
        dto.setTitle(source.getName());
        dto.setType(Source.class.getSimpleName());
        dto.setUrl("#/"+source.getProjectId()+"/source/detail/"+source.getId());
        dto.setVersion("");
        dto.setProjectId(source.getProjectId());
        //索引内容 = 备注内容 + 文档内容
        String docContent = "";
        try {
            docContent = GetTextFromFile.getText(source.getFilePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dto.setContent(source.getRemark() + docContent);
        //如果备注为空，则提取文档内容前2500 个字
        if( MyString.isEmpty(source.getRemark()) ){
            source.setRemark( docContent.length() > 2500? docContent.substring(0, 2500) +" ... \r\n..." : docContent);
        }
        // 私有项目不能建立索引
        if(cacheService.getProject(source.getProjectId()).getType() == ProjectType.PRIVATE.getType()){
            dto.setNeedCreateIndex(false);
        }
        return dto;
    }
}