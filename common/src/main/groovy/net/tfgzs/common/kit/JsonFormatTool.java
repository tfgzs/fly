package net.tfgzs.common.kit;

import java.util.ArrayList;
import java.util.List;

public class JsonFormatTool {
//    public static void main(String[] args) {
//        String jsonFormat = JsonFormatTool.formatJson("{\"merchantname\":\"太原市万柏林区新车洁仕汽车美容装潢部\",\"ShopLicense\":\"140109660075761\",\"shopLicenseExp\":\"2014-08-14\",\"ShopAddress\":\"万柏林区旧晋祠路南屯村门面房\",\"Legalname\":\"刘彦斌\",\"AccountName\":\"刘彦斌\",\"Bank\":\"邮政\",\"Account\":\"6210981600009170225\",\"BankName\":\"太原义井支行\",\"Finance\":\"张刘彦斌\",\"FinancePhone\":\"0351-6079929\",\"FinanceMail\":\"980895867@qq.com\",\"Saler\":\"黄超\",\"Lnvoice\":\"\",\"arriveType\":\"1\",\"CDD\":\"TY000029\",\"mid\":\"505556675380744\",\"area\":\"0351\",\"storeList\":[{\"storeName\":\"车洁仕汽车美容装潢部\",\"storeAddress\":\"万柏林区旧晋祠路南屯村门面房\",\"storeheader\":\"刘彦斌\",\"linkPhone\":\"0351-6079929\",\"wifiSN\":\"\",\"QR\":\"\",\"tid\":\"56603001\",\"productList\":[{\"pid\":\"231\",\"orgamt\":\"15\",\"actualamt\":\"18\"},{\"pid\":\"232\",\"orgamt\":\"18\",\"actualamt\":\"21\"},{\"pid\":\"230\",\"orgamt\":\"8\",\"actualamt\":\"8\"}]}]}");
//        System.out.println(jsonFormat);
//    }

    /**
     * json字符串的格式化
     *
     * @param json 需要格式的json串
     * @return
     */
    public static String formatJson(String json) {
        String fillStringUnit = "  ";
        if (json == null || json.trim().length() == 0) {
            return null;
        }

        int fixedLenth = 0;
        List<String> tokenList = new ArrayList<String>();
        {
            String jsonTemp = json;
            // 预读取
            while (jsonTemp.length() > 0) {
                String token = getToken(jsonTemp);
                jsonTemp = jsonTemp.substring(token.length());
                token = token.trim();
                tokenList.add(token);
            }
        }

        for (int i = 0; i < tokenList.size(); i++) {
            String token = tokenList.get(i);
            int length = token.getBytes().length;
            if (length > fixedLenth && i < tokenList.size() - 1
                    && tokenList.get(i + 1).equals(":")) {
                fixedLenth = length;
            }
        }

        StringBuilder buf = new StringBuilder();
        int count = 0;
        for (int i = 0; i < tokenList.size(); i++) {

            String token = tokenList.get(i);

            if (token.equals(",")) {
                buf.append(token);
                doFill(buf, count, fillStringUnit);
                continue;
            }
            if (token.equals(":")) {
                buf.append(" ").append(token).append(" ");
                continue;
            }
            if (token.equals("{")) {
                String nextToken = tokenList.get(i + 1);
                if (nextToken.equals("}")) {
                    i++;
                    buf.append("{ }");
                } else {
                    count++;
                    buf.append(token);
                    doFill(buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("}")) {
                count--;
                doFill(buf, count, fillStringUnit);
                buf.append(token);
                continue;
            }
            if (token.equals("[")) {
                String nextToken = tokenList.get(i + 1);
                if (nextToken.equals("]")) {
                    i++;
                    buf.append("[ ]");
                } else {
                    count++;
                    buf.append(token);
                    doFill(buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("]")) {
                count--;
                doFill(buf, count, fillStringUnit);
                buf.append(token);
                continue;
            }

            buf.append(token);
            // 左对齐
            if (i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) {
                int fillLength = fixedLenth - token.getBytes().length;
                if (fillLength > 0) {
                    for (int j = 0; j < fillLength; j++) {
                        buf.append(" ");
                    }
                }
            }
        }
        return buf.toString();
    }

    private static String getToken(String json) {
        StringBuilder buf = new StringBuilder();
        boolean isInYinHao = false;
        while (json.length() > 0) {
            String token = json.substring(0, 1);
            json = json.substring(1);

            if (!isInYinHao
                    && (token.equals(":") || token.equals("{")
                    || token.equals("}") || token.equals("[")
                    || token.equals("]") || token.equals(","))) {
                if (buf.toString().trim().length() == 0) {
                    buf.append(token);
                }

                break;
            }

            if (token.equals("\\")) {
                buf.append(token);
                buf.append(json.substring(0, 1));
                json = json.substring(1);
                continue;
            }
            if (token.equals("\"")) {
                buf.append(token);
                if (isInYinHao) {
                    break;
                } else {
                    isInYinHao = true;
                    continue;
                }
            }
            buf.append(token);
        }
        return buf.toString();
    }

    private static void doFill(StringBuilder buf, int count,
                               String fillStringUnit) {
        buf.append("\n");
        for (int i = 0; i < count; i++) {
            buf.append(fillStringUnit);
        }
    }

}
