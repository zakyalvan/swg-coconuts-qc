package com.swg.core.service.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Implementasi default dari {@link PagedList}.
 *
 * @param <T> type object dalam list.
 * @author zakyalvan
 */
public class BasePagedList<T> implements PagedList<T> {
    private static final long serialVersionUID = -5653817242728140373L;

    protected List<T> datas = new ArrayList<T>();

    protected Integer page;
    protected Integer size;

    protected Long total;

    public BasePagedList() {
    }

    public BasePagedList(List<T> datas, Integer page, Integer size, Long total) {
        this.datas = datas;

        this.page = page;
        this.size = size;

        this.total = total;
    }

    @Override
    public Iterator<T> iterator() {
        return datas.iterator();
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Integer getOffset() {
        return (size * page);
    }

    @Override
    public Long getTotal() {
        return total;
    }

    @Override
    public List<T> getDatas() {
        return datas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((datas == null) ? 0 : datas.hashCode());
        result = prime * result + ((page == null) ? 0 : page.hashCode());
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BasePagedList<T> other = (BasePagedList<T>) obj;
        if (datas == null) {
            if (other.datas != null)
                return false;
        } else if (!datas.equals(other.datas))
            return false;
        if (page == null) {
            if (other.page != null)
                return false;
        } else if (!page.equals(other.page))
            return false;
        if (size == null) {
            if (other.size != null)
                return false;
        } else if (!size.equals(other.size))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        return true;
    }
}
