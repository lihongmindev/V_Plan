package com.mycompany.vplan.presenter;

import com.mycompany.vplan.bean.CardList;
import com.mycompany.vplan.model.DeskModel;
import com.mycompany.vplan.model.IDeskModel;
import com.mycompany.vplan.view.IDeskView;


import java.util.List;

public class DeskPresenter {
    private IDeskView mIDeskView;
    private IDeskModel mIDeskModel;
    private List<CardList> cardLists;

    public DeskPresenter(IDeskView mIDeskView){
        this.mIDeskView = mIDeskView;
        mIDeskModel = new DeskModel();
    }

    public void requestDeskCard(String subject){
        cardLists = mIDeskModel.requestSubject(subject);
        mIDeskView.showDeskCard(cardLists);
    }

}
