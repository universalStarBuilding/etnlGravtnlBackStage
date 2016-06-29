package com.modules.service.account;

import com.etnlgravtnl.modules.model.AccBasic;
import com.etnlgravtnl.modules.service.account.AccBasicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Created by admin on 2016/6/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
@TestExecutionListeners(value = { DependencyInjectionTestExecutionListener.class })
public class AccBasicServiceImplTest {
    private AccBasic accBasic=new AccBasic();

    private static Integer id=1;
    @Autowired
    private AccBasicService accBasicService;

    /**
     * 修改支付密码
     */
    @Test
    public void updatePayPwd(){
        accBasic.setUserId(id);
        accBasic.setPayPwd("456rty");
        accBasicService.updatePayPwd(accBasic);
    }
}
