package com.actec.mocktest.modules.mock.service.impl;

import com.actec.mocktest.modules.mock.dao.SysProjectDao;
import com.actec.mocktest.modules.mock.entity.SysProject;
import com.actec.mocktest.modules.mock.service.MockService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zd
 * @Date 2019/7/25 16:01
 */
@Service("mockService")
public class MockServiceImpl implements MockService{

    @Autowired
    private SysProjectDao sysProjectDao;

    @Override
    public JSONObject mtQuery(int page,int pageSize) {
        JSONObject jsonObject = new JSONObject();
        int start = (page-1)*pageSize;
        List<SysProject> sysProjectList = sysProjectDao.queryProjectList(start,pageSize);
        //List<String> projectIdList = new ArrayList<>();
        for (SysProject sysProject : sysProjectList) {
            List<Map<String,Object>> cataLogList = sysProjectDao.queryCatalogByProjectId(sysProject.getProjectId().toString());
            sysProject.setCatalogName(cataLogList);
        }

        jsonObject.put("result",sysProjectList);

        int total = sysProjectDao.queryProjectListTotal();
        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("page",page);
        pageMap.put("pageSize",pageSize);
        pageMap.put("totalRecord",total);
        if (total%pageSize>0){
            pageMap.put("totalPage",(total/pageSize)+1);
        }else {
            pageMap.put("totalPage",total/pageSize);
        }
        jsonObject.put("pagination",pageMap);
        return jsonObject;
    }

    @Override
    public JSONObject queryTblDetail(String projectId, String tblName) {
        JSONObject jsonObject  = new JSONObject();
        Map<String,Object> resultMap = new HashMap<>();
        List<Map<String,Object>> tblMap =sysProjectDao.queryTblDetail(projectId,tblName);
        List<Map<String,Object>> resultList = new ArrayList<>();
        if (tblMap != null) {
            for (Map<String, Object> map : tblMap) {
                Map<String,Object> dataMap = new HashMap<>();
                dataMap = sysProjectDao.queryDataSourceByTblId(map.get("tblId").toString());
                resultMap.put("datasource",dataMap);
                resultMap.put("table",map);
                resultMap.put("bizCatalogs",new ArrayList<>());
                resultList.add(resultMap);
            }
        }
        jsonObject.put("result",resultList);
        return jsonObject;
    }

    @Override
    public JSONObject queryCol(JSONObject paramJson) {
        JSONObject jsonObject = new JSONObject();

        JSONObject pageJson = paramJson.getJSONObject("pageParam");
        int page = 1;
        int pageSize = 10;
        if (StringUtils.isNotBlank(pageJson.getString("page"))){
            page = pageJson.getInteger("page");
        }

        if (StringUtils.isNotBlank(pageJson.getString("pageSize"))){
            pageSize = pageJson.getInteger("pageSize");
        }
        int start = (page-1)*pageSize;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("start",start);
        paramMap.put("pageSize",pageSize);
        paramMap.put("projectId",paramJson.get("projectId"));
        List<Map<String,Object>> colList = sysProjectDao.queryColProIdAndTblName(paramMap);

        List<Map<String,Object>> resultList = new ArrayList<>();
        for (Map<String, Object> map : colList) {
            map.put("params",new ArrayList<>());
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("column",map);
            resultList.add(resultMap);
        }

        jsonObject.put("result",resultList);

        int total = sysProjectDao.queryColProIdAndTblNameTotal(paramMap);
        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("page",page);
        pageMap.put("pageSize",pageSize);
        pageMap.put("totalRecord",total);
        if (total%pageSize>0){
            pageMap.put("totalPage",(total/pageSize)+1);
        }else {
            pageMap.put("totalPage",total/pageSize);
        }
        jsonObject.put("pagination",pageMap);
        return jsonObject;
    }

    @Override
    public int testQ1() {
        return 500;
    }
}
