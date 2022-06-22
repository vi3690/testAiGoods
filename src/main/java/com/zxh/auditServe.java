package main.java.com.zxh;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class auditServe {
    /**
     * 清单开票时的excel解析
     *
     * @param diskId
     * @param type
     * @param withTax        当前状态是否含税
     * @param file
     * @param authentication
     * @return
     * @throws IOException
     */
    public List<Aigood> parseGoodsList(String diskId, String type, boolean withTax, MultipartFile file, Authentication authentication) throws IOException {
        if (file == null) {
            throw new RuntimeException("未接收到有效文件");
        }
        String fileType = this.getExcelType(file);
        if (fileType == null) {
            throw new RuntimeException("只支持导入xls格式与xlsx格式的文件");
        }

        Workbook wb;
        if ("xls".equals(fileType.toLowerCase())) {
            wb = new HSSFWorkbook(file.getInputStream());
        } else {
            wb = new XSSFWorkbook(file.getInputStream());
        }
        Sheet sheet = wb.getSheetAt(0);

        // 检测excel格式是否合法
//        if (!isValidGoodsList(sheet)) {
//            throw new RuntimeException("请使用对应的官方模板导入数据");
//        }
        List<GoodImport> goodImports = new ArrayList<>();

        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        lastRowNum = Math.min(lastRowNum, 2001);
        List<AiGood> aiGoodsList = null;
        for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
            List<AiGood> aiGoods = null;
            Row row = sheet.getRow(i);
            if (isEmptyRow(row)) {
                continue;
            }
            aiGoods = aiCoding2(getStringCellValue(row, 1), 0);
            aiGoodsList.add(aiGoods.get(0));
        }
        return aiGoodsList;
    }
}
