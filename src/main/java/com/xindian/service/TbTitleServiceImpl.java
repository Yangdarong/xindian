package com.xindian.service;

import com.xindian.dao.TbTitleDao;
import com.xindian.pojo.TbTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbTitleServiceImpl implements TbTitleService {

    @Autowired
    private TbTitleDao titleDao;

    @Override
    public List<TbTitle> queryTitlesFromTab(int tFrom) {
        return titleDao.queryTitlesFromTab(tFrom);
    }
}
