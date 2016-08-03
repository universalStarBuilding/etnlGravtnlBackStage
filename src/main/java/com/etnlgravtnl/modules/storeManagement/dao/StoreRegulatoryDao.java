package com.etnlgravtnl.modules.storeManagement.dao;

import com.etnlgravtnl.common.persistence.CrudDao;
import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;
import com.etnlgravtnl.modules.storeManagement.entity.SellShops;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface StoreRegulatoryDao extends CrudDao<SellShops>{
    public int getTotalShopsCount(SellShops sellShops);
    public List<SellShops> getShopsInfoList(SellShops sellShops);
    public int logicallyDeleted(int id);
    public int updateStoreStatus(Map map );
    public List<SellShops> getCertifiedSellerInfo(SellShops sellShops);
    public int getTotalCertifiedSellerCount(SellShops sellShops);

    public int certifiedSeller(Map map);
}