package com.xiaoshu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Bumen;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.BumenService;
import com.xiaoshu.service.EmpService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;
import com.xiaoshu.vo.EmpVo;

@Controller
@RequestMapping("emp")
public class EmpController extends LogController{
	static Logger logger = Logger.getLogger(EmpController.class);

	@Autowired
	private EmpService empService;
	
	@Autowired
	private BumenService bumenService ;
	
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("empIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Bumen> roleList = bumenService.findAll();
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		request.setAttribute("roleList", roleList);
		return "emp";
	}
	
	
	@RequestMapping(value="empList",method=RequestMethod.POST)
	public void userList(Emp emp,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<EmpVo> userList= empService.findAll(emp,pageNum,pageSize);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",userList.getTotal() );
			jsonObj.put("rows", userList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户展示错误",e);
			throw e;
		}
	}
	
	
	// 新增或修改
	@RequestMapping("reserveEmp")
	public void reserveUser(Emp emp,HttpServletRequest request,User user,HttpServletResponse response){
		Integer eid = emp.getEid();
		JSONObject result=new JSONObject();
		try {
			if (eid != null) {   // userId不为空 说明是修改
					emp.setEid(eid);
					empService.updateEmp(emp);
					result.put("success", true);
				}else {   // 添加
					empService.addEmp(emp);
					result.put("success", true);
				}
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存用户信息错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	@RequestMapping("deleteEmp")
	public void delUser(Emp emp,HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] ids=request.getParameter("ids").split(",");
			for (String id : ids) {
				empService.deleteEmp(Integer.parseInt(id));
			}
			result.put("success", true);
			result.put("delNums", ids.length);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	

@RequestMapping("out")
public void outStudent(HttpServletResponse response) {
	JSONObject result = new JSONObject();
	try {
		// 导出到指定的excel文件中
		// WorkBook
		HSSFWorkbook wb = new HSSFWorkbook();
		// sheet
		HSSFSheet sheet = wb.createSheet();
		// row 标题行
		HSSFRow row0 = sheet.createRow(0);
		String[] title = { "编号", "姓名", "性别", "年龄", "生日", "专业" };
		for (int i = 0; i < title.length; i++) {
			row0.createCell(i).setCellValue(title[i]);
		}

		List<EmpVo> list = empService.find();

		// 将数据库中的数据，导出到excel中
		for (int i = 0; i < list.size(); i++) {
			EmpVo emp = list.get(i);
			HSSFRow row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(emp.getEid());
			row.createCell(1).setCellValue(emp.getEname());
			row.createCell(2).setCellValue(emp.getSex());
			row.createCell(3).setCellValue(emp.getAge());
			// // 将Date类型，转换成String类型
			// SimpleDateFormat simpleDateFormat = new
			// SimpleDateFormat("yyyy-MM-dd");
			// String format =
			// simpleDateFormat.format(student.getBirthday());
			//

			row.createCell(4).setCellValue(emp.getBirthday());
			row.createCell(5).setCellValue(emp.getBname());

		}

		// 回显
		OutputStream out = new FileOutputStream(new File("E://gaoligei.xls"));

		wb.write(out);

		wb.close();
		out.close();

		result.put("success", true);
	} catch (Exception e) {
		e.printStackTrace();
		result.put("errorMsg", "对不起，删除失败");
	}
	WriterUtil.write(response, result.toString());
}

@RequestMapping("in")
public void inStudent(Emp emp,HttpServletResponse response, MultipartFile file) {

	JSONObject result = new JSONObject();
	try {
		// 创建WorkBook ，通过WorBook对象，获取上传的文件的内容
		HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
		// 读取sheet对象
		HSSFSheet sheet = wb.getSheetAt(0);
		// 读取行
		int lastRowNum = sheet.getLastRowNum();

		for (int i = 1; i <= lastRowNum ; i++) {

			HSSFRow row = sheet.getRow(i);
			Emp s = new Emp();
			// 姓名读取到了
			String ename = row.getCell(1).getStringCellValue();
			s.setEname(ename);;

			String sex = row.getCell(2).getStringCellValue();
			s.setSex(sex);
			
			String age = row.getCell(3).getStringCellValue();
			s.setAge(age);
			
			// 需要将生日，从String转会Date
			String str = row.getCell(4).getStringCellValue();
			s.setBirthday(str);

			String maname = row.getCell(5).getStringCellValue();
			Bumen bumen =bumenService.findID(maname);
			s.setBid(bumen.getBid());

			// 插入到数据库中
			empService.addEmp(s);

		}
		wb.close();
		result.put("success", true);
	} catch (Exception e) {
		e.printStackTrace();
		result.put("success", true);
		result.put("errorMsg", "对不起，操作失败");
	}
	WriterUtil.write(response, result.toString());
}

@RequestMapping("EchartsDevice")
public void EchartsDevice(HttpServletRequest request,HttpServletResponse response){
	JSONObject result=new JSONObject();
	try {
		List<EmpVo> list = empService.echartsDevice();
		result.put("success", true);
		result.put("data", list);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error("删除用户信息错误",e);
		result.put("errorMsg", "对不起，删除失败");
	}
	WriterUtil.write(response, result.toString());
}
}
