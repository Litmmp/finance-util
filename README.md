# finance-util
## 人民币金额格式化工具类

### 一般格式化：没有千分号，保留两位小数，小数多余部分直接忽略
```java
String str1 = MoneyFormat.general(new BigDecimal("1023456789"));
// str1 = 1023456789.00
String str2 = MoneyFormat.general(new BigDecimal("1023456789.8"));
// str2 = 1023456789.80
String str3 = MoneyFormat.general(new BigDecimal("1023456789.89"));
// str3 = 1023456789.89
String str4 = MoneyFormat.general(new BigDecimal("1023456789.896"));
// str4 = 1023456789.89
```

### 千分位格式化：有千分号，保留两位小数，小数多余部分直接忽略
```java
String str1 = MoneyFormat.thousandth(new BigDecimal("1023456789"));
// str1 = 1,023,456,789.00
String str2 = MoneyFormat.thousandth(new BigDecimal("1023456789.8"));
// str2 = 1,023,456,789.80
String str3 = MoneyFormat.thousandth(new BigDecimal("1023456789.89"));
// str3 = 1,023,456,789.89
String str4 = MoneyFormat.thousandth(new BigDecimal("1023456789.896"));
// str4 = 1,023,456,789.89
```

### 中文格式化：适用于最多两位小数的金额，最大可格式化单位兆
```java
String str1 = MoneyFormat.chinese(new BigDecimal("1023456789"));
// str1 = 壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元整
String str2 = MoneyFormat.chinese(new BigDecimal("1023456789.8"));
// str2 = 壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元捌角整
String str3 = MoneyFormat.chinese(new BigDecimal("1023456789.89"));
// str3 = 壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元捌角玖分
String str4 = MoneyFormat.chinese(new BigDecimal("1023456789.896"));
// str4 = 壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元捌角玖分
```
### 注意事项
同一个金额，中文大写的写法有很多种。

##### 例如：1023456789.00
1. 壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元整（本工具采用的是这种写法）
2. 壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元正
3. 壹拾亿零贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元整
4. 壹拾亿零贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元正

##### 例如：1023456789.10
1. 壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元壹角整（本工具采用的是这种写法）
2. 壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元壹角正
3. 壹拾亿零贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元壹角整
4. 壹拾亿零贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元壹角正
5. 壹拾亿零贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元壹角