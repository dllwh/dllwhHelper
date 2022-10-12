package org.dllwh.utils.apache.poi.excel;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dllwh.utils.apache.poi.excel.annotation.*;
import org.slf4j.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import static org.dllwh.utils.apache.poi.excel.util.ExcelHelper.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: 导出Excel文件
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-03 21:22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public final class ExportExcel {
    private final Logger logger = LoggerFactory.getLogger(ExportExcel.class);
    /**
     * Microsoft Excel 2003 文件
     */
    private final static String HSSF_FILE_EXTENSION = "xls";
    /**
     * Microsoft Excel 2007 文件
     */
    private final static String XSSF_FILE_EXTENSION = "xlsx";

    /**
     * 工作薄对象
     */
    private final Workbook workbook;
    /**
     * 工作表对象
     */
    private Sheet sheet;
    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 当前行号
     */
    private int rowNum;
    /**
     * 是否添加序号
     */
    boolean isAddSortNum = false;
    String serialName = "";
    private int dataStartNum;

    /**
     * 标题行号
     */
    private int headerNum;
    /**
     * 注解@ExcelField、@Excel相关列表
     */
    private List<Object[]> annotationList;

    /**
     * 支持无模板导出
     */
    public ExportExcel() {
        this.workbook = new XSSFWorkbook();
        this.styles = createStyles(workbook);
    }


    /**
     * 支持模板导出
     *
     * @param file 模板文件
     */
    public ExportExcel(File file) {
        String fileName = file.getName();
        if (StringUtils.isBlank(fileName)) {
            throw new RuntimeException("导出模板文档为空!");
        } else if (HSSF_FILE_EXTENSION.toLowerCase().endsWith(fileName)) {
            this.workbook = new HSSFWorkbook();
        } else if (XSSF_FILE_EXTENSION.toLowerCase().endsWith(fileName)) {
            this.workbook = new XSSFWorkbook();
        } else {
            throw new RuntimeException("文档格式不正确!");
        }
        logger.debug("Open the export-template successfully.");
    }

    /**
     * 添加sheet页(无模板时使用)
     *
     * @param title 唯一指定的sheet名称，如果名字是null或者已经被占用抛出异常
     * @param cls   实体类，通过annotation.ExportField获取设置
     */
    public void addSheet(String title, Class<?> cls) {
        addSheet(title, cls, false);
    }

    /**
     * 添加sheet页(无模板时使用)
     *
     * @param title      唯一指定的sheet名称，如果名字是null或者已经被占用抛出异常
     * @param cls        实体类，通过 @ExportField 获取设置
     * @param isJustData 是否只导出数据，(若设置为false，导出的excel带有下拉框；若设置为true，只导出数据)
     */
    public void addSheet(String title, Class<?> cls, Boolean isJustData) {
        if (StringUtils.isBlank(title) || workbook.getSheet(title) != null) {
            throw new RuntimeException("【" + title + "】为null或者已经被占用，请更换sheet页名称！");
        }

        // 初始化对应的数据类型
        initAnnotationList(cls);
        rowNum = 0;
        List<String> headerList = new ArrayList<>();

        boolean hasAnnotation = cls.isAnnotationPresent(ExcelProperty.class);
        if (hasAnnotation) {
            ExcelProperty excelProperty = cls.getAnnotation(ExcelProperty.class);
            this.isAddSortNum = excelProperty.isSerialNo();
            this.serialName = excelProperty.serialName();
        }

        for (Object[] os : annotationList) {
            ExcelField excelField = (ExcelField) os[0];
            String header = excelField.title();
            headerList.add(header);
        }

        //添加序号列
        if (isAddSortNum) {
            List<String> headerListTemp = new ArrayList<>();
            headerListTemp.add(serialName);
            headerListTemp.addAll(headerList);
            headerList = headerListTemp;
        }

        createTableHeader(title, headerList);
        dataStartNum = 1;
        if (!isJustData) {
            setDropdownBox();
        }
    }

    /**
     * 添加数据（通过annotation.ExportField添加数据）
     *
     * @param list 要导出的数据集
     */
    public <E> void setDataList(List<E> list) {
        setDataList(rowNum, list);
    }


    /**
     * 添加数据（通过annotation.ExportField添加数据）
     *
     * @param startRowNum 设置Excel文件中导出数据开始的行号
     * @param list        要导出的数据集
     */
    public <E> void setDataList(int startRowNum, List<E> list) {
        dataStartNum = startRowNum;
        rowNum = startRowNum;
        int num = 0;
        int n = 0;

        for (E e : list) {
            Row row = getRow(sheet, rowNum++);
            Object val = null;

            for (Object[] os : annotationList) {
                ExcelField excelField = (ExcelField) os[0];
                int column = excelField.orderNum();
                boolean isEnumClass = StringUtils.isNotBlank(excelField.enumClass());
                if (isAddSortNum) {
                    column = column + 1;
                }

                if ("list".equals(os[2])) {
                    try {
                        Collection valueList = (Collection) PropertyUtils.getProperty(e, (String) os[3]);
                        int i = 0;
                        for (Object o : valueList) {
                            Row row2 = getRow(sheet, row.getRowNum() + i);
                            val = getValueFromBean(o, (String) os[1], excelField);
                            addCell(row2, column, val, excelField.align(), excelField.isFormula(), excelField.isWrap(), isEnumClass);
                            i++;
                        }
                        n = Math.max(n, valueList.size());

                        //若list值为空，设置空格式
                        if (i == 0) {
                            addCell(row, column, "", excelField.align(), excelField.isFormula(), excelField.isWrap(), isEnumClass);
                        }
                    } catch (Exception e1) {
                        logger.error("获取List值出现异常，堆栈信息如下：", e1);
                        continue;
                    }
                    rowNum = n > 1 ? row.getRowNum() + n : rowNum;
                }
            }

            for (Object[] os : annotationList) {
                ExcelField excelField = (ExcelField) os[0];
                if (excelField.isColumnHidden()) {
                    sheet.setColumnHidden(excelField.orderNum(), true);
                }
                int column = excelField.orderNum();
                boolean isEnumClass = StringUtils.isNotBlank(excelField.enumClass());
                column = isAddSortNum ? column + 1 : column;
                if (!"list".equals(os[2])) {
                    if (n > 1) {
                        setCellRange(sheet, row.getRowNum(), row.getRowNum() + n - 1, column, column);
                    }
                    if (ObjectUtils.isEmpty(os[2])) {
                        val = getValueFromBean(e, (String) os[1], excelField);
                    } else if ("bean".equals(os[2])) {
                        try {
                            val = getValueFromBean(PropertyUtils.getProperty(e, (String) os[3]), (String) os[1], excelField);
                        } catch (Exception ex) {
                            logger.error("获取Bean值出现异常，堆栈信息如下：", ex);
                            continue;
                        }
                    }
                    addCell(row, column, val, excelField.align(), excelField.isFormula(), excelField.isWrap(), isEnumClass);
                }
            }

            // 添加序号
            if (isAddSortNum) {
                num++;
                Cell cell = row.getCell(0);
                cell = (cell == null) ? row.createCell(0) : cell;
                if (StringUtils.isBlank(cell.getStringCellValue())) {
                    addCell(row, 0, num, 2, false, false, false);
                }
            }
        }
    }

    /**
     * 使用反射和设置的ExcelField来获取某个属性值
     *
     * @param bean         实例对象
     * @param propertyName 属性名称
     * @param excelField   映射对象
     */
    private <E> Object getValueFromBean(E bean, String propertyName, ExcelField excelField) {
        Object val = null;
        try {
            val = PropertyUtils.getProperty(bean, propertyName);

            // 如果是枚举
            if (StringUtils.isNotBlank(excelField.enumClass())) {
                Map<String, Object> resultMap = getEnumMap(Class.forName(excelField.enumClass()));
                val = 1 == excelField.enumType() ? resultMap.get(val) : val;
                val = 2 == excelField.enumType() ? val + "-" + resultMap.get(String.valueOf(val)) : val;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }


    /**
     * 初始化有导出注解的数据类型List
     *
     * @param cls 有annotation.ExportField注解的实体类
     */
    private void initAnnotationList(Class<?> cls) {
        annotationList = getAnnotationList(cls);
    }

    /**
     * 根据ExcelMapping 生成列头(当没有导出模板的时候使用)
     *
     * @param title      表格标题，传“空值”，表示无标题
     * @param headerList 表头列表
     */
    private void createTableHeader(String title, List<String> headerList) {
        this.sheet = workbook.createSheet(title);

        if (headerList == null) {
            throw new RuntimeException("headerList not null!");
        }

        Row headerRow = sheet.createRow(rowNum++);
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            if (styles != null && styles.containsKey("header")) {
                cell.setCellStyle(styles.get("header"));
            }
            cell.setCellValue(headerList.get(i));
            // 标题行高度自适应
            sheet.autoSizeColumn(i);
        }
        for (int i = 0; i < headerList.size(); i++) {
            int colWidth = sheet.getColumnWidth(i) * 2;
            sheet.setColumnWidth(i, Math.max(colWidth, 3000));
        }
        logger.debug("Initialize success.");
    }

    /**
     * 输出数据流
     *
     * @param os 输出数据流
     */
    public ExportExcel write(OutputStream os) throws IOException {
        workbook.write(os);
        return null;
    }

    /**
     * 输出到客户端
     *
     * @param response HttpServletResponse对象
     * @param fileName 输出文件名
     */
    public ExportExcel write(HttpServletResponse response, String fileName) throws IOException {
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        write(response.getOutputStream());
        return this;
    }

    /**
     * 把excel写到指定文件中
     *
     * @param fileFullName 指定文件全名
     */
    public ExportExcel writeFile(String fileFullName) throws IOException {
        FileOutputStream os = new FileOutputStream(fileFullName);
        this.write(os);
        return this;
    }

    /**
     * 设置下拉框
     */
    private void setDropdownBox() {
        for (int i = 0; i < annotationList.size(); i++) {
            ExcelField excelField = (ExcelField) annotationList.get(i)[0];
            int firstCol = i;
            int endCol = i;
            try {
                // 如果是枚举
                if (StringUtils.isNotBlank(excelField.enumClass())) {
                    if (isAddSortNum) {
                        firstCol = i + 1;
                        endCol = i + 1;
                    }

                    String[] textList;

                    if (2 == excelField.enumType()) {
                        List<String> enumList = Lists.newArrayList();
                        Map<String, Object> resultMap = getEnumMap(Class.forName(excelField.enumClass()));
                        resultMap.forEach((key, value) -> enumList.add(key + "-" + value));
                        textList = enumList.toArray(new String[0]);
                    } else if (1 == excelField.enumType()) {
                        Map<String, Object> resultMap = getEnumMap(Class.forName(excelField.enumClass()));
                        textList = resultMap.values().toArray(new String[0]);
                    } else {
                        Map<String, Object> resultMap = getEnumMap(Class.forName(excelField.enumClass()));
                        textList = resultMap.keySet().toArray(new String[0]);
                    }

                    DataValidation dataValidation = setDropdownBoxByDataSource(sheet, textList, rowNum, Short.MAX_VALUE, firstCol, endCol);
                    sheet.addValidationData(dataValidation);
                }
            } catch (Exception ex) {
                logger.error("设置下拉框出现异常，异常堆栈信息：", ex);
            }
        }
    }

}
