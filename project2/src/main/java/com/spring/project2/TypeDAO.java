package com.spring.project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component  //싱글톤으로 생성해내서 언제든 불러올 수 있게 만들기 위한 것 . 유저의 조작장치
public class TypeDAO {
	
	
		@Autowired 
		SqlSessionTemplate my; //SqlSessionTemplate는 mybatis의 핵심!
		//SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다. 
		//SqlSessionTemplate 은 쓰레드에 안전하고 여러개의 DAO나 매퍼에서 공유할수 있다.
		
		public void insert(TypeVO typeVO) throws Exception {
		      //myBatis에 시길 커예요.
		      my.insert("idealtype.insert", typeVO); //SqlSessionTemplate사용!
		      //mapper파일의 namespace.id에 등록된 sql문을 을 사용한다.
		}	
		public TypeVO select(TypeVO typeVO) throws Exception {
			System.out.println(typeVO);//데이터 잘 가져왔는지 확인(vo에서 toString)
			return my.selectOne("idealtype.select", typeVO);//mapper파일의 namespace.id에 등록된 sql문을 을 사용한다. 
					//VO정보를 입력하고 그 중 한명의 회원의 정보만 가지고 온다.
			/*
			 * ResultSet rs = ps.executeQuery();
			 * 
			 * MemberVO bag = new MemberVO(); if (rs.next()) {
			 * 
			 * String id2 = rs.getString("id"	); String pw = rs.getString(2); String name =
			 * rs.getString(3); String company = rs.getString(4); String tel =
			 * rs.getString(5); String birth = rs.getString(6); String email =
			 * rs.getString(7); String card = rs.getString(8); String cardnum =
			 * rs.getString(9); bag.setId(id2); bag.setPw(pw); bag.setName(name);
			 * bag.setCompany(company); bag.setTel(tel); bag.setBirth(birth);
			 * bag.setEmail(email); bag.setCard(card); bag.setCardnum(cardnum); } return
			 * bag; 이런식의 코드는 mybatis에서 어떤식으로 이루어지는가?
			 */	
		}
		public TypeVO select2(String tid) throws Exception {
			TypeVO tvo = my.selectOne("idealtype.select2", tid);
			return tvo;        //parameterType이 String이고 resultType이 String인 id=select2 사용 !
			
		}
		public void update(TypeVO typeVO) throws Exception {
			my.update("idealtype.update", typeVO);  //parameterType VO를 바탕으로 데이터 update!
		}
		
		
			
}
