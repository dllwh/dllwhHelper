package org.dllwh.utils.apache.lang.doc;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;

/**
 * @类描述: StringUtils 操作方法 说明文档
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月21日 下午9:58:11
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/">
 *      org.apache.commons.lang3</a>
 */
interface StringUtilsDoc {
	/**
	 * 判断是否Null 或者 ""
	 */
	boolean isEmpty(final CharSequence cs);

	/**
	 * 判断是否非Null 或者 非""
	 */
	boolean isNotEmpty(final CharSequence cs);

	/**
	 * 检查是否有一个或多个字符串为""或null
	 */
	boolean isAnyEmpty(final CharSequence... css);

	/**
	 * 检查一个字符串是否为空白字符串、""或null
	 */
	boolean isNoneEmpty(final CharSequence... css);

	/**
	 * 判断是否null 或者 "" 或长度为0或由空白符构成
	 */
	boolean isBlank(final CharSequence cs);

	/**
	 * 判断是否null 或者 "" 去空格
	 */
	boolean isNotBlank(final CharSequence cs);

	/**
	 * 检查是否有一个或多个字符串为空白字符串、""或null
	 */
	boolean isAnyBlank(final CharSequence... css);

	/**
	 * @方法:
	 */
	boolean isNoneBlank(final CharSequence... css);

	/**
	 * 去掉字符串两端的控制符，如果输入空白字符串、""或null，则返回null 。
	 */
	String trim(final String str);

	/**
	 * 去掉字符串两端的控制符，如果输入空白字符串、""或null，则返回""
	 */
	String trimToNull(final String str);

	/**
	 * 去掉字符串两端的空白符(whitespace)，如果输入null则返回null，将NULL 和 "" 转换为""
	 */
	String trimToEmpty(final String str);

	/**
	 * 可能是对特殊空格符号去除
	 */
	String strip(final String str);

	/**
	 * 同上，将""和null转换为Null
	 */
	String stripToNull(String str);

	/**
	 * 去除指定字符串 如果第二个参数为null去空格(否则去掉字符串2边一样的字符，到不一样为止)
	 */
	String strip(String str, final String stripChars);

	/**
	 * 去掉字符串前指定的字符内容 如果第二个参数为null只去前面空格，否则去掉字符串前面一样的字符，到不一样为止
	 */
	String stripStart(final String str, final String stripChars);

	/**
	 * 去掉字符串后指定的字符内容 如果第二个参数为null只去后面空格，(否则去掉字符串后面一样的字符，到不一样为止)
	 */
	String stripEnd(final String str, final String stripChars);

	/**
	 * @方法:对数组没个字符串进行去空格
	 */
	String[] stripAll(final String... strs);

	/**
	 * @方法:如果第二个参数为null.对数组每个字符串进行去空格。(否则去掉数组每个元素开始和结尾一样的字符)
	 */
	String[] stripAll(final String[] strs, final String stripChars);

	/**
	 * 去掉参数中的一些特殊符号 如： 'à' 会被替换为 'a'
	 */
	String stripAccents(final String input);

	/**
	 * @方法:判断2个字符串是否相等相等,Null也相等
	 */
	boolean equals(final CharSequence cs1, final CharSequence cs2);

