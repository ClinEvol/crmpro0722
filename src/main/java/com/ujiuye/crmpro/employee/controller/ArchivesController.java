package com.ujiuye.crmpro.employee.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.common.utils.UUIDUtil;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.employee.pojo.Archives;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.ArchivesService;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("archives")
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;
    @Autowired
    private EmployeeService employeeService;

    //列表页
    @RequestMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "type",required = false,defaultValue = "0") int type,
            @RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
            @RequestParam(value = "size",required = false,defaultValue = "5") int size
    ){
        //设置分页
        PageHelper.startPage(pageNum,size);
        //查询
        List<Archives> list=archivesService.list(type,keyword);
        //创建分页对象
        PageInfo<Archives> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-archives";
    }

    //跳转到采集页面
    @RequestMapping("/tocollect")
    public String toCollect (){
        return "save-archivesforfile";
    }

    //实现采集
    @RequestMapping(value = "collect",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String collect (@RequestParam("myfiles") MultipartFile[] multipartFiles){
        List<File> files = MyFileUtils.upload(multipartFiles, null);
        List<String> names=new ArrayList<>();//存保添加失败的名字

        if(files==null || files.size()==0){
            return "file";
        }
        //获取上传的文件对象
        File  file=files.get(0);
        //读取Excel表中的数据到集合中
        try {
            List<Archives> list = ExcelToList(file, names);
            //全部失败
            if(list==null || list.size()==0){
                return "notAll";
            }
            int result = archivesService.save(list);
            if(result>0){
                if(names.size()==0){//没有添加失败的
                    return "true";
                }else{//部分添加成功
                    return names.toString();
                }
            }
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    /**
     *
     * @param file excel文件
     * @param names  存保添加失败的名字
     * @return 返回的是能保存的信息
     * @throws IOException
     */
    private List<Archives> ExcelToList(File  file,List<String> names) throws IOException {
        List<Archives> list=new ArrayList<>();
        //创建工作簿
        HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(file));
        //获取第一个表
        HSSFSheet sheet = workbook.getSheetAt(0);
        //获取总行数   有数据的行数
        int rows = sheet.getPhysicalNumberOfRows();
        //循环读取每一行的数据
        for(int i=1;i<rows;i++){//除去表头所以i从1开始
            Row row = sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            //通过名字判断是否存在该员工
            Employee employee = employeeService.getByName(name);
            if(employee==null){
                names.add(name);
                continue;
            }
            String no= UUIDUtil.getUUID();
            int empFk=employee.getEid();
            String telephone = row.getCell(1).getStringCellValue();
            String school = row.getCell(2).getStringCellValue();
            String major = row.getCell(3).getStringCellValue();
            String contact = row.getCell(4).getStringCellValue();
            Date graduate = row.getCell(5).getDateCellValue();
            String politics = row.getCell(6).getStringCellValue();
            String nation = row.getCell(7).getStringCellValue();
            String education = row.getCell(8).getStringCellValue();
            String email = row.getCell(9).getStringCellValue();
            String remark = row.getCell(10).getStringCellValue();
            Date hiredate = row.getCell(11).getDateCellValue();
            list.add(new Archives(no,telephone,school,major,contact,graduate,politics,nation,education,email,empFk,remark,hiredate));
        }
        return list;
    }
}
