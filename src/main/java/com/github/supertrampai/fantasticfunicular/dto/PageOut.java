package com.github.supertrampai.fantasticfunicular.dto;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageOut<T> implements Serializable {

    private static final long serialVersionUID = -3604145090360116441L;

    private Integer pageNum;
    private Long totalElements;
    private List<T> content;

    public PageOut(PageInfo<T> page) {
        super();
        this.totalElements = page.getTotal();
        this.content = page.getList();
        this.pageNum = page.getPageNum();
    }

    public PageOut(Long totalElements, List<T> content) {
        super();
        this.totalElements = totalElements;
        this.content = content;
    }
}
