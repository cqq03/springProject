package com.spring.project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //MemberDAO dao = new MemberDAO(); 싱글톤 생성!
public class Member2DAO {
   
   //myBatis싱글톤 주소를 주입!!
   @Autowired
   SqlSessionTemplate my;
	
   	   public List<Member2VO> select1(String mid) throws Exception{
   		   return my.selectList("member2.select1", mid); 
   		//parameterType이 String이고 resultType이 vo인 select1의 sql문을을 사용해 List를 찾고 반환!
   	   }
   	   public List<Member2VO> select2(char gender) throws Exception {
   		   return my.selectOne("member2.select2", gender);
   	   }//parameterType이 char이고 resultType이 vo가 담긴 List인 select2의 sql문을을 사용해 List 반환!
   	   public Member2VO select3(String mid) throws Exception {
   		   Member2VO vo = my.selectOne("member2.select3", mid);
   		   return vo;
   	   }
   	   public Member2VO select4(String mid) {
   		   return my.selectOne("member2.select1", mid);
   	   }//parameterType이 String이고 resultType이 vo인 select1의 sql문을을 사용해 한 회원의 정보만 찾고 반환!
}


