#package说明

> `webCrawler是一个网络爬虫工具`

> `在抓取数据时,很多的时候是需要登录的,这样是web网站防止数据被人以不正当的手段抓取采用的手段之一,这也是阻止数据被抓取的有效的方法之一.对此,聪明的程序猿们也想出的集中对策,在这里简要的说明下网络爬虫模拟登录的两种常用的策略以及其优缺:`

> + `策略一:模拟浏览器登录,用代码模拟表单填写,然后获取登陆后的信息,用apache的“HttpClients”进行信息保存`

> > + `特点:操作复杂,需要有足够深的http相关的知识,才能灵活运用,并且许多网站有很多加密规则,需要具体问题具体解决;不过,不需要考虑cookie失效问题`

> + `策略二:直接拿去cookie信息,进行设置`

> > + `特点:操作简单,只要将登陆后的页面的cookie信息拿过来即可;不过,可能会用时间限制,超过一定时间就不能再使用了,需要重新设置`

-----------------------------------
