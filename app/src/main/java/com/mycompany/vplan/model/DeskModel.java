package com.mycompany.vplan.model;

import android.util.Log;

import com.mycompany.vplan.bean.CardList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeskModel  implements IDeskModel{

    public static int EASYLEVEL = 0;
    public static int NORMALLEVEL = 1;
    public static int HIGHLEVEL =2;
    private CardList[] cards = new CardList[]{new CardList(EASYLEVEL, "化", true, "有机化学", false),
                                          new CardList(HIGHLEVEL, "数", false, "2019年全国高考试卷", true),
                                          new CardList(NORMALLEVEL, "语", true, "诗词大全", false),
                                          new CardList(EASYLEVEL, "历", false, "近代史", true)};
    private List<CardList> cardLists= new ArrayList<>();

    @Override
    public List<CardList> requestSubject(String subject) {

        initCards(subject);
        return cardLists;
    }
    private void initCards(String subject){
        cardLists.clear();

        for (int i=0; i<50;i++){
            Random random = new Random();
            int index = random.nextInt(cards.length);
            Log.d("subject选中的科目",(subject.substring(0,1)));
            Log.d("subject随机的科目",cards[index].getSubject());
            if ((subject.substring(0,1)).equals(cards[index].getSubject()) || "全部科目".equals(subject)){
                cardLists.add(cards[index]);
            }

        }
    }
}
