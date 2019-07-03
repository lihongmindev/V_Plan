package com.mycompany.vplan.model;

import com.mycompany.vplan.bean.CardList;

import java.util.List;

public interface IDeskModel {
    List<CardList> requestSubject(String subject);
}
