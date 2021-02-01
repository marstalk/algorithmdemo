package com.marstalk.testng;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * 注意这里不要使用junit的相关注解。
 */
public class NgServiceTest {
    //1，依赖的组件，使用@Mock注解。
    @Mock
    NgDao ngDao;

    //2，被测试的组件，使用InjectMocks，配合InitMocks，使得依赖的组件可以使用mock
    @InjectMocks
    NgService ngService = new NgService();

    @BeforeMethod
    public void beforeMethod() {
        //3，使用MockitoAnnotations.initMocks初始化mock组件。
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void hi() {
        //4，每个测试单元，书写自己的mock逻辑：希望依赖组件返回那些信息。
        Mockito.when(ngDao.hi(Mockito.eq("shanzhonglaosou"))).thenReturn("hi from mock");

        String hi = ngService.hi("shanzhonglaosou");
        //使用Assert来判断输出是否是期望值。
        Assert.assertEquals("hi from mock", hi);
    }

    @Test
    public void testFindUser() {
        //5，使用any
        Mockito.when(ngDao.queryByName(Mockito.any(String.class))).thenReturn("mockUser");

        String user = ngService.findUser("shanzhonglaosou");
        Assert.assertNotNull(user);
        Assert.assertEquals("mockUser", user);
    }
}
