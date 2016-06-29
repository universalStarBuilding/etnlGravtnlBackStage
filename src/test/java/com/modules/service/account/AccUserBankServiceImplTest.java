package com.modules.service.account;

import com.etnlgravtnl.modules.model.AccUserBank;
import com.etnlgravtnl.modules.service.account.AccUserBankService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Date;

/**
 * Created by admin on 2016/6/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
@TestExecutionListeners(value = { DependencyInjectionTestExecutionListener.class })
public class AccUserBankServiceImplTest {

    private AccUserBank accUserBank=new AccUserBank();

    @Autowired
    private AccUserBankService accUserBankService;

    private static Integer id=1;

    @Test
    public void insertUserBank(){
        accUserBank.setUserId(id);
        accUserBank.setName("吴栋林");
        accUserBank.setBankNo("95533");
        accUserBank.setBankAccount("6222804336010003614");
        accUserBank.setOwnerName("bbb");
        accUserBank.setOpenAcctBank("甘肃省酒泉市");
        accUserBank.setBankType("信用卡");
        accUserBank.setBankCityId(1);
        accUserBank.setBankProvinceId(1);
        accUserBank.setCreateTime(new Date());
        accUserBank.setState(null);
        accUserBankService.insert(accUserBank);
        Assert.assertTrue("Failed to find user " + accUserBank, accUserBank != null);
    }
}
