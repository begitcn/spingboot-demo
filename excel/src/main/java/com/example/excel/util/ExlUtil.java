package com.example.excel.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.excel.annotation.ExcelExport;
import com.example.excel.annotation.ExcelTitle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 自定义excel工具类
 * 主要封装excel导出
 *
 * @author guochao
 */
public class ExlUtil {

    /**
     * xlsx 后缀
     */
    public static final String XLS = ".xls";

    /**
     * 注解标题形式导出Excel数据
     *
     * @param rows     数据
     * @param clazz    注解类
     * @param response
     * @param mulSheet 是否多sheet(暂未实现)
     * @param <T>
     * @throws IOException
     */
    public static <T> void export(List<T> rows, Class<T> clazz, HttpServletResponse response,
                                  Boolean mulSheet) throws IOException {

        ExcelWriter writer = ExcelUtil.getWriter();

        //循环注解里面的值 填入list集合用于后续排序
        Field[] declaredFields = clazz.getDeclaredFields();
        List<ExcelExport> list = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            //获取注解对象
            ExcelExport excelExport = declaredField.getDeclaredAnnotation(ExcelExport.class);
            if (excelExport != null) {
                list.add(excelExport);
            }
        }
        //排序
        List<ExcelExport> sortedList =
                list.stream().sorted(Comparator.comparing(ExcelExport::order)).collect(Collectors.toList());
        //自定义标题别名
        for (ExcelExport excelExport : sortedList) {
            writer.addHeaderAlias(excelExport.baseName(), excelExport.zwName());
        }
        //获取标题名称
        String title = "";
        ExcelTitle excelTitle = clazz.getDeclaredAnnotation(ExcelTitle.class);
        if (excelTitle != null) {
            title = excelTitle.name();
        }

        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(sortedList.size() - 1, title);
        // 只显示别名列
        writer.setOnlyAlias(true);
        if (mulSheet) {

        } else {
            writer.write(rows, true);
        }

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLUtil.encode(title, "UTF-8") + XLS);

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    /**
     * 自定义标题形式导出Excel
     *
     * @param fileName 标题名称
     * @param rows     数据
     * @param clazz    注解类
     * @param response
     * @param mulSheet 是否多sheet(暂未实现)
     * @param <T>
     * @throws IOException
     */
    public static <T> void export(String fileName, List<T> rows, Class<T> clazz, HttpServletResponse response,
                                  Boolean mulSheet) throws IOException {

        ExcelWriter writer = ExcelUtil.getWriter();

        //循环注解里面的值 填入list集合用于后续排序
        Field[] declaredFields = clazz.getDeclaredFields();
        List<ExcelExport> list = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            //获取注解对象
            ExcelExport excelExport = declaredField.getDeclaredAnnotation(ExcelExport.class);
            if (excelExport != null) {
                list.add(excelExport);
            }
        }
        //排序
        List<ExcelExport> sortedList =
                list.stream().sorted(Comparator.comparing(ExcelExport::order)).collect(Collectors.toList());
        //自定义标题别名
        for (ExcelExport excelExport : sortedList) {
            writer.addHeaderAlias(excelExport.baseName(), excelExport.zwName());
        }

        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(sortedList.size() - 1, fileName);
        // 只显示别名列
        writer.setOnlyAlias(true);
        if (mulSheet) {

        } else {
            writer.write(rows, true);
        }

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLUtil.encode(fileName, "UTF-8") + XLS);

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }
}
