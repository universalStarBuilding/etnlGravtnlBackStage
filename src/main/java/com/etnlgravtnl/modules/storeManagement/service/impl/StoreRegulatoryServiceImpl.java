package com.etnlgravtnl.modules.storeManagement.service.impl;

import com.etnlgravtnl.common.constant.SystemCommonConstants;
import com.etnlgravtnl.common.exception.Constant.WebExceptionType;
import com.etnlgravtnl.common.service.impl.CrudServiceImpl;
import com.etnlgravtnl.modules.storeManagement.dao.StoreRegulatoryDao;
import com.etnlgravtnl.modules.storeManagement.entity.SellShops;
import com.etnlgravtnl.modules.storeManagement.service.StoreRegulatoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/29.
 */
@Service("storeRegulatoryService")
public class StoreRegulatoryServiceImpl extends CrudServiceImpl<StoreRegulatoryDao,SellShops> implements StoreRegulatoryService{
    @Autowired
    private StoreRegulatoryDao storeRegulatoryDao;


    @Override
    public int getTotalShopsCount(SellShops sellShops) {
        return storeRegulatoryDao.getTotalShopsCount( sellShops);
    }

    @Override
    public List<SellShops> getShopsInfoList(SellShops sellShops) {
        return storeRegulatoryDao.getShopsInfoList( sellShops);
    }
    @Transactional
    @Override
    public int logicallyDeleted(String id) {
        return storeRegulatoryDao.logicallyDeleted(Integer.parseInt(id));
    }

    @Transactional
    @Override
    public int updateStoreStatus(String id, boolean closeFlag) {
        String statusCode="";
        Map map=new HashMap();
        map.put("id",id);
        if(closeFlag)
        {
            statusCode= SystemCommonConstants.StoreStatus.CLOSE.toString();
        }else
        {
            statusCode= SystemCommonConstants.StoreStatus.NORMAL.toString();
        }
        map.put("statusCode",statusCode);

        return storeRegulatoryDao.updateStoreStatus(map);
    }

    @Override
    public List<SellShops> getCertifiedSellerInfo(SellShops sellShops) {

        return storeRegulatoryDao.getCertifiedSellerInfo(sellShops);
    }

    @Override
    public int getTotalCertifiedSellerCount(SellShops sellShops) {
        return storeRegulatoryDao.getTotalCertifiedSellerCount(sellShops);
    }

    @Transactional
    @Override
    public int certifiedSeller(String id,String certifiedFlag) {

        Map map=new HashMap();
        map.put("id",id);
        map.put("auth",certifiedFlag);
        return storeRegulatoryDao.certifiedSeller(map);
    }
}
