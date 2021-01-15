package com.pzz.createexcel.services;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.pzz.createexcel.beans.DeliveryAdvice;
import com.pzz.createexcel.beans.DeliveryAdviceData;
import com.pzz.createexcel.impls.PzzColumnCellStyleStrategy;
import com.pzz.createexcel.impls.PzzColumnWidthStyleStrategy;
import com.pzz.createexcel.impls.PzzRowHeightStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * @program: createexcel
 * @description: 创建出库单
 * @author: pzz
 * @create: 2021-01-14 18:25
 **/
public class DeliveryAdviceFactory {
    private static Properties p;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryAdviceFactory.class);
    private static String path;
    static {
        path = System.getProperty("java.class.path");
//        System.out.println(path);
        int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
        int lastIndex = path.lastIndexOf(File.separator) + 1;
        path = path.substring(firstIndex, lastIndex);
//        System.out.println(path);

        FileInputStream in = null;//以流的形式读入属性文件
//        String fileName = DeliveryAdviceFactory.class.getResource("/").getPath()  + "config.properties";
        String fileName = path + "config.properties";
        try {
            LOGGER.info("加载" + fileName);
            p = new Properties();
            in = new FileInputStream(fileName);
            p.load(in);
            in.close();
        } catch (Exception e) {
            LOGGER.error(fileName + "文件没找到");
        }
    }


    private List<DeliveryAdviceData> getDatas() {
//        String fileName = DeliveryAdviceFactory.class.getResource("/").getPath()  + "data.txt";
        String fileName = path  + "data.txt";
        LOGGER.info("开始读取"+ fileName +"文件");
        List<DeliveryAdviceData> datas = new ArrayList<DeliveryAdviceData>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
            String temp;
            List<String> list;
            while((temp = reader.readLine())!=null){
                list = Arrays.asList(temp.split(","));
                datas.add(new DeliveryAdviceData(list.get(0),list.get(1),list.get(2),list.get(3)));
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("找不到"+ fileName +"文件");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info(fileName + "文件读取完毕" + Arrays.toString(datas.toArray()) + "准备创建" + datas.size() + "个sheet");
        return datas;
    }


    /**
     * 生成sheet
     *
     */
    public void create() {
        //写入数据
        List<DeliveryAdviceData> datas = getDatas();
        String fileName = path + File.separator + "zhouhz" + System.currentTimeMillis() + ".xlsx";
//        String fileName = DeliveryAdviceFactory.class.getResource("/").getPath() + "zhouhz" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter = null;
        //模板结构
        List<DeliveryAdvice> lists = deliveryAdvicedata();
        String temp = lists.get(1).getC1();
        try {
            excelWriter = EasyExcel.write(fileName, DeliveryAdvice.class).build();
            LOGGER.info("创建表单"+fileName);
            LOGGER.info("开始设置表单样式");
            List<WriteTable> writeTables =
                    getWriteTables(lists,getPzzColumnCellStyleStrategies(),getPzzColumnWidthStyleStrategies(),getPzzRowHeightStyleStrategies());

            List<WriteSheet> sheets = new ArrayList<WriteSheet>();
            for (int i = 0; i < datas.size(); i++) {
                LOGGER.info("创建sheet" + (i + 1));
                WriteSheet sheet = EasyExcel.writerSheet("sheet" + (i + 1)).needHead(Boolean.FALSE).build();
                //表单模板里面的数据替换，日期编号，重量
                //日期、编号
                DeliveryAdviceData data = datas.get(i);
                String[] datestr = data.getDate().split("  ");
                LOGGER.info("开始填充sheet" + (i + 1) + "的日期编号:" + data.getDate());
                //第二行行结构
                DeliveryAdvice deliveryAdvice = lists.get(1);
                //因为这里是替换，第一次替换之后xxx就没了，所以在方法前面留一个temp，这里给它放回去
                deliveryAdvice.setC1(temp);
                String c1 = deliveryAdvice.getC1().replaceAll("xxxx-xx-xx",datestr[0]).replaceAll("xx:xx:xx",datestr[1]).replaceAll("xxxxxxxxxxxxx", data.getNo());
                deliveryAdvice.setC1(c1);
                //毛重
                lists.get(2).setC4(data.getGrossweight());
                //皮重
                lists.get(3).setC4(data.getTareweight());
                //净重
                lists.get(4).setC4(Double.parseDouble(data.getGrossweight()) - Double.parseDouble(data.getTareweight()) + "");
                LOGGER.info("开始填充sheet" + (i + 1) + "的重量:"+ data.getGrossweight() + " "  + data.getTareweight());
                //table写入
                for (int j = 0; j < lists.size(); j++) {
//                    LOGGER.info("开始写sheet" + (i + 1) + "的第" + (j + 1) + "行数据");
                    excelWriter.write(lists.subList(j,j+1), sheet, writeTables.get(j));
                }
                LOGGER.info("sheet" + (i + 1) + "写入完毕");
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
            LOGGER.info("出库单生成完毕");
        }
    }

    private List<WriteTable> getWriteTables(List<DeliveryAdvice> lists,
                                            List<PzzColumnCellStyleStrategy> cellStyleStrategies,
                                            List<PzzColumnWidthStyleStrategy> columnWidthStyleStrategies,
                                            List<PzzRowHeightStyleStrategy> rowHeightStyleStrategies) {
        List<WriteTable> writeTables = new ArrayList<WriteTable>();
        for (int i = 0; i < lists.size(); i++) {

            DeliveryAdvice rows = lists.get(i);
            if (rows.getC2() == null){
                LOGGER.info("设置第" + (i + 1) + "行合并单元格：1 2 3 4");
                writeTables.add(
                        EasyExcel.writerTable(i)
                                .needHead(Boolean.FALSE)
                                .registerWriteHandler(new LoopMergeStrategy(1, 4,0))
                                .registerWriteHandler(cellStyleStrategies.get(i))
                                .registerWriteHandler(columnWidthStyleStrategies.get(i))
                                .registerWriteHandler(rowHeightStyleStrategies.get(i))
                                .build());
            }else {
                writeTables.add(
                        EasyExcel.writerTable(i)
                                .needHead(Boolean.FALSE)
                                .registerWriteHandler(cellStyleStrategies.get(i))
                                .registerWriteHandler(columnWidthStyleStrategies.get(i))
                                .registerWriteHandler(rowHeightStyleStrategies.get(i))
                                .build());
            }
        }
        return writeTables;
    }

    /*
     * 获取所有行高
     * */
    private List<PzzRowHeightStyleStrategy> getPzzRowHeightStyleStrategies() {
        List<PzzRowHeightStyleStrategy> rowHeightStyleStrategies = new ArrayList<PzzRowHeightStyleStrategy>();
        rowHeightStyleStrategies.add(r0CellHeightStrategy());
        rowHeightStyleStrategies.add(r1CellHeightStrategy());
        LOGGER.info("设置第3-8行高度:" + p.getProperty("rd","30"));
        for (int i = 2; i < 8; i++) {
            rowHeightStyleStrategies.add(contentCellHeightStrategy());
        }
        rowHeightStyleStrategies.add(r8CellHeightStrategy());
        return rowHeightStyleStrategies;
    }
    /*
     * 第0行高度策略
     * */
    private PzzRowHeightStyleStrategy r0CellHeightStrategy(){
        LOGGER.info("设置第1行高度:" + p.getProperty("r1","30"));
        return new PzzRowHeightStyleStrategy(p.getProperty("r1","30"));
    }
    /*
     * 第1行高度策略
     * */
    private PzzRowHeightStyleStrategy r1CellHeightStrategy(){
        LOGGER.info("设置第2行高度:" + p.getProperty("r2","30"));
        return new PzzRowHeightStyleStrategy(p.getProperty("r2","30"));
    }
    /*
     * 第2到7行高策略
     * */
    private PzzRowHeightStyleStrategy contentCellHeightStrategy(){
        return new PzzRowHeightStyleStrategy(p.getProperty("rd","30"));
    }
    /*
     * 第8行高度策略
     * */
    private PzzRowHeightStyleStrategy r8CellHeightStrategy(){
        LOGGER.info("设置第9行高度:" + p.getProperty("r2","30"));
        return new PzzRowHeightStyleStrategy(p.getProperty("r8","30"));
    }

    /*
     * 获取所有行宽
     * */
    private List<PzzColumnWidthStyleStrategy> getPzzColumnWidthStyleStrategies() {

        List<PzzColumnWidthStyleStrategy> columnWidthStyleStrategies = new ArrayList<PzzColumnWidthStyleStrategy>();
//        columnWidthStyleStrategies.add(c0CellWidthStrategy());
        LOGGER.info("设置各列宽度" +
                p.getProperty("c1","40") + " " +
                p.getProperty("c2","20") + " " +
                p.getProperty("c3","40") + " " +
                p.getProperty("c4","20")
        );
        for (int i = 0; i < 9; i++) {
            columnWidthStyleStrategies.add(contentCellWidthStrategy());
        }
        return columnWidthStyleStrategies;
    }
    /*
     * 所有列宽统一设置
     * */
    private PzzColumnWidthStyleStrategy contentCellWidthStrategy(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(Integer.parseInt(p.getProperty("c1","40")));
        list.add(Integer.parseInt(p.getProperty("c2","20")));
        list.add(Integer.parseInt(p.getProperty("c3","40")));
        list.add(Integer.parseInt(p.getProperty("c4","20")));
        return new PzzColumnWidthStyleStrategy(list);
    }

    /*
     * 获取所有行单元格样式
     * */
    private List<PzzColumnCellStyleStrategy> getPzzColumnCellStyleStrategies() {
        List<PzzColumnCellStyleStrategy> cellStyleStrategies = new ArrayList<PzzColumnCellStyleStrategy>();
        cellStyleStrategies.add(c0CellStrategy());
        cellStyleStrategies.add(c1CellStrategy());
        LOGGER.info("第3-8行单元格样式：宋体 15 Bold 带上下左右边框 HorizontalAlignment.CENTER VerticalAlignment.CENTER");
        for (int i = 2; i < 8; i++) {
            cellStyleStrategies.add(contentCellStrategy());
        }
        cellStyleStrategies.add(c8CellStrategy());
        return cellStyleStrategies;
    }

    /*
     * 第2到7行表单内容样式策略
     * */
    //
    private PzzColumnCellStyleStrategy contentCellStrategy() {
        List<WriteCellStyle> list = new ArrayList<WriteCellStyle>();
        // 表格样式c2-c8
        WriteCellStyle columnStyle1 = new WriteCellStyle();
        //字体
        WriteFont font1 = new WriteFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 15);
        font1.setBold(true);
        columnStyle1.setWriteFont(font1);
        //居中
        columnStyle1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        columnStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        //内容全部显示
        columnStyle1.setWrapped(true);
        //边框
        columnStyle1.setBorderBottom(BorderStyle.MEDIUM);
        columnStyle1.setBorderLeft(BorderStyle.MEDIUM);
        columnStyle1.setBorderRight(BorderStyle.MEDIUM);
        columnStyle1.setBorderTop(BorderStyle.MEDIUM);
        list.add(columnStyle1);

        return new PzzColumnCellStyleStrategy(null, list);
    }
    /*
     * 第0列样式策略
     * */
    private PzzColumnCellStyleStrategy c0CellStrategy() {
        LOGGER.info("第1行单元格样式：Times New Roman 30 Bold 不带边框 HorizontalAlignment.CENTER erticalAlignment.CENTER");
        // 表格样式c0
        WriteCellStyle columnStyle1 = new WriteCellStyle();
        //字体
        WriteFont font1 = new WriteFont();
        font1.setFontName("Times New Roman");
        font1.setFontHeightInPoints((short) 30);
        font1.setBold(true);
        columnStyle1.setWriteFont(font1);
        //居中
        columnStyle1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        columnStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        //内容全部显示
        columnStyle1.setWrapped(true);
        return new PzzColumnCellStyleStrategy(null, columnStyle1);
    }
    /*
     * 第1列样式策略
     * */
    private PzzColumnCellStyleStrategy c1CellStrategy() {
        LOGGER.info("第2行单元格样式：宋体 15 Bold 不带边框 HorizontalAlignment.LEFT VerticalAlignment.TOP");
        // 表格样式c0
        WriteCellStyle columnStyle1 = new WriteCellStyle();
        //字体
        WriteFont font1 = new WriteFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 15);
        font1.setBold(true);
        columnStyle1.setWriteFont(font1);
        //居中
        columnStyle1.setHorizontalAlignment(HorizontalAlignment.LEFT);
        columnStyle1.setVerticalAlignment(VerticalAlignment.TOP);
        //内容全部显示
        columnStyle1.setWrapped(true);
        //边框
        return new PzzColumnCellStyleStrategy(null, columnStyle1);
    }
    /*
     * 第8列样式策略
     * */
    private PzzColumnCellStyleStrategy c8CellStrategy() {
        LOGGER.info("第9行单元格样式：宋体 15 Bold 不带边框 HorizontalAlignment.LEFT VerticalAlignment.TOP");
        // 表格样式c0
        WriteCellStyle columnStyle1 = new WriteCellStyle();
        //字体
        WriteFont font1 = new WriteFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 15);
        font1.setBold(true);
        columnStyle1.setWriteFont(font1);
        //居中
        columnStyle1.setHorizontalAlignment(HorizontalAlignment.LEFT);
        columnStyle1.setVerticalAlignment(VerticalAlignment.BOTTOM);
        //内容全部显示
        columnStyle1.setWrapped(true);
        //边框
        return new PzzColumnCellStyleStrategy(null, columnStyle1);
    }


    private List<DeliveryAdvice> deliveryAdvicedata(){
        List<DeliveryAdvice> data = new ArrayList<DeliveryAdvice>();
        List<List<String>> filedata = Filedata();
        for (int i = 0; i < filedata.size(); i++) {
            DeliveryAdvice d = new DeliveryAdvice();
            if (filedata.get(i).size()>1){
                d.setC1(filedata.get(i).get(0));
                d.setC2(filedata.get(i).get(1));
                d.setC3(filedata.get(i).get(2));
                d.setC4(filedata.get(i).get(3));
            }else {
                d.setC1(filedata.get(i).get(0));
            }
            data.add(d);
        }
        return data;
    }

    private List<List<String>> Filedata() {
        String fileName = path + "template.txt";
        List<List<String>> tables= new ArrayList<List<String>>();
        BufferedReader reader = null;
        try {
            LOGGER.info("开始加载" + path + "template.txt文件");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
            String temp;
            String[] rows;
            while((temp = reader.readLine())!=null){
                LOGGER.info(temp);
                rows = temp.split(",");
                tables.add(Arrays.asList(rows));
            }
        } catch (Exception e) {
            LOGGER.info("找不到template.txt文件，请检查文件名是否有误");
        }  finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tables;
    }
    public boolean verify() {
        if (!"zhouhz@18174031313".equals(p.getProperty("user",""))){
            LOGGER.error("user不匹配");
            return false;
        }
        LOGGER.info("user匹配成功");
        return true;
    }
}
