package com.xindian.service;

import com.xindian.pojo.TbTitle;

import java.util.List;

public interface TbTitleService {

    // 通过底部导航栏点击的栏目获取顶部导航栏信息
    List<TbTitle> queryTitlesFromTab(int tFrom);


    // 查询标题信息
    //TbTitleService queryTitle(TbTitleService title);

    // 更改标题
    //void updateTitle(TbTitleService title);
}
