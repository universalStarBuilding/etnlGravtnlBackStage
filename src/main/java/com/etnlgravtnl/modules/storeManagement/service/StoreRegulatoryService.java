package com.etnlgravtnl.modules.storeManagement.service;

import com.etnlgravtnl.common.service.CrudService;
import com.etnlgravtnl.modules.storeManagement.entity.SellShops;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface StoreRegulatoryService extends CrudService<SellShops>{
    public int getTotalShopsCount(SellShops sellShops);
    public List<SellShops> getShopsInfoList(SellShops sellShops);

    public int logicallyDeleted(String id);

    public int update(SellShops sellShops);

    public int updateStoreStatus(String id ,boolean closeFlag);

    public List<SellShops> getCertifiedSellerInfo(SellShops sellShops);
    public int getTotalCertifiedSellerCount(SellShops sellShops);

    public int certifiedSeller(String id,String certifiedFlag);




}
