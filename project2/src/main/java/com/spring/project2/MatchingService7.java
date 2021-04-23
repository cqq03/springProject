package com.spring.project2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component //싱글톤
public class MatchingService7 {
	@Autowired
	TypeDAO tdao;  //싱글톤호출
	@Autowired	
	Member2DAO mdao;	//싱글톤
	@Autowired
	MatchDAO mcdao;	//싱글톤
	Double point = 0.0;  //매칭점수를 준비해둔다.
	public List<MatchVO> list(String mid) throws Exception {
		
		//밑에 연산에서 사용할 회원들의 정보만 미리 모아놓고 리스트로 반환해 밑에서 연산을 시작한다. 
		 
		MatchVO mvo = mcdao.select1(mid);	//matchs테이블에서 id를 입력해 그 아이디가 있는 VO를 전부 가져와 mvo에 넣는다.
		
		System.out.println(mvo.getMbti()); //mvo로 데이터 잘 넘어왔는지 확인
		List<MatchVO> list = new ArrayList<MatchVO>();
		List<MatchVO> list2 = new ArrayList<MatchVO>();
		//한 mbti에 최고궁합인 mbti는 보통 2개이므로 list 2개를 준비해 넣을 준비를 한다/.  
		
		if ((mvo.getMbti().equals("infp"))) {//회원의 mbti가 infp일때 조건문을 실행한다. 다른 mbti의 경우 한 메서드에 들어가는 코드 수를 줄이기 위해
			  //메서드를 분할하여 작성한다.
			
				list = mcdao.select2("enfj");
		       //최고궁합중 하나를 갖고 있는 회원들의 정보를 list에 넣는다.
				list2 = mcdao.select2("entj");
				//최고궁합중 다른 하나를 갖고 있는 회원들의 정보를 다른 list에 넣는다.
		} else if ((mvo.getMbti().equals("enfp"))) {//다른 조건일때도 빼먹지 않게 해준다. mbti의 경우 전체 궁합의 수는 14*2 + 2*3 (2유형은 각각 3유형과 최고궁합이다.) 
			
				list = mcdao.select2("infj");
			
				list2 = mcdao.select2("intj");
				
		} else if ((mvo.getMbti().equals("infj"))) {
			
				list = mcdao.select2("enfp");
			
				list2 = mcdao.select2("entp");
				
		}else if ((mvo.getMbti().equals("enfj"))) {
			
				list = mcdao.select2("infp");
			
				list2 = mcdao.select2("isfp");
				
		} else if ((mvo.getMbti().equals("intj"))) {
				list = mcdao.select2("enfp");
				list2 = mcdao.select2("entp");
		} else if ((mvo.getMbti().equals("entj"))) {
			
				list = mcdao.select2("infp");
			
				list2 = mcdao.select2("intp");
			 	
		} else if ((mvo.getMbti().equals("intp"))) {
			
				list = mcdao.select2("enttj");
			
				list2 = mcdao.select2("estj");
			 	
		}else if ((mvo.getMbti().equals("entp"))) {
			
				list = mcdao.select2("infj");
			
				list2 = mcdao.select2("intj");
			 	
		} else if ((mvo.getMbti().equals("isfp"))) {
				list = mcdao.select2("enfj");
				list2 = mcdao.select2("esfj");
				List<MatchVO> list3 = new ArrayList<MatchVO>(); //isfp같은 경우 최고궁합이 3개이므로 추가로 
				list3 = mcdao.select2("estj");      //list를 하나 더 준비해 넣어주고 list2에 list3모두를 추가해준다.
				list2.addAll(list3);
		}else if ((mvo.getMbti().equals("esfp"))) {
			
				list = mcdao.select2("isfj");
		
				list2 = mcdao.select2("istj");
				
		} else if ((mvo.getMbti().equals("istp"))) {
			
				list = mcdao.select2("esfj");
			
				list2 = mcdao.select2("estj");
			 	
		}else if ((mvo.getMbti().equals("estp"))) {
			
				list = mcdao.select2("isfj");
			
				list2 = mcdao.select2("istj");
			 	
		} else if ((mvo.getMbti().equals("isfj"))) {
			
				list = mcdao.select2("esfp");
		
				list2 = mcdao.select2("estp");
			 	
		}else if ((mvo.getMbti().equals("esfj"))) {
			
				list = mcdao.select2("isfp");
		
				list2 = mcdao.select2("istp");
			 	
		} else if ((mvo.getMbti().equals("istj"))) {
			
				list = mcdao.select2("esfp");
			
				list2 = mcdao.select2("estp");
			
		} else if ((mvo.getMbti().equals("esfj"))) {
		
				list = mcdao.select2("intp");
			
				list2 = mcdao.select2("isfp");
				List<MatchVO> list3 = new ArrayList<MatchVO>();
				list3 = mcdao.select2("istp");
				list2.addAll(list3);
			
		}
		list.addAll(list2);//list2개를 합쳐주고
		return list;//반환한다.
		
	}
	public HashMap<String, Double> match(String mid) throws Exception{
		HashMap<String, Double> map = new HashMap<String, Double>(); //반환받을 해쉬맵을 생성한다.
		Member2VO vo = mdao.select4(mid);  //member2테이블에서 id를 입력해 그 아이디가 있는 VO를 전부 가져와 vo에 넣는다. 
		MatchVO mvo = mcdao.select1(mid);	//matchs테이블에서 id를 입력해 그 아이디가 있는 VO를 전부 가져와 mvo에 넣는다.
		System.out.println(vo.getGender()); //vo 데이터 잘 넘어왔는지 확인
		System.out.println(mvo.getMbti()); //mvo로 데이터 잘 넘어왔는지 확인
		String tid = mid;          //idealType테이블의 경우 id의 칼럼명이 mid가 아닌 tid로 지정되어 있기에  tid로 지정한다.
		TypeVO tvo = tdao.select2(tid); // idealType테이블에서 id를 입력해 그 아이디가 있는 VO를 전부 가져와 tvo에 넣는다.
		System.out.println(tvo.getHeight());//데이터 잘 넘어왔는지 확인.
		
		List<MatchVO> list = list(mid);//list메서드에서 반환한 list(id가 들어있다)를 가져와 연산을 시작한다. 
			//mbti를 입력하고 그것에 맞는 vo를 list에 추가하는 방식으로 mapper와 dao를 구성한다(selectList)
		for (int i = 0; i < list.size(); i++) { //mbti가 entj인 회원들의 데이터를 순서대로 가져오기 위해 for문 사용
			System.out.println(list.get(i));//제대로 가져왔는지 확인
			Member2VO vo2 = mdao.select3(list.get(i).getMid()); //member테이블에서 id를 입력해 id가 있는 vo를 찾아오는 sql문 사용
			//>dao호출(selectOne)
			//for문이 돌아갈때마다 다른 회원의 vo를 호출
			System.out.println(tvo);
			System.out.println(vo2);//vo 잘 가져왔는지 확인
			System.out.println("나의 성 : " + vo.getGender() + " ,너의 성:" + vo2.getGender()); //성별 비교 전에 잘 가져왔는지 확인
			if (vo.getGender() == vo2.getGender()) { 
				point = 0.0;   //두 회원의 성별이 같으면 매칭점수를 0으로 한다. >>매칭 안 되게
				System.out.println("성별 같을 떄: " + point);
			} else { //두 회원의 성별이 다를 때 위의 mbti로 인한 점수를 바탕으로 감점이나 가산점을 주는 알고리즘 부여
				point = 90.0; // mbti궁합이 좋고, 서로의 성별이 다를 때 점수를 90점 부여한다.
				if (tvo.getDrinking() != 6) { //6은 상대방의 음주성향 상관없음이므로 감점x/ 6이 아닐때 감점 가능
					int a = Math.abs(tvo.getDrinking() - list.get(i).getDrink()); //내가 원하는 성향과 상대방의 성향을 비교한다.
					point = point - 3*a;  //Math.abs()함수로 위의 차이를 절대값으로 가져오고 그것에 비례해서 감점
				}
				System.out.println("성별 다르고 음주 연산: " + point); 
				if (tvo.getSmoking() == 3) { //상대방이 흡연하든 말든 상관 없을 경우 값이 3이고, 이  경우 감점x

				}else if (tvo.getSmoking() != list.get(i).getSmoke()) {
					point = point - 10; // 흡연여부가 상관없지 않고, 상대방의 흡연여부와 내가 원하는 바가 다를 경우 10점 감점  
				}
				System.out.println("흡연 연산: " +point);
				if(tvo.getReligion() == 5) {  //상대방이 어떤 종교를 갖든  상관 없을 경우 값이 5이고, 이  경우 감점x

				}else if (tvo.getReligion() != list.get(i).getBelieve()) {
					point = point - 5; // 상대방의 종교와 내 이상형 정보가 다를 때 감점 
				}
				System.out.println("종교연산: " + point);
				if(list.get(i).getTall() >= 185) { //상대방의 키가 185 이상일떄는
					if (tvo.getHeight() == 185) { // 나의 이상형 키가 185이상일때(값 185)만 가산점 
						point = point + 3.01; //소수점은 우선순위 가산점 같은 +3가산점 중에서도 .01이 하, .1이 상 중복을 최대한 피하기 위한 가산점 나중에 캐슬링해줄 것
					}
				}else if (list.get(i).getTall() >= 180) {  //상대방의 키가 180~185일때 
					if (tvo.getHeight() == 180) {     //나의 이상형 키가 180~185(값 180)만 가산점
						point = point + 3.01;
					}
				}else if (list.get(i).getTall() >= 175) { //상대방의 키가 175~180일때 
					if (tvo.getHeight() == 175) {		//나의 이상형 키가 175~180(값 175)만 가산점
						point = point + 3.01;
					}
				}else if (list.get(i).getTall() >= 170) {  //위와 같은 구조
					if (tvo.getHeight() == 170) {
						point = point + 3.01;
					}
				}else if (list.get(i).getTall() >= 165) {
					if (tvo.getHeight() == 165) {
						point = point + 3.01;
					}
				}else if (list.get(i).getTall() >= 160) {
					if (tvo.getHeight() == 160) {
						point = point + 3.01;
					}
				}else if (list.get(i).getTall() >= 150) {  
					if (tvo.getHeight() == 150) {
						point = point + 3.01;
					}
				}else {									//상대방의 키가 150이 안될 때
					if (tvo.getHeight() == 145) {      //내가 원하는 키가 150 이하일때만 가산점
						point = point + 3.01;
					}
				}
				System.out.println("키연산: " + point);
				System.out.println(tvo.getPlace());
				System.out.println(list.get(i).getPlace());
				System.out.println(tvo.getPlace() == list.get(i).getPlace());
				if (tvo.getPlace() == list.get(i).getPlace()) {  //나의 선호 데이트 장소와 상대방의 선호 데이트 장소가 같을 때 가산점
					point = point + 2.2;  // 2점 가산점 주는 부분 중 추가 가산을 소수점으로 표현 .2가 상/ .02가 하

				}
				System.out.println("선호 장소 연산: " + point);
				if (tvo.getContact() == list.get(i).getContact()) {//나의 선호 연락 방식과 상대방의 선호 연락 방식이 같을 때 가산점
					point = point + 2.02;
				}
				System.out.println("선호 연락방식 연산: " + point);
				if(tvo.getWage() == 1) { //내가 원하는 사람이 연상일때  내가 태어난 연도가 상대방이 태어난 연도보다  클때만 가산점
					if (vo.getBirthdate() > vo2.getBirthdate()) {
						point = point + 2.2; 
					}
				}else if (tvo.getWage() == 2) {  //내가 원하는 사람이 연하일때  내가 태어난 연도가 상대방이 태어난 연도보다  작을때만 가산점
					if (vo.getBirthdate() < vo2.getBirthdate()) {
						point = point + 2.2;
					}
				}else { //내가 원하는 사람이 동갑일때  내가 태어난 연도가 상대방이 태어난 연도랑 같을 때만 가산점
					if (vo.getBirthdate() == vo2.getBirthdate()) {
						point = point + 3.1;
					}
				}
				System.out.println("나이궁합연산: " + point);
			}//성별
			System.out.println("결과 연산: " + point);
			if(point != 0) {
			map.put(list.get(i).getMid(), point);//위에서 계산한 점수와 그 점수가 나오는 상대의 아이디를 HashMap에 넣는다.
			}
		}//for
	//myMbti
	return map; // 반환값은 위에서 준비한 HashMap 변수
	//메서드	
	
	}
	public HashMap<String, Double> RecN (String mid) throws Exception { //위에서 나눈 메서드를 호출하기 한다음 추가 작업하는 메서드를 따로 만들어 사용한다. 
		
		MatchVO mvo = mcdao.select1(mid);	//matchs테이블에서 id를 입력해 그 아이디가 있는 VO를 전부 가져와 mvo에 넣는다.
		HashMap<String, Double> map1 = new HashMap<String, Double>();   //나의 유형에 맞는 mbti를 지닌 유저를 db에서 읽어와
																		//위의 메서드에서 조건에 따른 가산점 연산하고 map에 넣기!
		HashMap<String, Double> map2 = new HashMap<String, Double>();
	
		map1 = match(mid);  //list메서드와 match메서드를 거쳐 나온 결과를 map1에 넣어준다.
		
	
		System.out.println("서비스 메서드1 결과: " + map1);//확인용
	
		ArrayList<Double> list = new ArrayList<>(); //점수를 순서대로 정렬할 ArrayList준비
		
		list.addAll(map1.values());            //list에 map의 value값(점수, double타입)을 모두 넣어준다.
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println("리스트: " + list.get(i)); //잘 들어갔나 확인
		}
		Collections.sort(list); //오름차순으로 리스트 안을 정렬한다.
		for (int i = 0; i < list.size(); i++) {    
			System.out.println("리스트2: " + list.get(i)); //잘 정렬되었는지 확인
		}	
		int listSize = list.size();     //밑에서 자주 사용할 list.size()(정수값)을 listSize변수에 넣는다.
		if(listSize >= 3) {//list에 들어온 map.value의 갯수가 3개 이상이면 뒤에서 3개만 뽑아오기 위해 if문 작성한다. 
			System.out.println("1:" + list.get(listSize - 1) + ", 2:" + list.get(listSize - 2));//재대로 있는지 확인
			System.out.println(list.get(listSize - 1) == list.get(listSize - 2));//조건 출력되는지 확인
			System.out.println(list.get(listSize - 1).compareTo(list.get(listSize - 2)) == 0);//조건 출력되는지 확인
			if (list.get(listSize - 1).compareTo(list.get(listSize - 2)) == 0) {
			//double값의 경우 (큰값 - 작은)값 ==0 을 사용할 수 없다. 
			//컴퓨터는 	숫자를 2진수로 표현하는데, 정수부분은 문제없이 인식하지만  
			//소수점 아래 부분은 무한반복되어 2진수로 표현하지 못할경우가 생긴다. 	
			//예를 들어0.3 => 0.01001100110011......(0011)의 무한 반복. 이때 어쩔 수 없이 근사치가 저장되어 인간의 인식과 다른 수가 저장된다.
			
				//이때 정수의 비트수와 소수의 비트 수(범위, 크기)를 미리 정해놓고 하는 고정소수점을 사용하면 
			//263.3같은 경우  263.3을 표현하면 (0)0000000100000111.010011001100110으로 표현할 경우
				// 맨 앞 1자리는  0이면 양수, 1이면 음수라는 뜻.
					//나머지 비트들은 소수점을 기준으로 정수부랑 소수부를 표현하는 비트로 각각 나누게 되는데, 소수점의 위치는 미리 정해둔다.
					//소수부의 경우 앞에서부터 채우며 남는 뒷자리는 다 0으로 채운다.
					//이런식으로 할 경우 비트 수가 적으면 정수는 큰수를 표현못하고 소수는 뒤에 0밑의 자리를 다 읽지 못해 정밀성이 떨어지고
					//비트수가 커지면 반대의 경우 발생
					//이런점을 위해 IEEE에서 표준으로 제안한	 부동소수점 방식은 비트수 대비 지수의 크기가 정밀성, 큰수 양면에서 모두  잘 표현할 수 있어
					//소수점 표현방식에서 자주 기용된다.
				
				//list에 들어온 map.value의 갯수가 3개 이상이면 뒤에서 3개만 뽑아오기 위해 if문 작성한다. 
				//compareTo함수는 비교대상이 동일한 값일 때 : 0, 비교 대상이 작은 경우 : -1	, 비교 대상이 큰 경우 : 1 리턴	
				map1.replace(getKey(map1, list.get(listSize - 2)), list.get(listSize - 1) - 0.001);
				//getKey는 밑의 메서드에서 정의한 해당 value에 있는 키를 찾는 메서드 ! 이 경우 String값이다. 중복인 경우에는 둘 중 하나만 고정적으로 가져오기에 
				//키 하나의 value를 아예 바꾸고 다른 키의 value를 바꾸어 짝을 맞게하는 전략을 실행.
				//예를 들어 ["a"=10, "b"=10] 의 경우 getKey메서드는 value가 10인 key를 찾으면 b를 버리고 a를 찾는다. 그렇다면 
				//["a"=10, "b"=10.1] 이런식으로 바꾸어주고 value값만 전에 따로 빼온 list<Double>의 두번째 값을 10.1로 바꾸어 10.1로 b도 찾을 수 있게 하는 전략
				//동일점수 간에 차별을 두기 위해 가산점을 할건데 그것을 하기전에 해당 값에 해당 하는 키값을 미리 바꾸어준다.
				//바꾸어주는 키 값은 밑의 value값으로 찾을 수 있는 키와 동일하게 한다.map.replace(키, 앞의 키의 value를 어떻게 바꿀거야?) 를 가능하게 하는 함수
				// 코드가 위에서 아래로 실행되기에 가능한 방법.
				list.set(listSize - 2, list.get(listSize - 1) - 0.001);
				//key값을 바꾸어주었으면 value값도 바꾸어준다.
				if (list.get(listSize - 1).compareTo(list.get(listSize - 3)) == 0) { //큰 value가 3개가 존재하는 경우에 위에 같은 방식으로 뒤에서 3번째
					//것만 가져와 키값을 바꾸고 value를 위와 좀 다른 수 (이 경우 위는 -0.001, 아래는 -0.002) 로 바꾸어 찾을 수 있게 한다.
					map1.replace(getKey(map1, list.get(listSize - 3)), list.get(listSize - 1)- 0.002);
					list.set(listSize - 3, list.get(listSize - 1)- 0.002);
					
				}
			}else if(list.get(listSize - 2).compareTo(list.get(listSize - 3)) == 0) {//뒤에서 1,2는 같지 않은데 2,3이 같을 경우도 같은 문제가 발생하기에
				//뒤에서 2,3번째에 한정해 위와 같은 전략 수행
				map1.replace(getKey(map1, list.get(listSize - 3)), list.get(listSize - 1) - 0.02);
				list.set(listSize - 3, list.get(listSize - 1)- 0.02);
			}
			
			System.out.println("1번: " + list.get(listSize - 1) + ", 2번: " + list.get(listSize - 2) + ", 3번: " + list.get(listSize - 3));
			String no1 = getKey(map1, list.get(listSize - 1));  //스트링 변수에 위에서 설정해놓은 value들을 가진 키값을 찾아 넣어준다   
			String no2 = getKey(map1, list.get(listSize - 2));	//뒤에서 1,2번째 value값이 같거나 2,3번째가 같거나 1,3이 같을때(1,3이 같을때는 당연히 2,3은 같으므로
																//위의 if절을 모두 거쳐 나온다.)
			String no3 = getKey(map1, list.get(listSize - 3));	//위의 if절을 거쳐나온 value을 통해 key를 찾는다. 
			System.out.println(no1 + "," + no2 + "," + no3);
			map2.put(no1, list.get(listSize - 1));//전에 새롭게 만든 텅빈 map에 본래 있던 [String=Dobule]과 같은 대응을 할 수 있게 설정하여 map에 넣는다. 
			map2.put(no2, list.get(listSize - 2)); //뒤에서 3개만 넣어주는 이유는 가장 높은 점수의 사람 3명만 controller로 데려올거기 때문
			map2.put(no3, list.get(listSize - 3));
	
			} else if (list.size() == 2) { //사이즈가 2일경우, 즉 mbti궁합이 맞는 사람이 데이터에 2명밖에 없는 경우는 무조건 오류가 난다.
										//jsp의 ajax 부분에서 error부분을 설정해 에러가 나면 매칭회원을 찾을 수 없습니다라고 메시지를 뜨게한다.
										//이 경우 앞의 1~2명은 가져오기 위해 list.get(0~1)이 가능하게 코드를 설정한다.
				System.out.println("1:" + list.get(listSize - 1) + ", 2:" + list.get(listSize - 2));
				System.out.println(list.get(listSize - 1) == list.get(listSize - 2));
				System.out.println(list.get(listSize - 1).compareTo(list.get(listSize - 2)) == 0);
				if (list.get(listSize - 1).compareTo(list.get(listSize - 2)) == 0) { //위와 동일한 방식
					//double은 == 연산자 안됨 ! .compareTo(다른 double값) == 0으로 비교해야함!!!!! 
					//compareTo함수는 비교대상이 동일한 값일 때 : 0, 비교 대상이 작은 경우 : -1	, 비교 대상이 큰 경우 : 1 리턴	
					map1.replace(getKey(map1, list.get(listSize - 2)), list.get(listSize - 1) - 0.001);
					list.set(listSize - 2, list.get(listSize - 1) - 0.001);
				
				}
				
				System.out.println("1번: " + list.get(listSize - 1) + ", 2번: " + list.get(listSize - 2));
				String no1 = getKey(map1, list.get(listSize - 1)); //위와 동일한 방식이지만 2명밖에 없으므로 2개를 넣어준다.
				String no2 = getKey(map1, list.get(listSize - 2));//2명만 데려오기 위해 2개
				
				System.out.println(no1 + "," + no2);
				map2.put(no1, list.get(listSize - 1));
				map2.put(no2, list.get(listSize - 2));
				
			} else if (list.size() == 1){//위와 동일한 방식 , 1명만 데려오기 위해 1개
				System.out.println("1:" + list.get(listSize - 1));
				System.out.println("1번: " + list.get(listSize - 1));
				String no1 = getKey(map1, list.get(listSize - 1));
				System.out.println(no1);
				map2.put(no1, list.get(listSize - 1));
				
			}//굳이 else를 할 필요 없이 컨트롤러에서 0명일 경우 에러가 뜨게 만든다. 거기서 에러가 뜰 경우 매칭가능한 회원이 없다고 출력
		return map2;

	}
	public static String getKey(HashMap<String, Double> m, double value) {
						//해당 value가 들어있는 map을 찾아 key값을 하나 가져오는 메서드
					
		for(String o: m.keySet()) { 
			//반복해서 준비한 double과 value와 key에 value르 같을 경우 o를 출력 
			if(m.get(o) == value) { 

				return o; 
			} 
		}

		return null;//없을 경우 null!
	}


	
}
