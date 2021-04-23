package com.spring.project2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller//컨트롤러 설정하고
public class MatchingController {
@Autowired //사용하기 위해 생성해둔 싱글톤 객체들을 불러온다. 
TypeDAO tdao;
@Autowired
Member2DAO mdao;
@Autowired
MatchDAO m2dao;
@Autowired
MatchingService7 ms;


	@RequestMapping("matching")//jsp에서 1번 버튼을 선택했을대
	public void matching(Model model, HttpSession session) throws Exception {
		String mid = (String)session.getAttribute("Mid"); //jsp에 있던 데이터인 세션의 아이디를 가져온다.
		System.out.println(mid);
		HashMap<String, Double> map = new HashMap<String, Double>(); //텅 빈 map을 만들고
		map = ms.RecN(mid);//service를 실행시키고 그에 대한 반환 값을 map에 넣는다.  
		System.out.println("컨트롤러에 map값:  " + map);
		ArrayList<Double> point = new ArrayList<Double>();//점수만 넣을 arrayList를 만들어 준비
		point.addAll(map.values());// map의 value를 모두 넣는다./
		Collections.sort(point);//오름차순 정렬
		if (point.size() == 3) {//3개 일경우  jsp에서 1번 누를 시 3번째(가장 높은 점수) 사람id를 가져와 select문으로 
		System.out.println(point.get(2));							//id에 해당하는 사람의 상세정보를 모두 가져와 model로 설정
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(ms.getKey(map, point.get(0))); //리스트에는 정렬된 point와 대응될 수 있게 순서대로 넣어준다.
		list.add(ms.getKey(map, point.get(1)));//ms.getKey는 String을 반환하는 메서드이다. 
		list.add(ms.getKey(map, point.get(2)));//스태틱방식으로 하려면 MatchingService7.getKey로 해야하지만
		System.out.println(list.get(2));     //ms가 짧으므로 그렇게 적는다.
		TypeVO tvo = tdao.select2(list.get(2));       //세 테이블의 (그 사람의 이상형정보, 나의 회원 정보, 조건정보)를 모두 갖고 오 위해 모델 3개 설정
		Member2VO mvo = mdao.select3(list.get(2));
		MatchVO mcvo = m2dao.select1(list.get(2));
		model.addAttribute("tvo",tvo);
		model.addAttribute("mvo",mvo);
		model.addAttribute("mcvo",mcvo);
		model.addAttribute("point1",point.get(2).intValue());//매칭점수의 경우 소수점 밑의 수를 빼는게 깔끔하므로
																//,어차피 앞에서 그건 중복문제를 해결하기 위한 것이었으므로 정수값만 가져온다.
	} else if (point.size() == 2) { //2개일 경우 1번을 선택하면 가장 높은 점수의 사람만(오름차순 2번째) 가져올 수 있게 설정 위와 같은방식
	System.out.println(point.get(1));//
	
	ArrayList<String> list = new ArrayList<String>();
	list.add(ms.getKey(map, point.get(0)));
	list.add(ms.getKey(map, point.get(1)));
	
	System.out.println(list.get(1));
	TypeVO tvo = tdao.select2(list.get(1));
	Member2VO mvo = mdao.select3(list.get(1));
	MatchVO mcvo = m2dao.select1(list.get(1));
	model.addAttribute("tvo",tvo);
	model.addAttribute("mvo",mvo);
	model.addAttribute("mcvo",mcvo);
	model.addAttribute("point1",point.get(1).intValue());
	} else if (point.size() == 1) {//한개일 경우 1번을 선택하면 그 사람의 데이터만 가져올 수 있게 설정
		System.out.println(point.get(0));
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.addAll(map.keySet());
		System.out.println(list.get(0));
		TypeVO tvo = tdao.select2(list.get(0));
		Member2VO mvo = mdao.select3(list.get(0));
		MatchVO mcvo = m2dao.select1(list.get(0));
		model.addAttribute("tvo",tvo);
		model.addAttribute("mvo",mvo);
		model.addAttribute("mcvo",mcvo);
		model.addAttribute("point1",point.get(0).intValue());
	} else {
		System.out.println(point.get(2)); //map이 텅 비어있을땐 무조건 오류뜨게!
	}
}
@RequestMapping("matching2")//2번 버튼 클릭했을 시 이 컨트롤러로!
public void matching2(Model model, HttpSession session) throws Exception {
	String mid = (String)session.getAttribute("Mid");
	System.out.println(mid);	
	HashMap<String, Double> map = new HashMap<String, Double>(); 
	map = ms.RecN(mid);
	System.out.println("컨트롤러에 map값:  " + map);
	
	ArrayList<Double> point = new ArrayList<Double>();
	
	point.addAll(map.values());
	Collections.sort(point);
	System.out.println(point);
		if (point.size() == 3) {  //위와 같은 방식이나 2번버튼은 2순위의 사람을 데려오므로  3명중 점수가 2번째로 높은사람 데려오게 설정
			System.out.println(point.get(1));
			ArrayList<String> list = new ArrayList<String>();
			
			list.add(ms.getKey(map, point.get(0)));
			list.add(ms.getKey(map, point.get(1)));
			list.add(ms.getKey(map, point.get(2)));
			System.out.println("1번: " + list.get(0) + "2번: " + list.get(1));	
			System.out.println(list.get(1));
			TypeVO tvo = tdao.select2(list.get(1));
			Member2VO mvo = mdao.select3(list.get(1));
			MatchVO mcvo = m2dao.select1(list.get(1));
			model.addAttribute("tvo",tvo);
			model.addAttribute("mvo",mvo);
			model.addAttribute("mcvo",mcvo);
			model.addAttribute("point2",point.get(1).intValue());
		}else if(point.size() == 2) {//위와 같은 방식이나 2번버튼은 2순위의 사람을 데려오므로  2명중 점수가 2번째로 높은사람 데려오게 설정
			System.out.println(point.get(0));
			ArrayList<String> list = new ArrayList<String>();
			
			list.add(ms.getKey(map, point.get(0)));
			list.add(ms.getKey(map, point.get(1)));
			System.out.println(list.get(0));
			TypeVO tvo = tdao.select2(list.get(0));
			Member2VO mvo = mdao.select3(list.get(0));
			MatchVO mcvo = m2dao.select1(list.get(0));
			model.addAttribute("tvo",tvo);
			model.addAttribute("mvo",mvo);
			model.addAttribute("mcvo",mcvo);
			model.addAttribute("point2",point.get(0).intValue());
		}	else if(point.size() == 1) {
			System.out.println(point.get(2));
			//map에 데이터가 한개 밖에 없을 경우 에러나게 2순위가 있다는게 말이 안되므로 에러나게
		}
		System.out.println(point.get(2));
	}     //map에 데이터가 0개 있을 경우 2순위가 있다는게 말이 안되므로 에러나게
@RequestMapping("matching3")
public void matching3(Model model, HttpSession session) throws Exception {
	String mid = (String)session.getAttribute("Mid");
	System.out.println(mid);
	HashMap<String, Double> map = new HashMap<String, Double>(); 
	map = ms.RecN(mid);
	System.out.println("컨트롤러에 map값:  " + map);
	ArrayList<Double> point = new ArrayList<Double>();
	
	point.addAll(map.values());
	Collections.sort(point);
	if (point.size() == 3) { //위와 같은 방식이나 3번버튼은 3순위의 사람을 데려오므로  3명중 점수가 가장 낮은사람(오름차순이므로 첫번째) 데려오게 설정
		System.out.println(point.get(0));
		ArrayList<String> list = new ArrayList<String>();
		
		list.add(ms.getKey(map, point.get(0)));
		list.add(ms.getKey(map, point.get(1)));
		list.add(ms.getKey(map, point.get(2)));
		System.out.println(list.get(0));
		TypeVO tvo = tdao.select2(list.get(0));
		Member2VO mvo = mdao.select3(list.get(0));
		MatchVO mcvo = m2dao.select1(list.get(0));
		model.addAttribute("tvo",tvo);
		model.addAttribute("mvo",mvo);
		model.addAttribute("mcvo",mcvo);
		model.addAttribute("point3",point.get(0).intValue());
	}else if(point.size() == 2) {
		System.out.println(point.get(2));//2명밖에 못데려왔을 경우  3순위는 당연 없으므로 에러로 3순위 없다고 표시할 수 있게
	
	}	else if(point.size() == 1) {//1명밖에 못데려왔을 경우  3순위는 당연 없으므로 에러로 3순위 없다고 표시할 수 있게
		System.out.println(point.get(2));
		
		}
	System.out.println(point.get(2));//0명밖에 못데려왔을 경우  3순위는 당연 없으므로 에러로 3순위 없다고 표시할 수 있게
	}
	
	
}
