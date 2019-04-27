package com.xindian.service;

import com.xindian.pojo.TbMer;

public interface TbMerService {

    TbMer queryMer(TbMer mer);

    TbMer queryMerById(int mId);

    void updateMer(TbMer mer);

    void changeMerPicture(TbMer mer);
}
