package com.zsc.tbpractice.table;


import com.zsc.base.abs.BaseController;
import com.zsc.base.spring.JsonResult;
import com.zsc.base.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author ZhangSuchao
 * @create 2019/6/4
 * @since 1.0.0
 */

public class PoiController extends BaseController {

    /**
     * 导入老师信息
     *
     * @param file
     * @return
     */
    @RequestMapping("importExcel")
    public JsonResult importExcel(@RequestParam(value = "filename") MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
            //获取文件名
            String fileName = file.getOriginalFilename();

            //根据版本选择创建Workbook的方式
            Workbook wb = null;
            Sheet sheetAt = null;
            //根据文件名判断文件是2003版本还是2007版本
            if (ExcelUtils.isExcel2007(fileName)) {
                wb = new XSSFWorkbook(is);
                sheetAt = wb.getSheetAt(0);
            } else if (ExcelUtils.isExcel2003(fileName)) {
                wb = new HSSFWorkbook(is);
                sheetAt = wb.getSheetAt(0);
            } else {
                return sendError("请传Excel文件");
            }

            for (Row row : sheetAt) {
                int rowNum = row.getRowNum();
                if (rowNum == 0) {
                    continue;
                }

                Cell cell0 = row.getCell(0);
                cell0.setCellType(CellType.STRING);
                String teacherName = cell0.getStringCellValue();//用户名
                Cell cell1 = row.getCell(1);
                cell1.setCellType(CellType.STRING);
                String teacherPhone = cell1.getStringCellValue();//手机号
                Cell cell2 = row.getCell(2);
                cell2.setCellType(CellType.STRING);
                String teacherSex = cell2.getStringCellValue();//性别

                Cell cell3 = row.getCell(3);
                cell3.setCellType(CellType.STRING);
                String teacherAge = cell3.getStringCellValue();//年龄

                Cell cell4 = row.getCell(4);
                cell4.setCellType(CellType.STRING);
                String jobTitle = cell4.getStringCellValue();//教师职称
                Cell cell5 = row.getCell(5);
                cell5.setCellType(CellType.STRING);
                String jobOrg = cell5.getStringCellValue();//工作单位
                Cell cell6 = row.getCell(6);
                cell6.setCellType(CellType.STRING);
                String bankCard = cell6.getStringCellValue();//银行卡号
                Cell cell7 = row.getCell(7);
                cell7.setCellType(CellType.STRING);
                String IDCard = cell7.getStringCellValue();//身份证号

         /* 判断格式
         String qty = "0";
          switch (row.getCell(1).getCellType()) {
              case HSSFCell.CELL_TYPE_STRING:
                    qty = row.getCell(1).getRichStringCellValue().getString().trim();
                    break;
              case HSSFCell.CELL_TYPE_NUMERIC:
                    qty = nf.format(row.getCell(1).getNumericCellValue());
                    break;
              default:
                    qty = "";
                }
        */


                //判断是否重复用户名重复

                //FIXME 老师表的保存的唯一筛选条件
                //  TeacherDO teacherDO = teacherService.selectTeacherByTeacherXxx();

//                ReqTeacher reqTeacher = new ReqTeacher();
//                reqTeacher.setTeacherName(teacherName);
//                reqTeacher.setTeacherPhone(teacherPhone);
//                if ("男".equals(teacherSex)) {
//                    reqTeacher.setTeacherSex(Constants.SEX_MAN);
//                } else if ("女".equals(teacherSex)) {
//                    reqTeacher.setTeacherSex(Constants.SEX_WOMAN);
//                }
//                reqTeacher.setTeacherAge(Integer.parseInt(teacherAge));
//                reqTeacher.setJobTitle(jobTitle);
//                reqTeacher.setJobOrg(jobOrg);
//                reqTeacher.setBankCard(bankCard);
//                reqTeacher.setIdCard(IDCard);
//                reqTeacher.setIsEnable(1);
//                teacherService.insertTeacher(reqTeacher, adminSession);
//                return sendSuccess();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return sendError();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }

        return null;
    }


    /**
     * 条件导出老师信息
     *
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/exportExcel")
    public JsonResult exportExcel(HttpServletResponse response, Integer inOrgId) {


        ServletOutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            // 创建 excel 文件
            workbook = new HSSFWorkbook();
            // 创建一个标签页
            HSSFSheet sheet = workbook.createSheet("用户信息");

            //设置列宽
            sheet.setColumnWidth(0, 3000);
            sheet.setColumnWidth(1, 3500);
            sheet.setColumnWidth(2, 3000);
            sheet.setColumnWidth(3, 3000);
            sheet.setColumnWidth(4, 3000);
            sheet.setColumnWidth(5, 5500);
            sheet.setColumnWidth(6, 5000);
            sheet.setColumnWidth(7, 5000);

            //创建格式
            HSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

            HSSFFont titleFont = workbook.createFont();
            titleFont.setBold(true);//粗体
            titleFont.setFontHeightInPoints((short) 12);//大小
            titleFont.setFontName("宋体");//字体类型
            titleStyle.setFont(titleFont);


            // 创建标题行
            HSSFRow titleRow = sheet.createRow(0);

            HSSFCell cell0 = titleRow.createCell(0);
            cell0.setCellValue("用户名");
            cell0.setCellStyle(titleStyle);

            HSSFCell cell1 = titleRow.createCell(1);
            cell1.setCellValue("手机号");
            cell1.setCellStyle(titleStyle);

            HSSFCell cell2 = titleRow.createCell(2);
            cell2.setCellValue("性别");
            cell2.setCellStyle(titleStyle);

            HSSFCell cell3 = titleRow.createCell(3);
            cell3.setCellValue("年龄");
            cell3.setCellStyle(titleStyle);

            HSSFCell cell4 = titleRow.createCell(4);
            cell4.setCellValue("教师职称");
            cell4.setCellStyle(titleStyle);

            HSSFCell cell5 = titleRow.createCell(5);
            cell5.setCellValue("工作单位");
            cell5.setCellStyle(titleStyle);

            HSSFCell cell6 = titleRow.createCell(6);
            cell6.setCellValue("银行卡号");
            cell6.setCellStyle(titleStyle);

            HSSFCell cell7 = titleRow.createCell(7);
            cell7.setCellValue("身份证号");
            cell7.setCellStyle(titleStyle);

            //TODO 插入或更新数据


            // 设置两个头 一个输出流
            String filename = "老师表.xls";
            outputStream = response.getOutputStream();
            // 响应信息，弹出文件下载窗口
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (outputStream != null) {
                    workbook.close();
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        return null;
    }




}
