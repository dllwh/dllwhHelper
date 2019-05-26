package org.dllwh.utils.apache.poi.excel;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 解析EXCEL文档
 *       <ul>
 *       <li>Java读取excel文件的顺序是</li>
 *       <li>Excel文件->工作表->行->单元格 对应到POI中为:workbook->sheet->row->cell</li>
 *       <li>● sheet:以0开始,以workbook.getNumberOfSheets()-1结束</li>
 *       <li>● row:以0开始(getFirstRowNum),以getLastRowNum结束</li>
 *       <li>● cell:以0开始(getFirstCellNum),以getLastCellNum结束</li>
 *       <li>●</li>
 *       <li></li>
 *       </ul>
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月7日 下午11:54:39
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class ExcelParseHelper {

}
