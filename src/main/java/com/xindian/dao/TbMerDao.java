package com.xindian.dao;

import com.xindian.pojo.TbMer;

public interface TbMerDao {
    // 查询
    TbMer queryMer(TbMer mer);
    // 更新
    void updateMer(TbMer mer);
    //
    TbMer queryMerById(int mId);

    void changeMerPicture(TbMer mer);
}
