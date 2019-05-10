package com.cn.demo.view.controller.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.demo.view.controller.BaseController;
@Controller
@RequestMapping("/api/v1/information/notice")
public class InformNoticeController extends BaseController{

	
	@RequestMapping(value = "/index/view")
	public String index_view() {
		
		return "infomation/notice/index";
	}
}
