package org.dllwh.utils.apache.poi.excel.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;
import org.dllwh.utils.apache.poi.excel.annotation.*;
import org.dllwh.utils.apache.poi.excel.core.BaseCommonEnum;
import org.slf4j.*;

import java.lang.reflect.*;
import java.text.*;
import java.util.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: 导入导出工具类
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-04 14:24
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public final class ExcelHelper {
    private final static Logger logger = LoggerFactory.getLogger(ExcelHelper.class);
    /**
     * 内置样式
     */
    private static final Map<String, CellStyle> STYLE_MAP = new HashMap<>();

    /**
     * 创建表格样式
     *
     * @param workbook 工作薄对象
     */
    public static Map<String, CellStyle> createStyles(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font titleFont = workbook.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBold(true);
        style.setFont(titleFont);
        STYLE_MAP.put("header", style);

        style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 右边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        // 左边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        // 上边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        // 下边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        Font dataFont = workbook.createFont();
        dataFont.setFontName("Arial");
        style.setFont(dataFont);
        STYLE_MAP.put("data", style);

        style = workbook.createCellStyle();
        style.cloneStyleFrom(STYLE_MAP.get("data"));
        style.setAlignment(HorizontalAlignment.LEFT);
        STYLE_MAP.put("data1", style);

        style = workbook.createCellStyle();
        style.cloneStyleFrom(STYLE_MAP.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);

        STYLE_MAP.put("data2", style);

        style = workbook.createCellStyle();
        style.cloneStyleFrom(STYLE_MAP.get("data"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        STYLE_MAP.put("data3", style);

        return STYLE_MAP;
    }

    /**
     * 获取sheet页签的第i行对象
     *
     * @param sheet  Sheet页签对象
     * @param rowNum 行号（行号默认从0开始）
     */
    public static Row getRow(Sheet sheet, int rowNum) {
        rowNum = Math.max(rowNum, 0);
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        return row;
    }

    /**
     * 添加一个单元格
     *
     * @param row    行对象
     * @param column 添加列号
     * @param val    添加值
     */
    public static Cell addCell(Row row, int column, Object val) {
        return addCell(row, column, val, 0, false, false, false);
    }

    /**
     * 添加一个单元格
     *
     * @param row         行对象
     * @param column      添加列号
     * @param val         添加值
     * @param align       对齐方式（1：靠左；2：居中；3：靠右）
     * @param isFormula   是否为公式
     * @param isWrap      是否换行
     * @param isEnumClass 是否是枚举类
     */
    public static Cell addCell(Row row, int column, Object val, int align, boolean isFormula, boolean isWrap, boolean isEnumClass) {
        Cell cell = row.getCell(column);
        if (cell == null) {
            cell = row.createCell(column);
        }
        setCellValue(cell, val, isFormula);
        CellStyle style = STYLE_MAP.get("data" + align);
        if (style == null) {
            style = STYLE_MAP.get("data");
        }
        style.setWrapText(isWrap);
        // 如果是枚举类，则设置为文本类型
        if (isEnumClass) {
            Workbook workbook = cell.getSheet().getWorkbook();
            DataFormat format = workbook.createDataFormat();
            style.setDataFormat(format.getFormat("@"));
        }
        cell.setCellStyle(style);
        return cell;
    }

    /**
     * 获取合并单元格的值 （判断是否有合并单元格，若没有返回单个单元格的值，若有合并单元格，则获取合并单元格的值）
     *
     * @param sheet  Sheet页签对象
     * @param row    行号
     * @param column 列号
     */
    public static Object getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return getCellValue(sheet, firstRow, firstColumn);
                }
            }
        }
        return getCellValue(sheet, row, column);
    }

    /**
     * 获取单元格值
     *
     * @param sheet  Sheet页签对象
     * @param row    行号
     * @param column 列号
     */
    public static Object getCellValue(Sheet sheet, int row, int column) {
        Row rowObj = sheet.getRow(row);
        try {
            Cell cell = rowObj.getCell(column);
            if (cell != null) {
                switch (cell.getCellType()) {
                    case BOOLEAN:
                        return CellValue.valueOf(cell.getBooleanCellValue()).formatAsString();
                    case NUMERIC: // 数字
                        // 当excel 中的数据为数值或日期是需要特殊处理
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                            try {
                                return new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(date);
                            } catch (Exception e1) {
                                try {
                                    return new SimpleDateFormat("yyyy-MM-dd  HH:mm").format(date);
                                } catch (Exception e2) {
                                    return new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }
                            }

                        } else {
                            NumberFormat numberFormat = NumberFormat.getInstance();
                            numberFormat.setGroupingUsed(false);
                            return numberFormat.format(cell.getNumericCellValue());
                        }
                    case ERROR:// 故障
                        return CellValue.getError(cell.getErrorCellValue()).formatAsString();
                    case STRING: // 字符串
                        return cell.getStringCellValue();
                    case BLANK:// 空值
                        return null;
                    case FORMULA: // 公式
                        return cell.getCellFormula();
                    default:
                        throw new IllegalStateException("Unexpected value: " + cell.getCellType());
                }
            }
            return "";
        } catch (Exception e) {
            logger.error("ExcelHelper.getCellValue出现异常，堆栈信息如下：", e);
            return "";
        }
    }

    /**
     * 设置单元格值
     *
     * @param cell      单元格对象
     * @param val       值
     * @param isFormula 是否是公式
     */
    public static void setCellValue(Cell cell, Object val, boolean isFormula) {
        try {
            if (val == null) {
                cell.setCellValue("");
            } else if (val instanceof String) {
                // 如果是公式，则设置公式
                if (isFormula) {
                    cell.setCellFormula((String) val);
                } else {
                    cell.setCellValue((String) val);
                }
            } else if (val instanceof Integer) {
                cell.setCellValue((Integer) val);
            } else if (val instanceof Long) {
                cell.setCellValue((Long) val);
            } else if (val instanceof Double) {
                cell.setCellValue((Double) val);
            } else if (val instanceof Float) {
                cell.setCellValue((Float) val);
            } else if (val instanceof Date) {
                // 复制原来的样式，再添加日期的格式
                CellStyle cStyle = cell.getSheet().getWorkbook().createCellStyle();
                cStyle.cloneStyleFrom(cell.getCellStyle());
                cStyle.setDataFormat(cell.getSheet().getWorkbook().createDataFormat().getFormat("yyyy-MM-dd"));
                cell.setCellStyle(cStyle);
                cell.setCellValue((Date) val);
            } else if (val instanceof Enum) {
                cell.setCellValue((String) val);
            } else {
                cell.setCellValue("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Row row = cell.getRow();
            int column = cell.getColumnIndex();
            logger.info("Set cell value [" + row.getRowNum() + "," + column + "] error: " + ex.getMessage());
            cell.setCellValue("");
        }
    }

    /**
     * 获取Class中设置的ExcelField注解信息
     *
     * @param cls cls Class
     */
    public static List<Object[]> getAnnotationList(Class<?> cls) {
        List<Object[]> annotationList = new ArrayList<>();

        // 获得有注解的字段
        Field[] fs = cls.getDeclaredFields();
        for (Field f : fs) {
            addAnnotationListByField(annotationList, f, "", "");
            Excel excel = f.getAnnotation(Excel.class);
            if (excel != null) {
                Class<?> fieldClazz = f.getType();
                // 判断是否为集合类型
                if (Collection.class.isAssignableFrom(fieldClazz)) {
                    Type fc = f.getGenericType();
                    // 如果是泛型参数的类型
                    if (fc instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) fc;
                        //得到泛型里的class类型对象。
                        Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                        Field[] fs2 = genericClazz.getDeclaredFields();
                        for (Field f2 : fs2) {
                            addAnnotationListByField(annotationList, f2, "list", f.getName());
                        }
                        Method[] ms2 = genericClazz.getDeclaredMethods();
                        for (Method m2 : ms2) {
                            addAnnotationListByMethod(annotationList, m2, "list", f.getName());
                        }
                    }
                } else {
                    Field[] fs2 = fieldClazz.getDeclaredFields();
                    for (Field f2 : fs2) {
                        addAnnotationListByField(annotationList, f2, "bean", f.getName());
                    }
                    Method[] ms2 = fieldClazz.getDeclaredMethods();
                    for (Method m2 : ms2) {
                        addAnnotationListByMethod(annotationList, m2, "bean", f.getName());
                    }
                }
            }
        }
        // 获得有注解的方法
        Method[] methodArr = cls.getDeclaredMethods();
        for (Method method : methodArr) {
            addAnnotationListByMethod(annotationList, method, "", "");
            Excel excel = method.getAnnotation(Excel.class);
            if (excel != null) {
                Class<?> fieldClazz = method.getReturnType();
                // 判断是否为集合类型
                if (Collection.class.isAssignableFrom(fieldClazz)) {
                    Type fc = method.getGenericReturnType();
                    // 如果是泛型参数的类型
                    if (fc instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) fc;
                        // 得到泛型里的class类型对象。
                        Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                        Field[] fs2 = genericClazz.getDeclaredFields();
                        for (Field f2 : fs2) {
                            // 首字母转小写
                            addAnnotationListByField(annotationList, f2, "list", StringUtils.uncapitalize(method.getName().substring(3)));
                        }
                        Method[] methodArr2 = genericClazz.getDeclaredMethods();
                        for (Method method2 : methodArr2) {
                            // 首字母转小写
                            addAnnotationListByMethod(annotationList, method2, "list", StringUtils.uncapitalize(method.getName().substring(3)));
                        }
                    }
                } else {
                    Field[] fields = fieldClazz.getDeclaredFields();
                    for (Field field : fields) {
                        // 首字母转小写
                        addAnnotationListByField(annotationList, field, "bean", StringUtils.uncapitalize(method.getName().substring(3)));
                    }
                    Method[] methods = fieldClazz.getDeclaredMethods();
                    for (Method method3 : methods) {
                        // 首字母转小写
                        addAnnotationListByMethod(annotationList, method3, "bean", StringUtils.uncapitalize(method.getName().substring(3)));
                    }
                }
            }
        }
        // 对字段排序
        annotationList.sort(Comparator.comparingInt(o -> ((ExcelField) o[0]).orderNum()));
        return annotationList;
    }

    /**
     * 查找方法中设有ExcelField的信息，并将信息记入annotationList中
     *
     * @param method       方法对象
     * @param mark         标记是否是bean对象还是list对象，还是基础对象
     * @param propertyName f所属类在Bean对象（即setSheet传入的Class<?> cls参数）中对应的字段名
     */
    private static void addAnnotationListByMethod(List<Object[]> annotationList, Method method, String mark, String propertyName) {
        ExcelField excelField = method.getAnnotation(ExcelField.class);
        if (excelField != null) {
            // 首字母转小写
            annotationList.add(new Object[]{excelField, StringUtils.uncapitalize(method.getName().substring(3)), mark, propertyName});
        }
    }

    /**
     * 查找字段中设有ExcelField的信息，并将信息记入annotationList中
     *
     * @param field        字段对象
     * @param mark         标记是否是bean对象还是list对象，还是基础对象
     * @param propertyName 所属类在Bean对象（即setSheet传入的Class<?> cls参数）中对应的字段名
     */
    private static void addAnnotationListByField(List<Object[]> annotationList, Field field, String mark, String propertyName) {
        ExcelField excelField = field.getAnnotation(ExcelField.class);
        if (excelField != null) {
            annotationList.add(new Object[]{excelField, field.getName(), mark, propertyName});
        }
    }

    /**
     * 设置某些列的值只能输入预制的数据，显示下拉框即。适用于下拉选项字符少于225。
     *
     * @param sheet    Sheet页签对象
     * @param textList 下拉框显示的内容
     * @param firstRow 起始行
     * @param endRow   终止行
     * @param firstCol 起始列
     * @param endCol   终止列
     */
    public static DataValidation setDropdownBoxByDataSource(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol) {
        return setDropdownBoxByDataSource(sheet, textList, firstRow, endRow, firstCol, endCol, true);
    }

    /**
     * 设置某些列的值只能输入预制的数据，显示下拉框即。适用于下拉选项字符少于225。
     *
     * @param sheet       Sheet页签对象
     * @param textList    下拉框显示的内容
     * @param firstRow    起始行
     * @param endRow      终止行
     * @param firstCol    起始列
     * @param endCol      终止列
     * @param isShowError 输入无效数据后是否显示错误警告
     */
    public static DataValidation setDropdownBoxByDataSource(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol, Boolean isShowError) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
        // 设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);

        // 数据有效性对象
        DataValidation dataValidation = helper.createValidation(constraint, regions);

        // 设置单元格上提示
        dataValidation.setShowPromptBox(false);
        dataValidation.createPromptBox("标题", "内容");
        // 显示下拉箭头
        dataValidation.setSuppressDropDownArrow(true);
        // 忽略空值
        dataValidation.setEmptyCellAllowed(true);

        // 输入无效数据后是否显示错误警告
        if (isShowError) {
            dataValidation.createErrorBox("输入错误", "只能输入" + ArrayUtils.toString(textList, null));
            dataValidation.setShowErrorBox(true);
        }

        return dataValidation;
    }

    private static <E extends Enum<E> & BaseCommonEnum> Map<String, Object> getEnumsMap(Class<E> clazz) {
        Map<String, Object> resultMap = Maps.newHashMap();
        EnumSet<E> all = EnumSet.allOf(clazz);
        all.forEach(e -> resultMap.put(e.getTypeCode(), e.getTypeName()));
        return resultMap;
    }

    /**
     * 获取枚举的所有值，且key、value以"-"拼接
     *
     * @param clazz 枚举class
     */
    public static Map<String, Object> getEnumMap(Class clazz) {
        return getEnumsMap(clazz);
    }


    /**
     * 设置合并单元格
     *
     * @param sheet    Sheet页签对象
     * @param startRow 起始行号（从0开始）
     * @param endRow   结束行号（从0开始）
     * @param startCol 起始列号（从0开始）
     * @param endCol   结束列号（从0开始）
     */
    public static void setCellRange(Sheet sheet, int startRow, int endRow, int startCol, int endCol) {
        if (sheet == null) {
            return;
        }
        CellRangeAddress region = new CellRangeAddress((short) startRow, (short) endRow, (short) startCol, (short) endCol);
        sheet.addMergedRegion(region);
        // 设置合并后单元格的样式
        setCellRangeStyle(sheet, region);
    }

    /**
     * 设置合并单元格的格式
     *
     * @param sheet  Sheet页签对象
     * @param region 合并列索引
     */
    private static void setCellRangeStyle(Sheet sheet, CellRangeAddress region) {
        CellStyle style = STYLE_MAP.get("data");
        RegionUtil.setBorderBottom(style.getBorderBottom(), region, sheet);
        RegionUtil.setBorderTop(style.getBorderTop(), region, sheet);
        RegionUtil.setBorderLeft(style.getBorderLeft(), region, sheet);
        RegionUtil.setBorderRight(style.getBorderRight(), region, sheet);
        RegionUtil.setBottomBorderColor(style.getBottomBorderColor(), region, sheet);
        RegionUtil.setTopBorderColor(style.getTopBorderColor(), region, sheet);
        RegionUtil.setLeftBorderColor(style.getLeftBorderColor(), region, sheet);
        RegionUtil.setRightBorderColor(style.getRightBorderColor(), region, sheet);
    }
}

