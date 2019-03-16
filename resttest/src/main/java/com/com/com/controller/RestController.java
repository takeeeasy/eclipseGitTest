package com.com.com.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.com.com.vo.SampleVO;

@Controller
@RequestMapping("/rest")
public class RestController {

	
	@RequestMapping(value = "/index", method= RequestMethod.GET)
	public String index(@RequestParam Map<String, Object> map) throws Exception {
//		System.out.println(URLDecoder.decode(map.get("data").toString(), "UTF-8"));
		return "index";
	}
	
	@RequestMapping(value = "/index/{name}", method = RequestMethod.POST)
	public @ResponseBody String sayHello(@PathVariable String name) {
		System.out.println(name);
		return "hello";
	}
	
	@RequestMapping("/sendVO")    // JSON 형식으로 객체 SampleVO를 반환하는 "/sample/sendVO" 작성

	  public @ResponseBody SampleVO sendVO() {       // SampleVO를 반환하는 sendVO method 정의

		SampleVO vo = new SampleVO();     // SampleVO를 생성

		vo.setMno(123);                            // 데이터를 삽입
		
		vo.setFirstName("길동");

		vo.setLastName("홍");

		return vo;                                    // SampleVO 객체를 반환

	  }
	
	
	  @RequestMapping("/sendList")	    // List 형태의 데이터를 반환하는 예 (/sample/sendList)

	  public @ResponseBody List<SampleVO> sendList() {    // List<SampleVO> 형태의 리스트를 반환하는 method sendList

		List<SampleVO> list = new ArrayList<SampleVO>();	// List 생성

		for(int i = 0;i < 10;i++) {	   // 예로 10개의 SampleVO를 담는다.

			SampleVO vo = new SampleVO();	// SampleVO를 생성

			vo.setMno(i);				// 데이터를 담는다.

			vo.setFirstName("길동" + i);

			vo.setLastName("홍");

			list.add(vo);					// list에 객체를 추가한다.

		}

		return list;						// List 객체를 반환

	  }


	@RequestMapping("/map")
	public @ResponseBody Map<String, Object> rest()
	{
		System.out.println("in");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "victolee");
		map.put("pwd", 26);
		map.put("content", "hello~");
		return map;
	}
	
	@RequestMapping("model")
	public ModelAndView modelAndView(ModelAndView mv) throws Exception
	{
		// 데이터와 뷰를 동시에 설정이 가능
	    mv.setViewName("redirect:/rest/index"); // 뷰의 이름
	    mv.addObject("data", URLEncoder.encode("최과장", "UTF-8")); // 뷰로 보낼 데이터 값
	    
	    return mv;
	}
}
