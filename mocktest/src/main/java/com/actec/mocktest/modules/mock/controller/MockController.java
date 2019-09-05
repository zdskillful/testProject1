package com.actec.mocktest.modules.mock.controller;

import com.actec.mocktest.modules.mock.service.MockService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zd
 * @Date 2019/7/25 15:26
 *
 *  @Api： 用在请求的类上，表示对类的说明
 *  tags="说明该类的作用，可以在UI界面上看到的注解"
 *  value="该参数没什么意义，在UI界面上也看到，所以不需要配置"
 */
@Api(value="aaaa", tags="hhhhhh")
@RestController
public class MockController {

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

    @GetMapping("/test1")
    public JSONObject test1(){
        JSONObject jsonObject = new JSONObject();
        //匹配指定范围内的数字
        String regEx = "[^0-9]";
        //Pattern是一个正则表达式经编译后的表现模式
        Pattern p = Pattern.compile(regEx);
        // 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
        Matcher m = p.matcher("申请成功，待审批。对应申请号为： 439");
        //将输入的字符串中非数字部分用空格取代并存入一个字符串
        String string = m.replaceAll(" ").trim();
        jsonObject.put("status",200);
        jsonObject.put("message","成功");
        return jsonObject;
    }

}
