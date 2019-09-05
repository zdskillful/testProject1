package com.actec.mocktest.modules.mock.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author zd
 * @Date 2019/7/25 15:29
 */
public interface MockService {

    JSONObject mtQuery(int page, int pageSize);

    JSONObject queryTblDetail(String projectId, String tblName);

    JSONObject queryCol(JSONObject paramJson);

    int testQ1();

}
