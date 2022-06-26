package com.comeheart.finance.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 货币金额工具
 */
public class MoneyFormat {

    private static final String ZERO = "零";

    private static final String WHOLE = "整";

    private static final String[] NUMBER = {"壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    private static final String[] NUMBER_UNIT_1 = {"仟", "佰", "拾", ""};

    private static final String[] NUMBER_UNIT_2 = {"兆", "亿", "万", ""};

    private static final String[] CURRENCY_UNIT = {"元", "角", "分"};

    /**
     * 格式化成一般金额，保留两位小数，不足补零，没有千分号
     *
     * @param money 金额
     * @return 格式化金额
     */
    public static String general(BigDecimal money) {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(money);
    }

    /**
     * 格式化成千分位金额，保留两位小数，不足补零，有千分号
     *
     * @param money 金额
     * @return 格式化金额
     */
    public static String thousandth(BigDecimal money) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(money);
    }

    /**
     * 金额转中文大写
     *
     * @param money 金额
     * @return 大写金额
     */
    public static String chinese(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) == 0) return ZERO + CURRENCY_UNIT[0] + WHOLE;
        BigDecimal temp = money.setScale(2, RoundingMode.DOWN);
        String number = temp.toString();
        String[] split = number.split("\\.");
        String integer = split[0], decimal = split[1];

        StringBuilder integerSb = readInteger(integer);
        StringBuilder decimalSb = readDecimal(decimal);

        if (decimalSb.length() == 0 || decimal.endsWith("0")) {
            integerSb.append(decimalSb).append(WHOLE);
            return integerSb.toString();
        }

        if (integerSb.length() > 0 && decimal.startsWith("0")) {
            integerSb.append(ZERO).append(decimalSb);
            return integerSb.toString();
        }

        return integerSb.append(decimalSb).toString();
    }

    /**
     * 金额整数部分转换大写
     *
     * @param integer 金额整数
     * @return 大写整数金额
     */
    private static StringBuilder readInteger(String integer) {
        StringBuilder sb = new StringBuilder();
        int length = integer.length();
        int b = length % 4, a = length / 4 + (b == 0 ? 0 : 1);
        int idx1 = 0, idx2 = (b == 0 ? 4 : b);

        for (int i = 0; i < a; i++) {
            boolean zero = false;
            for (int j = idx1; j < idx2; j++) {
                int num = integer.charAt(j) - 48;
                if (num == 0) {
                    zero = true;
                    continue;
                }
                if (zero) {
                    sb.append(ZERO);
                    zero = false;
                }
                sb.append(NUMBER[num - 1]).append(NUMBER_UNIT_1[4 - (idx2 - j)]);
            }
            sb.append(NUMBER_UNIT_2[4 - (a - i)]);
            idx1 = idx2;
            idx2 = idx2 + 4;
        }
        return sb.length() > 0 ? sb.append(CURRENCY_UNIT[0]) : sb;
    }

    /**
     * 金额小数部分转换大写
     *
     * @param decimal 金额小数
     * @return 大写小数金额
     */
    private static StringBuilder readDecimal(String decimal) {
        StringBuilder sb = new StringBuilder();
        int num1 = decimal.charAt(0) - 48, num2 = decimal.charAt(1) - 48;
        if (num1 != 0) sb.append(NUMBER[num1 - 1]).append(CURRENCY_UNIT[1]);
        if (num2 != 0) sb.append(NUMBER[num2 - 1]).append(CURRENCY_UNIT[2]);
        return sb;
    }

}
