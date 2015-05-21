package com.swg.core.service;

import com.swg.core.entity.VoteObserverBean;
import com.swg.core.service.support.BasePagedList;

import java.util.List;

public class VoteObserverPagedList extends BasePagedList<VoteObserverBean> {
    private static final long serialVersionUID = -699131429051587523L;

    public VoteObserverPagedList() {
    }

    public VoteObserverPagedList(List<VoteObserverBean> datas, Integer page, Integer size, Long total) {
        super(datas, page, size, total);
    }
}