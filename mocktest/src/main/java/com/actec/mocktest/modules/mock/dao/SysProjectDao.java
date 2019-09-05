package com.actec.mocktest.modules.mock.dao;

import com.actec.mocktest.modules.mock.entity.SysProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author zd
 * @Date 2019/7/25 15:30
 */
@Mapper
public interface SysProjectDao extends BaseMapper<SysProject> {

    List<SysProject> queryProjectList(@Param("start") int start, @Param("pageSize") int pageSize);

    int queryProjectListTotal();

    List<Map<String,Object>> queryCatalogByProjectId(String projectId);

    List<Map<String,Object>> queryTblDetail(@Param("projectId") String projectId, @Param("tblName") String tblName);

    Map<String,Object> queryDataSourceByTblId(@Param("tblId") String tblId);

    List<Map<String,Object>> queryColProIdAndTblName(Map map);

    int queryColProIdAndTblNameTotal(Map map);

}
