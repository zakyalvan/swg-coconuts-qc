package com.swg.base.backend.service;

import com.swg.base.backend.service.support.BasePagedList;
import com.swg.base.backend.entity.VoteObserverBean;

import java.util.List;

public class VoteObserverPagedList extends BasePagedList<VoteObserverBean> {
    private static final long serialVersionUID = -699131429051587523L;

    public VoteObserverPagedList() {
    }

    public VoteObserverPagedList(List<VoteObserverBean> datas, Integer page, Integer size, Long total) {
        super(datas, page, size, total);
    }
}