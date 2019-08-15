package com.yifei.sendmessage.bean;

import java.util.ArrayList;
import java.util.List;

public class FestivalLab {
    private List<Festival> mFestival = new ArrayList<>();
    private List<Msg> msgs = new ArrayList<>();

    public static FestivalLab instance;

    private FestivalLab() {
        mFestival.add(new Festival(1, "国庆节"));
        mFestival.add(new Festival(2, "父亲节"));
        mFestival.add(new Festival(3, "母亲节"));
        mFestival.add(new Festival(4, "妇女节"));
        mFestival.add(new Festival(5, "儿童节"));
        mFestival.add(new Festival(6, "青年节"));


        msgs.add(new Msg(1, 2, "云淡风也轻，秋叶飘满天，金秋收获季，共庆国" +
                "庆节；祝您国庆佳节天天好心情，事事都如意！"));
        msgs.add(new Msg(2, 2, "朋字双月并肩行,远隔千里两地明;祝友健康阂家" +
                "乐,事业顺利展宏程;国庆佳节同喜日,捧杯聚首秋月中."));
        msgs.add(new Msg(3, 1, "过几天就是中秋了，不知道现在发祝福短信给" +
                "你是否有点早，不过我想通了，提前的祝福和迟到的祝福都没有关系，因为我对你祝福的心是永远都不会" +
                "改变的。中秋快乐！"));
        msgs.add(new Msg(4, 2, "送你一个月饼,含量成分:100%纯关心；" +
                "配料:甜蜜+快乐+开心+宽容+忠诚=幸福；保质期:一辈子；保存方法:珍惜."));
        msgs.add(new Msg(5, 2, "新年新气象，百事可乐，万事七喜，心情雪碧，" +
                "学习芬达，工作红牛，生活茹梦，爱情鲜橙多，天天娃哈哈，月月乐百事"));
        msgs.add(new Msg(6, 1, "我这份祝福跨过重重高山，掠过条条小溪，跳过马路，" +
                "窜出胡同，闪过卖冰糖葫芦的老太太，钻进你耳朵里－祝新年快乐！"));

        msgs.add(new Msg(7, 1, "心连心，接受春的赏赐。愿你快快乐乐地迎新年。" +
                "我们不常拥有新年，却常拥有新的一天。愿你每一天，都充满幸福和喜悦。"));
        msgs.add(new Msg(8, 1, "端起轻松的酒杯，与美丽举杯；端起如意的酒杯，" +
                "与成功交杯；端起惬意的酒杯，与健康碰杯；端起幸福的酒杯，与快乐干杯。端午节到了，愿你端起人生" +
                "美满的酒杯，快乐相随。"));

    }
    public List<Msg> getMsgsByFestivalId(int festId){
        List<Msg> newMsgs = new ArrayList<>();
        for (Msg msg:msgs) {
            if(msg.getFestivalId()==festId){
                newMsgs.add(msg);
            }
        }
        return newMsgs;
    }

    public Msg getMsgById(int id){
        for (Msg msg:msgs) {
            if(msg.getId()==id){
               return msg;
            }
        }
        return null;
    }



    public List<Festival> getFestivals() {
        return new ArrayList<>(mFestival);
    }

    public Festival getFestivalById(int id) {
        for (int i = 0; i < mFestival.size(); i++) {
            if (mFestival.get(i).getId() == id) {
                return mFestival.get(i);
            }
        }
        return null;
    }

    public static FestivalLab getInstance() {
        if (instance == null) {
            synchronized (FestivalLab.class) {
                if (instance == null) {
                    instance = new FestivalLab();
                }
            }
        }
        return instance;
    }
}
