package com.pzz.createexcel.impls;

import com.alibaba.excel.write.style.row.AbstractRowHeightStyleStrategy;
import org.apache.poi.ss.usermodel.Row;

/**
 * @program: easyexcel
 * @description: 我的行高
 * @author: pzz
 * @create: 2021-01-14 11:26
 **/
public class PzzRowHeightStyleStrategy extends AbstractRowHeightStyleStrategy {
    private Short headRowHeight;
    private Short contentRowHeight;

    public PzzRowHeightStyleStrategy(String contentRowHeight) {
        this.contentRowHeight = Short.parseShort(contentRowHeight);
    }

    @Override
    protected void setHeadColumnHeight(Row row, int relativeRowIndex) {

    }

    @Override
    protected void setContentColumnHeight(Row row, int relativeRowIndex) {
        if (contentRowHeight != null) {
            row.setHeightInPoints(contentRowHeight);
        }
    }
}
