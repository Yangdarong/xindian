package com.xindian.dao;

import com.xindian.pojo.TbTitle;

import java.util.List;

public interface TbTitleDao {


    List<TbTitle> queryTitlesFromTab(int tFrom);
}
