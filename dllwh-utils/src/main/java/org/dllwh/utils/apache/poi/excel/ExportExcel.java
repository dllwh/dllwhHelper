package org.dllwh.utils.apache.poi.excel;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dllwh.utils.apache.poi.excel.annotation.*;
import org.slf4j.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import static org.dllwh.utils.apache.poi.excel.util.EnumHelper.*;
import static org.dllwh.utils.apache.poi.excel.util.ExcelHelper.*;
import static org.dllwh.utils.apache.poi.excel.util.AnnotationHelper.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: Excel导出工具类
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-03 21:22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public final class ExportExcel {
    private final Logger logger = LoggerFactory.getLogger(ExportExcel.class);


    /**
     * WorkBook对象
     */
    private final Workbook workbook;
    /**
     * Sheet(工作表)页面对象
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
     * @param filePath 模板文件
     */
    public ExportExcel(String filePath) throws IOException {
        if (StringUtils.isBlank(filePath)) {
            throw new RuntimeException("导出模板文档为空!");
        } else {
            this.workbook = createWorkbook(filePath);
        }
        this.styles = createStyles(workbook);
        logger.debug("成功打开导出模板.");
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
     * @param isJustData 是否只导出数据，若设置为false，导出的excel带有下拉框，否则只导出数据)
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
     * 设置sheet页签(有模板时使用,当需要手动写入Excel单元格值时使用)
     *
     * @param sheetIndex sheet页序号，默认从0开始
     */
    public void setSheet(int sheetIndex) {
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            throw new RuntimeException("文档中没有第" + (sheetIndex + 1) + "个Sheet页！");
        }
        this.sheet = getSheet(workbook, sheetIndex);
    }

    /**
     * 设置sheet页(有模板时使用)
     *
     * @param cls 实体类，通过annotation.ExportField获取设置
     */
    public void setSheet(Class<?> cls) {
        setSheet(0, 0, cls, false);
    }

    /**
     * 设置sheet页(有模板时使用)
     *
     * @param sheetIndex sheet页序号，默认从0开始
     * @param headerNum  标题行号，数据行号=标题行号+1
     * @param cls        实体类，通过annotation.ExportField获取设置
     */
    public void setSheet(int sheetIndex, int headerNum, Class<?> cls) {
        setSheet(sheetIndex, headerNum, cls, false);
    }

    /**
     * 设置sheet页(有模板时使用)
     *
     * @param sheetIndex sheet页序号，默认从0开始
     * @param headerNum  标题行号，数据行号=标题行号+1
     * @param cls        实体类，通过annotation.ExportField获取设置
     * @param isJustData 是否只导出数据，若设置为false，导出的excel带有下拉框，否则只导出数据
     */
    public void setSheet(int sheetIndex, int headerNum, Class<?> cls, Boolean isJustData) {
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            throw new RuntimeException("文档中没有第" + (sheetIndex +1) + "个Sheet页！");
        }
        this.sheet = getSheet(workbook, sheetIndex);
        this.headerNum = headerNum;
        this.rowNum = headerNum +1;
        this.dataStartNum = headerNum + 1;
        //初始化当前sheet对应的数据类型
        initAnnotationList(cls);
        // 检查模板表头是否也设置一样
        for (Object[] os : annotationList) {
            ExcelField excelField = (ExcelField) os[0];
            Object vlaue = getMergedRegionValue(sheet, headerNum, excelField.orderNum());
            if (!StringUtils.contains(vlaue.toString(), excelField.title())) {
                throw new RuntimeException("模板中表头信息" + excelField.title() + "与要导出的数据表头不一致，在" + (sheetIndex + 1) + "个sheet页！");
            }

        }

        if (!isJustData) {
            // 设置下拉框
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
                switch (excelField.enumType()) {
                    case 1:
                        val = resultMap.get(val);
                        break;
                    case 2:
                        val = val + "-" + resultMap.get(String.valueOf(val));
                        break;
                    default:
                }
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
        this.sheet = getSheet(workbook, title);

        if (headerList == null) {
            throw new RuntimeException("headerList not null!");
        }

        Row headerRow = getRow(sheet, rowNum++);

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
        logger.info("数据初始化标题列头成功.");
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

                    DataValidation dataValidation = setDropdownBoxByDataSource(sheet, textList, rowNum, Short.MAX_VALUE, firstCol, endCol, true);
                    sheet.addValidationData(dataValidation);
                }
            } catch (Exception ex) {
                logger.error("设置下拉框出现异常，异常堆栈信息：", ex);
            }
        }
    }

}
