package jsonArrayParsing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

public class Parsing {

    public static void main(String[] args) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        jsonObject = (JSONObject) jsonParser.parse("{\n" +
                "   \"policyNumber\":\"A1234567\",\n" +
                "   \"policyStatusCode\":\"22\",\n" +
                "   \"policyStatus\":\"active\",\n" +
                "   \"fundResults\":[\n" +
                "      {\n" +
                "         \"fundDetails\":[\n" +
                "            {\n" +
                "               \"fundName\":\"fund1\",\n" +
                "               \"fundDescription\":\"description1\",\n" +
                "               \"fundCode\":\"var1\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"unitBalance\":\"1000.00\",\n" +
                "         \"unitValue\":\"25.00\",\n" +
                "         \"fundBalance\":\"25000.00\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"fundDetails\":[\n" +
                "            {\n" +
                "               \"fundName\":\"fund2\",\n" +
                "               \"fundDescription\":\"description2\",\n" +
                "               \"fundCode\":\"var2\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"unitBalance\":\"5000.00\",\n" +
                "         \"unitValue\":\"2.00\",\n" +
                "         \"fundBalance\":\"10000.00\"\n" +
                "      }\n" +
                "   ]\n" +
                "}");


        jsonArrayParsing.Parsing parsing = new jsonArrayParsing.Parsing();
        ResultObject resultObject = parsing.getFundByFundCode(jsonObject, "var1");
        System.out.println();

    }

    private ResultObject getFundByFundCode(JSONObject jsonObject, String searchFundCode) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("fundResults");
        if (jsonArray == null){
            return null;
        }

        HashMap<String, ResultObject> resultObjectHashMap = new HashMap<>();
        for (int i = 0; i <jsonArray.size(); i++) {
            JSONObject fundResutItem = (JSONObject) jsonArray.get(i);
            String unitBalance = (String) fundResutItem.get("unitBalance");
            String fundBalance = (String) fundResutItem.get("fundBalance");
            String unitValue = (String) fundResutItem.get("unitValue");
            JSONArray fundDetailsArray = (JSONArray) fundResutItem.get("fundDetails");
            if (fundDetailsArray == null){
                continue;
            }
            for (int j = 0; j < fundDetailsArray.size(); j++) {
                JSONObject fundDetailsItem = (JSONObject) fundDetailsArray.get(j);
                String fundCode = (String) fundDetailsItem.get("fundCode");
                String fundName = (String) fundDetailsItem.get("fundName");
                String fundDescription = (String) fundDetailsItem.get("fundDescription");
                ResultObject resultObject = new ResultObject(fundCode, fundName, fundDescription, unitBalance, fundBalance, unitValue);
                resultObjectHashMap.put(fundCode, resultObject);

            }
        }
        return resultObjectHashMap.get(searchFundCode);
    }

    class ResultObject{
        private String fundCode;
        private String fundName;
        private String fundDescription;
        private String unitBalance;
        private String fundBalance;
        private String unitValue;


        public ResultObject(String fundCode, String fundName, String fundDescription, String unitBalance, String fundBalance, String unitValue) {
            this.fundCode = fundCode;
            this.fundName = fundName;
            this.fundDescription = fundDescription;
            this.unitBalance = unitBalance;
            this.fundBalance = fundBalance;
            this.unitValue = unitValue;
        }

        public String getFundCode() {
            return fundCode;
        }

        public void setFundCode(String fundCode) {
            this.fundCode = fundCode;
        }

        public String getFundName() {
            return fundName;
        }

        public void setFundName(String fundName) {
            this.fundName = fundName;
        }

        public String getFundDescription() {
            return fundDescription;
        }

        public void setFundDescription(String fundDescription) {
            this.fundDescription = fundDescription;
        }

        public String getUnitBalance() {
            return unitBalance;
        }

        public void setUnitBalance(String unitBalance) {
            this.unitBalance = unitBalance;
        }

        public String getFundBalance() {
            return fundBalance;
        }

        public void setFundBalance(String fundBalance) {
            this.fundBalance = fundBalance;
        }

        public String getUnitValue() {
            return unitValue;
        }

        public void setUnitValue(String unitValue) {
            this.unitValue = unitValue;
        }
    }
}
