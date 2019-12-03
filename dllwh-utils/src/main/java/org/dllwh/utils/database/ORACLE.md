# 数据库表说明:

* **获取表名和注释映射**

> 只查询有注释的字段

```
select 
	table_name tname,
	comments from all_tab_comments 
where 
	comments is not null -- 只查询有注释的字段
order by tname
```
	
* **获取获取表字段注释**

> 只查询有注释的字段

```
select 
	table_name tname,
	column_name cname,
	comments from all_col_comments 
where 
	comments is not null
order by table_name,column_name
```