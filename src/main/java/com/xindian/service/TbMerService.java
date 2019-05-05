package com.xindian.service;

import com.xindian.pojo.TbFood;
import com.xindian.pojo.TbMer;

import java.util.List;

public interface TbMerService {

    TbMer queryMer(TbMer mer);

    TbMer queryMerById(int mId);

    void updateMer(TbMer mer);

    void changeMerPicture(TbMer mer);

    List<TbFood> queryFoodsByMid(int mId);
}
