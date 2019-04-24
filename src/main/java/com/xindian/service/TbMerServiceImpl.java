package com.xindian.service;

import com.xindian.dao.TbMerDao;
import com.xindian.pojo.TbMer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbMerServiceImpl implements TbMerService {

    @Autowired
    private TbMerDao merDao;

    @Override
    public TbMer queryMer(TbMer mer) {
        return merDao.queryMer(mer);
    }

    @Override
    public TbMer queryMerById(int mId) {
        return merDao.queryMerById(mId);
    }

    @Override
    public void updateMer(TbMer mer) {
        merDao.updateMer(mer);
    }
}
