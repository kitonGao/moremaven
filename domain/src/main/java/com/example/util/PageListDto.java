package com.example.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/4/29/0029 16:18
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */

public class PageListDto<T> implements Serializable {
    private Long total;

    private List<T> data;

    public PageListDto(Long total, List<T> data) {
        this.total = total;
        if (data == null) {
            data = new ArrayList<>();
        }

        this.data = (List)data;
    }


    public PageListDto() {
        this.total = 0L;
        this.setData(new ArrayList<>());
    }

    public PageListDto(List<T> data) {
        if (data == null) {
            this.total = 0L;
            this.setData(new ArrayList<>());
        } else {
            this.setData(data);
            this.total = (long)data.size();
        }
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        if (total == null) {
            this.total = 0L;
        } else {
            this.total = total;
        }
    }

    public void setTotal(Integer total) {
        if (total != null) {
            this.total = total.longValue();
        } else  {
            this.total = 0L;
        }
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


    public void setRows(List<T> data) {
        this.data = data;
    }
}
