package com.actec.mocktest;

import com.actec.mocktest.modules.mock.service.MockService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author zd
 * @Date 2019/8/2 16:36
 */
public class FirstTest extends MocktestApplicationTests {

    @Autowired
    private MockService mockService;

    //@Ignore("not ready yet")
    @Test
    public void testQ1(){
        Assert.assertEquals("操作有误",500,500);
    }

}
