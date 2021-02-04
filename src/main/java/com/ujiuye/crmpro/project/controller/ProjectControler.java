package com.ujiuye.crmpro.project.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.Attachment;
import com.ujiuye.crmpro.project.pojo.Project;
import com.ujiuye.crmpro.project.service.AnalysisService;
import com.ujiuye.crmpro.project.service.AttachmentService;
import com.ujiuye.crmpro.project.service.ProjectService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/project")
@Controller
public class ProjectControler {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private AttachmentService attchmentService;

    //列表页
    @RequestMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "type",required = false,defaultValue = "0") int type,
            @RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
            @RequestParam(value = "size",required = false,defaultValue = "10") int size
    ){
        //设置分页
        PageHelper.startPage(pageNum,size);
        //查询
        List<Project> list=projectService.list(type,keyword);
        //创建分页对象
        PageInfo<Project> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-project";
    }


    @RequestMapping("/tosave")
    public String tosave(){
        return "save-project";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Project project){
        try {
            int result=projectService.save(project);
            if (result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public String removeById(int id){
        try {
            //判断是否有关系数据
            Analysis analysis = analysisService.getById(id);
            if(analysis!=null){
                return "file";
            }
            List<Attachment> attachments = attchmentService.listByProFk(id);
            if(attachments!=null && attachments.size()>0){
                return "file";
            }
            int result=projectService.removeById(id);
            if(result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

    }
    //批量
    @RequestMapping("/remove")
    @ResponseBody
    public String remove(Integer[] ids){

        //这里存在三种情况     1、全部删除    2、全部失败    3、部分删除
        List<Integer> noDel=new ArrayList<>();//用于保存不能删除的id
        List<Integer> del=new ArrayList<>();//用于保存删除的id
        //判断组数组中每一个id是否存在关联数据
        for(int i=0;i<ids.length;i++){
            int id=ids[i];
            //判断是否有关系数据
            Analysis analysis = analysisService.getById(id);
            if(analysis!=null){
                noDel.add(id);
                continue;
            }
            List<Attachment> attachments = attchmentService.listByProFk(id);
            if(attachments!=null && attachments.size()>0){
                noDel.add(id);
                continue;
            }
            del.add(id);
        }
        if(del.size()==0){//全部失败
            return "notAll";
        }

        try {
            int result=projectService.remove(del);
            if(result>0){
                if(noDel.size()>0){//部分删除
                    return noDel.toString();//把不能删除的id返回给前台
                }
                return "true";//全部成功
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }


    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(Project project){
        try {
            int result=projectService.update(project);
            if (result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    //跳转到修改页
    @RequestMapping("/toupdate")
    public String toUpdate(Model model,int id){
        Project project = projectService.getById(id);
        model.addAttribute("project",project);
        return "update-project";
    }

    @RequestMapping("/listJSON")
    @ResponseBody
    public List<Project> listJSON(){
        return projectService.list(0, null);
    }


    //导出表格
    @RequestMapping("/downloadExcel")
    public ResponseEntity<byte[]> downloadExcel(){
        //1、获取数据
        List<Project> list = projectService.list(0, null);
        //2、把集中的数据写到exel中
        File file = ListToExcel(list);
        //3、下载excel
        return MyFileUtils.download(file.getName(),null);
    }

    //把集中的数据写到exel中
    private File ListToExcel(List<Project> list){
        //创建一个工作簿
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建一个表格
        XSSFSheet sheet = workbook.createSheet("项目数据");
        //创建一行
        XSSFRow rowHeader = sheet.createRow(0);
        //设置第一行的单元格
        rowHeader.createCell(0).setCellValue("项目名称");
        rowHeader.createCell(1).setCellValue("客户公司名称");
        rowHeader.createCell(2).setCellValue("客户方负责人");
        rowHeader.createCell(3).setCellValue("项目经理");
        rowHeader.createCell(4).setCellValue("开发人员数");
        rowHeader.createCell(5).setCellValue("立项时间");
        rowHeader.createCell(6).setCellValue("开始时间");
        rowHeader.createCell(7).setCellValue("计划完成时间");
        //遍历集合
        for (int i=0;i<list.size();i++){
            Project project = list.get(i);
            XSSFRow row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(project.getName());
            row.createCell(1).setCellValue(project.getCustomer().getComname());
            row.createCell(2).setCellValue(project.getCompanyperson());
            row.createCell(3).setCellValue(project.getEmployee().getEname());
            row.createCell(4).setCellValue(project.getEmpcount());
            SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
            row.createCell(5).setCellValue(format.format(project.getBuildtime()));
            row.createCell(6).setCellValue(format.format(project.getStarttime()));
            row.createCell(7).setCellValue(format.format(project.getEndtime()));
        }

        //创建一个文件对象
        File file=new File("/Users/ClinEvol/Workspaces/static/项目数据.xls");
        //把workbook的中数据写到file文件对象中去
        try {
            workbook.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //把文件对象返回用于下载
        return file;
    }

}
