package com.marstalk.testng;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.Test;

/**
 * 包含静态类如何Mock<br/>
 * 1，继承：PowerMockTestCase<br/>
 * 2，两个注解在单元测试上<br/>
 * 1)PrepareForTest，放置的是依赖的静态类数组。<br/>
 * 2)PowerMockIgnore，放置了那些东西可以避免<br/>
 */
@PrepareForTest({NgUtils.class})
@PowerMockIgnore({"javax.management.*", "javax.script.*", "org.*"})
public class NgServiceStaticTest extends PowerMockTestCase {
    @Mock
    NgDao ngDao;

    @InjectMocks
    NgServiceWithStatic ngService;

    //    @BeforeMethod
    public void beforeMethod() {

        PowerMockito.mockStatic(NgUtils.class);
        PowerMockito.when(NgUtils.notNull()).thenReturn(false);
        System.out.println(1);
    }

    @Test
    public void testWithException() throws RuntimeException {
        PowerMockito.mockStatic(NgUtils.class);
        PowerMockito.when(NgUtils.notNull()).thenReturn(false);
        System.out.println(1);

        Mockito.when(ngDao.queryByName(Mockito.any(String.class))).thenReturn("shanzhonglaosou");
        ngService.checkUser("shanzhonglaosou");
    }

}
