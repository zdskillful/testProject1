package com.actec.mocktest.modules.admin.controller;

import com.actec.mocktest.modules.mock.service.MockService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zd
 * @Date 2019/7/25 15:26
 *
 *  @Api： 用在请求的类上，表示对类的说明
 *  tags="说明该类的作用，可以在UI界面上看到的注解"
 *  value="该参数没什么意义，在UI界面上也看到，所以不需要配置"
 */
@Api(value="bbb")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private MockService mockService;

    @ApiOperation(value = "查询元数据")
    @PostMapping("/104/mtquery/query")
    public JSONObject mtquery(@RequestBody JSONObject paramJson){
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
        jsonObject = mockService.mtQuery(page,pageSize);
        jsonObject.put("status",200);
        jsonObject.put("message","成功");
        return jsonObject;
    }

    @PostMapping("/104/query/manager/table/detail")
    public JSONObject tableDetail(@RequestBody JSONObject paramJson){
        JSONObject jsonObject = mockService.queryTblDetail(paramJson.getString("projectId"),paramJson.getString("tblName"));
        jsonObject.put("status",200);
        jsonObject.put("message","成功");
        return jsonObject;
    }


    @PostMapping("/104/query/catalog/tableColumn/list/full")
    public JSONObject tableColumn(@RequestBody JSONObject paramJson){
        JSONObject jsonObject = mockService.queryCol(paramJson);
        jsonObject.put("status",200);
        jsonObject.put("message","成功");
        return jsonObject;
    }



}
