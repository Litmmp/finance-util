package com.comeheart.finance.util;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class MoneyFormatTest {

    @Test
    public void general() {
        String str1 = MoneyFormat.general(new BigDecimal("1023456789"));
        Assert.assertEquals(str1, "1023456789.00");
        String str2 = MoneyFormat.general(new BigDecimal("1023456789.8"));
        Assert.assertEquals(str2, "1023456789.80");
        String str3 = MoneyFormat.general(new BigDecimal("1023456789.89"));
        Assert.assertEquals(str3, "1023456789.89");
        String str4 = MoneyFormat.general(new BigDecimal("1023456789.896"));
        Assert.assertEquals(str4, "1023456789.89");
    }

    @Test
    public void thousandth() {
        String str1 = MoneyFormat.thousandth(new BigDecimal("1023456789"));
        Assert.assertEquals(str1, "1,023,456,789.00");
        String str2 = MoneyFormat.thousandth(new BigDecimal("1023456789.8"));
        Assert.assertEquals(str2, "1,023,456,789.80");
        String str3 = MoneyFormat.thousandth(new BigDecimal("1023456789.89"));
        Assert.assertEquals(str3, "1,023,456,789.89");
        String str4 = MoneyFormat.thousandth(new BigDecimal("1023456789.896"));
        Assert.assertEquals(str4, "1,023,456,789.89");
    }

    @Test
    public void chinese() {
        String str1 = MoneyFormat.chinese(new BigDecimal("1023456789"));
        Assert.assertEquals(str1, "壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元整");
        String str2 = MoneyFormat.chinese(new BigDecimal("1023456789.8"));
        Assert.assertEquals(str2, "壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元捌角整");
        String str3 = MoneyFormat.chinese(new BigDecimal("1023456789.89"));
        Assert.assertEquals(str3, "壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元捌角玖分");
        String str4 = MoneyFormat.chinese(new BigDecimal("1023456789.896"));
        Assert.assertEquals(str4, "壹拾亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元捌角玖分");
    }

}