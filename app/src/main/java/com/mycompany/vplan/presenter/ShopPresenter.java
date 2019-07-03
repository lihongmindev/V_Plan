package com.mycompany.vplan.presenter;

import com.mycompany.vplan.model.IShopModel;
import com.mycompany.vplan.model.ShopModel;
import com.mycompany.vplan.view.IShopView;

public class ShopPresenter {

    private IShopView mIShopView;
    private IShopModel mIShopModel;

    public ShopPresenter(IShopView mIShopView){
        this.mIShopView = mIShopView;
        mIShopModel = new ShopModel();
    }
}