	/**
	 * @方法:不区分大小写比较
	 */
	boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2);

	/**
	 * 返回字符 searchChar 在seq 中第一次出现的位置。 如果一参数为null或者""返回-1，searchChar 没有在 seq中出现则返回-1
	 */
	int indexOf(final CharSequence seq, final int searchChar);

	/**
	 * @方法:从指定位置(startPos)开始查找
	 */
	int indexOf(final CharSequence seq, final int searchChar, final int startPos);

	/**
	 * @方法:
	 */
	int indexOf(final CharSequence seq, final CharSequence searchSeq);

	/**
	 * @方法:
	 */
	int indexOf(final CharSequence seq, final CharSequence searchSeq, final int startPos);

	/**
	 * 返回第n次匹配的所在的索引数
	 */
	int ordinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal);

	/**
	 * 查找,不区分大小写
	 */
	int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr);

	/**
	 * @方法:从指定位置(startPos)开始查找,不区分大小写
	 */
	int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos);

	/**
	 * @方法:从后往前查找
	 */
	int lastIndexOf(final CharSequence seq, final int searchChar);

	/**
	 * @方法:从后往前查找
	 */
	int lastIndexOf(final CharSequence seq, final int searchChar, final int startPos);

	/**
	 * @方法:从后往前查找
	 */
	int lastIndexOf(final CharSequence seq, final CharSequence searchSeq);

	/**
	 * @方法:从后往前查，不区分大小写
	 */
	int lastOrdinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal);

	/**
	 * @方法:
	 */
	int lastIndexOf(final CharSequence seq, final CharSequence searchSeq, final int startPos);

	/**
	 * @方法:返回字符串searchStr在字符串str中最后一次出现的位置，忽略大小写
	 */
	int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr);

	/**
	 * @方法:返回字符串searchStr在字符串str中最后一次出现的位置，忽略大小写
	 */
	int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos);

	/**
	 * @方法:
	 */
	boolean contains(final CharSequence seq, final int searchChar);

	/**
	 * 检查字符串seq 是否包含字符串searchSeq
	 */
	boolean contains(final CharSequence seq, final CharSequence searchSeq);

	/**
	 * 检查字符串seq 是否包含字符串searchSeq,不区分大小写
	 */
	boolean containsIgnoreCase(final CharSequence str, final CharSequence searchStr);

	/**
	 * 检查是否有含有空格,返回boolean
	 */
	boolean containsWhitespace(final CharSequence seq);

	/**
	 * 查询字符串跟数组任一元素相同的第一次相同的位置
	 */
	int indexOfAny(final CharSequence cs, final char... searchChars);

	/**
	 * 查询字符串中指定字符串(searchChars)出现的次数
	 */
	int indexOfAny(final CharSequence cs, final String searchChars);

	/**
	 * 查找字符串中是否有字符数组中相同的字符，返回boolean
	 */
	boolean containsAny(final CharSequence cs, final char... searchChars);

	/**
	 * 批量判断包含任意一个
	 */
	boolean containsAny(final CharSequence cs, final CharSequence searchChars);

	/**
	 * 批量判断包含任意一个
	 */
	boolean containsAny(CharSequence cs, CharSequence... searchCharSequences);

	/**
	 * 返回不在搜索字符范围内的第一个索引位置
	 */
	int indexOfAnyBut(final CharSequence cs, final char... searchChars);

	/**
	 * 返回不在搜索字符范围内的第一个索引位置
	 */
	int indexOfAnyBut(final CharSequence seq, final CharSequence searchChars);

	/**
	 * 判断字符串中所有字符，都是出自参数二中
	 */
	boolean containsOnly(final CharSequence cs, final char... valid);

	/**
	 * 判断字符串中所有字符，都是出自参数二的数组中
	 */
	boolean containsOnly(final CharSequence cs, final String validChars);

	/**
	 * 判断字符串中所有字符，都不在参数二的数组中
	 */
	boolean containsNone(final CharSequence cs, final char... searchChars);

	/**
	 * 判断字符串中所有字符，都不在参数二中
	 */
	boolean containsNone(final CharSequence cs, final String invalidChars);

	int indexOfAny(final CharSequence str, final CharSequence... searchStrs);

	/**
	 * @方法:从后往前查找字符串中与字符数组中相同的元素第一次出现的位置
	 */
	int lastIndexOfAny(final CharSequence str, final CharSequence... searchStrs);

	/**
	 * 截取指定位置的字符
	 */
	String substring(final String str, int start);

	/**
	 * 截取指定区间的字符
	 */
	String substring(final String str, int start, int end);

	/**
	 * 从左开始截取指定长度的字符串 第一个参数：原字符串，第二个参数：取左侧字符串的长度
	 */
	String left(final String str, final int len);

	/**
	 * 从右截开始取指定长度的字符串
	 */
	String right(final String str, final int len);

	/**
	 * @方法:从第几个开始截取，三参数表示截取的长度
	 */
	String mid(final String str, int pos, final int len);

	/**
	 * 从左往右截取到指定separator的字符串之前为止
	 */
	String substringBefore(final String str, final String separator);

	/**
	 * 从左往右查到相等的字符开始，保留后边的，不包含等于的字符
	 */
	String substringAfter(final String str, final String separator);

	/**
	 * 这个也是截取到相等的字符，但是是从右往左
	 */
	String substringBeforeLast(final String str, final String separator);

	/**
	 * 这个截取同上是从右往左。但是保留右边的字符
	 */
	String substringAfterLast(final String str, final String separator);

	/**
	 * 截取特定字符串(tag)中间部分，如果没找到第二个返回null
	 * <p>
	 * StringUtils.substringBetween("tagabctag", "tag") = "abc"
	 */
	String substringBetween(final String str, final String tag);

	/**
	 * 返回起止字符串中间的字符串，且只返回第一次匹配结果
	 * <p>
	 * StringUtils.substringBetween("tagabctag", "tag","ag") = "abcabct"
	 */
	String substringBetween(final String str, final String open, final String close);

	/**
	 * @方法:返回 open 和 close 中间的字符串，返回数组形式
	 */
	String[] substringsBetween(final String str, final String open, final String close);

	/**
	 * 用空格分割成数组
	 */
	String[] split(final String str);

	/**
	 * 以指定字符分割成数组
	 */
	String[] split(final String str, final char separatorChar);

	/**
	 * 以指定间隔符分割成数组
	 */
	String[] split(final String str, final String separatorChars);

	/**
	 * 以指定字符分割成数组，参数(max)表示分隔成数组的长度，如果为0全体分割
	 */
	String[] split(final String str, final String separatorChars, final int max);

	/**
	 * 指定字符分割成数组
	 */
	String[] splitByWholeSeparator(final String str, final String separator);

	/**
	 * 以指定字符分割成数组，参数(max)表示分隔成数组的长度
	 */
	String[] splitByWholeSeparator(final String str, final String separator, final int max);

	/**
	 * 分割，但" "不会被忽略算一个元素,二参数为null默认为空格分隔
	 */
	String[] splitByWholeSeparatorPreserveAllTokens(final String str, final String separator);

	/**
	 * 同上，分割," "不会被忽略算一个元素。第三个参数代表分割的数组长度
	 */
	String[] splitByWholeSeparatorPreserveAllTokens(final String str, final String separator, final int max);

	/**
	 * 分割。 空白字符作为一个数组元素返回
	 * <p>
	 * StringUtils.splitPreserveAllTokens("abc def") = ["abc", "def"]
	 * StringUtils.splitPreserveAllTokens("abc def") = ["abc", "", "def"]
	 * StringUtils.splitPreserveAllTokens(" abc ") = ["", "abc", ""]
	 */
	String[] splitPreserveAllTokens(final String str);

	/**
	 * 指定字符分割成数组
	 */
	String[] splitPreserveAllTokens(final String str, final char separatorChar);

	/**
	 * 指定字符分割成数组
	 */
	String[] splitPreserveAllTokens(final String str, final String separatorChars);

	/**
	 * 以指定字符分割成数组，第三个参数表示分隔成数组的长度
	 */
	String[] splitPreserveAllTokens(final String str, final String separatorChars, final int max);

	/**
	 * @方法:以不同类型进行分隔
	 */
	String[] splitByCharacterType(final String str);

	/**
	 * 根据字符类型分割，同一类划为一个数组元素，驼峰命名情况下，最后一个大写字母归属后面元素而不是前面
	 * <p>
	 * StringUtils.splitByCharacterTypeCamelCase("ab de fg") = ["ab", " ", "de", "
	 * ", "fg"] StringUtils.splitByCharacterTypeCamelCase("ab de fg") = ["ab", " ",
	 * "de", " ", "fg"] StringUtils.splitByCharacterTypeCamelCase("ab:cd:ef") =
	 * ["ab", ":", "cd", ":", "ef"]
	 * StringUtils.splitByCharacterTypeCamelCase("number5") = ["number", "5"]
	 * StringUtils.splitByCharacterTypeCamelCase("fooBar") = ["foo", "Bar"]
	 * StringUtils.splitByCharacterTypeCamelCase("foo200Bar") = ["foo", "200",
	 * "Bar"] StringUtils.splitByCharacterTypeCamelCase("ASFRules") = ["ASF",
	 * "Rules"]
	 */
	String[] splitByCharacterTypeCamelCase(final String str);

	/**
	 * 默认合并，注意：自动去除空白字符或null元素 StringUtils.join(null) = null StringUtils.join([]) =
	 * "" StringUtils.join([null]) = "" StringUtils.join(["a", "b", "c"]) = "abc"
	 * StringUtils.join([null, "", "a"]) = "a"
	 */
	@SuppressWarnings("unchecked")
	<T> String join(final T... elements);

	/**
	 * 将数组或集合以某拼接符拼接到一起形成新的字符串。注意：保留空白字符或null元素
	 * <p>
	 * StringUtils.join(["a", "b", "c"], ';') = "a;b;c" StringUtils.join([null, "",
	 * "a"], ';') = ";;a"
	 */
	String join(final Object[] array, final char separator);

	/**
	 * 拼接指定数组下标的开始(startIndex)和结束(endIndex,不包含)的中间这些元素，用连接符连接
	 */
	String join(final Object[] array, final char separator, final int startIndex, final int endIndex);

	String join(final Object[] array, String separator, final int startIndex, final int endIndex);

	/**
	 * 拼接数值
	 */
	String join(long[] array, char separator);

	/**
	 * 拼接数值
	 */
	String join(int[] array, char separator);

	String join(final Iterable<?> iterator, final char separator);

	String join(final Iterable<?> iterator, final String separator);

	String join(final Iterator<?> iterator, final char separator);

	String join(final Iterator<?> iterator, final String separator);

	/**
	 * 删除所有空格符
	 */
	String deleteWhitespace(final String str);

	/**
	 * 移除开始部分的相同的字符
	 */
	String removeStart(final String str, final String remove);

	/**
	 * 移除开始部分的相同的字符,不区分大小写
	 */
	String removeStartIgnoreCase(final String str, final String remove);

	/**
	 * 移除后面相同的部分
	 */
	String removeEnd(final String str, final String remove);

	/**
	 * 移除后面相同的部分，不区分大小写
	 */
	String removeEndIgnoreCase(final String str, final String remove);

	/**
	 * 移除所有相同的部分
	 */
	String remove(final String str, final String remove);

	/**
	 * 移除字符
	 */
	String remove(final String str, final char remove);

	/**
	 * 替换指定的字符，只替换第一次出现的
	 */
	String replaceOnce(final String text, final String searchString, final String replacement);

	/**
	 * @方法:
	 */
	String replacePattern(final String source, final String regex, final String replacement);

	/**
	 * @方法:
	 */
	String removePattern(final String source, final String regex);

	/**
	 * 替换所有出现过的字符
	 */
	String replace(final String text, final String searchString, final String replacement);

	/**
	 * 替换所有出现过的字符，最后一个参数表示替换几个
	 * <p>
	 * StringUtils.replace("abaa", "a", "z", 0) = "abaa" StringUtils.replace("abaa",
	 * "a", "z", 1) = "zbaa" StringUtils.replace("abaa", "a", "z", 2) = "zbza"
	 * StringUtils.replace("abaa", "a", "z", -1) = "zbzz"
	 *
	 * @param max 替换次数，0代表不做替换，-1代表替换所有
	 */
	String replace(final String text, final String searchString, final String replacement, int max);

	/**
	 * 扩展批量，过于复杂，不建议使用 二三参数对应的数组，查找二参数数组一样的值，替换三参数对应数组的值
	 */
	String replaceEach(final String text, final String[] searchList, final String[] replacementList);

	/**
	 * 扩展批量，过于复杂，不建议使用 二三参数对应的数组，查找二参数数组一样的值，替换三参数对应数组的值
	 */
	String replaceEachRepeatedly(final String text, final String[] searchList, final String[] replacementList);

	/**
	 * 替换所有出现过的字符
	 */
	String replaceChars(final String str, final char searchChar, final char replaceChar);

	/**
	 * 替换所有出现过的字符
	 */
	String replaceChars(final String str, final String searchChars, String replaceChars);

	/**
	 * 替换指定开始(start)和结束(end)中间的所有字符
	 * <p>
	 * StringUtils.overlay("13712345678","****",3,7)=“137****5678”
	 */
	String overlay(final String str, String overlay, int start, int end);

	/**
	 * 移除结尾字符为"\n","\r",或者 "\r\n".
	 * <p>
	 * StringUtils.chomp("\r") = "" StringUtils.chomp("\n") = ""
	 * StringUtils.chomp("\r\n") = "" StringUtils.chomp("abc \r") = "abc "
	 * StringUtils.chomp("abc\n") = "abc" StringUtils.chomp("abc\r\n") = "abc"
	 * StringUtils.chomp("abc\r\n\r\n") = "abc\r\n" StringUtils.chomp("abc\n\r") =
	 * "abc\n" StringUtils.chomp("abc\n\rabc") = "abc\n\rabc"
	 */
	String chomp(final String str);

	/**
	 * @方法:去掉末尾最后一个字符.如果是"\n","\r",或者 "\r\n"也去除 注意：使用时需确保最后一位一定是间隔符，否则有可能破坏正常数据
	 *                                <p>
	 *                                StringUtils.chop("1,2,3,") = "1,2,3"
	 *                                StringUtils.chop("a") = ""
	 *                                StringUtils.chop("abc") = "ab"
	 *                                StringUtils.chop("abc\nabc") = "abc\nab"
	 */
	String chop(final String str);

	/**
	 * 复制参数str，repeat为复制的次数
	 * <p>
	 * StringUtils.repeat("a", 3) = "aaa" StringUtils.repeat("ab", 2) = "abab"
	 */
	String repeat(final String str, final int repeat);

	/**
	 * 复制参数str，参数repeat为复制的次数。参数separator为复制字符串中间的连接字符串
	 */
	String repeat(final String str, final String separator, final int repeat);

	/**
	 * 如何字符串长度小于参数二的值，末尾加空格补全。(小于字符串长度不处理返回)
	 */
	String repeat(final char ch, final int repeat);

	/**
	 * @方法:字符串长度小于二参数，末尾用参数三补上，多于的截取(截取补上的字符串)
	 */
	String rightPad(final String str, final int size);

	/**
	 * @方法:
	 */
	String rightPad(final String str, final int size, final char padChar);

	/**
	 * @方法:
	 */
	String rightPad(final String str, final int size, String padStr);

	/**
	 * 左侧补齐，默认使用空格补齐，第一个参数：原字符串，第二个参数：字符串总长度，不足用空格补全
	 */
	String leftPad(final String str, final int size);

	/**
	 * 左侧补充。 第一个参数：原始字符串，第二个参数：字符串的长度，第三个是补充的字符串 字符串长度小于二参数，前面用参数三补上，多于的截取(截取补上的字符串)
	 */
	String leftPad(final String str, final int size, final char padChar);

	/**
	 * @方法:字符串长度小于二参数，前面用参数三补上，多于的截取(截取补上的字符串)
	 */
	String leftPad(final String str, final int size, String padStr);

	/**
	 * @方法:
	 */
	int length(final CharSequence cs);

	/**
	 * 自动补齐至指定宽度，可指定字符，如不指定，默认补空格
	 *
	 * 字符串长度小于二参数。在两侧用空格平均补全（测试后面补空格优先）
	 */
	String center(final String str, final int size);

	/**
	 * 左侧补齐 第一个参数：原始字符串，第二个参数：字符串的长度，第三个是补充的字符串
	 */
	String center(String str, final int size, final char padChar);

	/**
	 * @方法:
	 */
	String center(String str, final int size, String padStr);

	/**
	 * 字符串转大写
	 */
	String upperCase(final String str);

	/**
	 * 字符串转大写
	 */
	String upperCase(final String str, final Locale locale);

	/**
	 * 字符串转小写
	 */
	String lowerCase(final String str);

	/**
	 * 字符串转小写
	 */
	String lowerCase(final String str, final Locale locale);

	/**
	 * 将字符串首字母大写
	 */
	String capitalize(final String str);

	/**
	 * 将字符串首字母小写
	 */
	String uncapitalize(final String str);

	/**
	 * 大小写互相转换，大写变小写，小写变大写
	 */
	String swapCase(final String str);

	/**
	 * 未发现与indexOfAny不同之处 查询字符串中指定字符串(参数二)出现的次数
	 */
	int countMatches(final CharSequence str, final CharSequence sub);

	/**
	 * 计算匹配次数
	 */
	int countMatches(final CharSequence str, final char ch);

	/**
	 * 检查是否CharSequence的只包含Unicode的字母。一个空的CharSequence将返回true
	 */
	boolean isAlpha(final CharSequence cs);

	/**
	 * 检查是否只包含Unicode的CharSequence的字母和空格（''）
	 */
	boolean isAlphaSpace(final CharSequence cs);

	/**
	 * 检查是否只包含Unicode的CharSequence的字母或数字
	 */
	boolean isAlphanumeric(final CharSequence cs);

	/**
	 * 如果检查的Unicode CharSequence的只包含字母，数字或空格（''）
	 */
	boolean isAlphanumericSpace(final CharSequence cs);

	/**
	 * 检查是否只包含ASCII可CharSequence的字符。
	 */
	boolean isAsciiPrintable(final CharSequence cs);

	/**
	 * 判断是否只包含数值，注意：小数点和正负号，都会判定为false
	 */
	boolean isNumeric(final CharSequence cs);

	/**
	 * 判断是否只包含数字及空格
	 */
	boolean isNumericSpace(final CharSequence cs);

	/**
	 * 判定是否只是空格或""
	 */
	boolean isWhitespace(final CharSequence cs);

	/**
	 * 判定是否全是英文小写
	 */
	boolean isAllLowerCase(final CharSequence cs);

	/**
	 * 判定是否全是英文大写
	 */
	boolean isAllUpperCase(final CharSequence cs);

	/**
	 * 获取默认字符串，null及空格将会返回“”，其他情况返回原始字符串
	 */
	String defaultString(final String str);

	/**
	 * 获取默认字符串，第一个参数为null及空格将会返回第二个参数指定值，其他情况返回原始字符串
	 */
	String defaultString(final String str, final String defaultStr);

	/**
	 * 如果为空白或空，返回指定值
	 */
	<T extends CharSequence> T defaultIfBlank(final T str, final T defaultStr);

	/**
	 * 如果为空白或空，返回指定值
	 */
	<T extends CharSequence> T defaultIfEmpty(final T str, final T defaultStr);

	/**
	 * 将字符串倒序排列
	 */
	String reverse(final String str);

	/**
	 * 根据特定字符(二参数)分隔进行反转
	 */
	String reverseDelimited(final String str, final char separatorChar);

	/**
	 * 只显示指定数量(maxWidth)的字符,后面以三个点补充(参数一截取+三个点=二参数)
	 * <p>
	 * 如果字符长度小于maxWidth，直接返回该字符串，否则缩减效果为 substring(str, 0, max-3) + "..."
	 * <p>
	 * StringUtils.abbreviate("abcdefg", 4) = "a..."
	 */
	String abbreviate(final String str, final int maxWidth);

	/**
	 * 将字符串缩减为指定宽度
	 */
	String abbreviate(final String str, int offset, final int maxWidth);

	/**
	 * 保留指定长度，最后一个字符前加点.
	 */
	String abbreviateMiddle(final String str, final String middle, final int length);

	/**
	 * 返回字符串差异部分，实用性差，不建议使用
	 */
	String difference(final String str1, final String str2);

	/**
	 * 统计2个字符串开始部分共有的字符个数
	 */
	int indexOfDifference(final CharSequence cs1, final CharSequence cs2);

	/**
	 * 统计数组中各个元素的字符串开始都一样的字符个数
	 */
	int indexOfDifference(final CharSequence... css);

	/**
	 * 取数组每个元素共同的部分字符串
	 */
	String getCommonPrefix(final String... strs);

	/**
	 * 统计参数一中每个字符与参数二中每个字符不同部分的字符个数
	 */
	int getLevenshteinDistance(CharSequence s, CharSequence t);

	/**
	 * @方法:
	 */
	int getLevenshteinDistance(CharSequence s, CharSequence t, final int threshold);

	/**
	 * @方法:
	 */
	double getJaroWinklerDistance(final CharSequence first, final CharSequence second);

	/**
	 * @方法:
	 */
	int getFuzzyDistance(final CharSequence term, final CharSequence query, final Locale locale);

	/**
	 * 判断 str 是否以 prefix 开始
	 */
	boolean startsWith(final CharSequence str, final CharSequence prefix);

	/**
	 * 判断 str 是否以 prefix 开始，不区分大小写
	 */
	boolean startsWithIgnoreCase(final CharSequence str, final CharSequence prefix);

	/**
	 * 判断字符串开始部分是否与数组中的某一元素相同
	 */
	boolean startsWithAny(final CharSequence string, final CharSequence... searchStrings);

	/**
	 * 判断 str 是否以 suffix 结尾
	 */
	boolean endsWith(final CharSequence str, final CharSequence suffix);

	/**
	 * 判断 str 是否以 suffix 结尾，不区分大小写
	 */
	boolean endsWithIgnoreCase(final CharSequence str, final CharSequence suffix);

	/**
	 * 去除首尾，但中间的空白字符，替换为单个空格
	 */
	String normalizeSpace(final String str);

	/**
	 * @方法:
	 */
	boolean endsWithAny(final CharSequence string, final CharSequence... searchStrings);

	/**
	 * 追加后缀，如只有两个参数，则是无条件追加，超过两个参数，是在不匹配suffixes任何情况下才追加
	 */
	String appendIfMissing(final String str, final CharSequence suffix, final CharSequence... suffixes);

	/**
	 * 追加后缀，如只有两个参数，则是无条件追加，超过两个参数，是在不匹配suffixes任何情况下才追加
	 */
	String appendIfMissingIgnoreCase(final String str, final CharSequence suffix, final CharSequence... suffixes);

	/**
	 * 追加前缀，如只有两个参数，则是无条件追加，超过两个参数，是在不匹配prefixes任何情况下才追加
	 */
	String prependIfMissing(final String str, final CharSequence prefix, final CharSequence... prefixes);

	/**
	 * 追加前缀，如只有两个参数，则是无条件追加，超过两个参数，是在不匹配prefixes任何情况下才追加
	 */
	String prependIfMissingIgnoreCase(final String str, final CharSequence prefix, final CharSequence... prefixes);

	/**
	 * @方法:
	 */
	String toString(final byte[] bytes, final String charsetName) throws UnsupportedEncodingException;

	/**
	 * @方法:
	 */
	String toEncodedString(final byte[] bytes, final Charset charset);

	/**
	 * 无条件同时增加前缀和后缀
	 */
	String wrap(final String str, final char wrapWith);

	/**
	 * 无条件同时增加前缀和后缀
	 */
	String wrap(final String str, final String wrapWith);

	/**
	 * 有条件同时增加前缀和后缀 StringUtils.wrapIfMissing(null, *) = null
	 * StringUtils.wrapIfMissing("", *) = "" StringUtils.wrapIfMissing("ab", '\0') =
	 * "ab" StringUtils.wrapIfMissing("ab", 'x') = "xabx"
	 * StringUtils.wrapIfMissing("ab", '\'') = "'ab'"
	 * StringUtils.wrapIfMissing("\"ab\"", '\"') = "\"ab\""
	 * StringUtils.wrapIfMissing("/", '/') = "/" StringUtils.wrapIfMissing("a/b/c",
	 * '/') = "/a/b/c/" StringUtils.wrapIfMissing("/a/b/c", '/') = "/a/b/c/"
	 * StringUtils.wrapIfMissing("a/b/c/", '/') = "/a/b/c/"
	 */
	String wrapIfMissing(final String str, final char wrapWith);

	/**
	 * 去除前缀和后缀
	 * <p>
	 * StringUtils.unwrap(null, null) = null StringUtils.unwrap(null, '\0') = null
	 * StringUtils.unwrap(null, '1') = null StringUtils.unwrap("a", 'a') = "a"
	 * StringUtils.unwrap("aa", 'a') = "" StringUtils.unwrap("\'abc\'", '\'') =
	 * "abc" StringUtils.unwrap("AABabcBAA", 'A') = "ABabcBA"
	 * StringUtils.unwrap("A", '#') = "A" StringUtils.unwrap("#A", '#') = "#A"
	 * StringUtils.unwrap("A#", '#') = "A#"
	 */
	String unwrap(String str, char wrapChar);
}