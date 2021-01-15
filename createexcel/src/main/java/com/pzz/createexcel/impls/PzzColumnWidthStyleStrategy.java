package com.pzz.createexcel.impls;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.style.column.AbstractHeadColumnWidthStyleStrategy;

import java.util.List;

/**
 * @program: easyexcel
 * @description:自定义宽度策略
 * @author: pzz
 * @create: 2021-01-14 09:46
 **/
public class PzzColumnWidthStyleStrategy extends AbstractHeadColumnWidthStyleStrategy {
    private List<Integer> widths;

    public PzzColumnWidthStyleStrategy(List<Integer> widths) {
        this.widths = widths;
    }

    public List<Integer> getWidths() {
        return widths;
    }

    public void setWidths(List<Integer> widths) {
        this.widths = widths;
    }

    @Override
    protected Integer columnWidth(Head head, Integer columnIndex) {
        return this.widths.get(columnIndex);
    }
}
