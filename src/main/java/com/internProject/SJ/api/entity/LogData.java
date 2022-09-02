package com.internProject.SJ.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="logdata", schema = "public")
@RequiredArgsConstructor
public class LogData {
	@Id
	@GeneratedValue
	private Long Id;
	private String httpEvntNm; // HTTP이벤트명
	private String urlAddrNm; // URL주소명
	private String mthEvntNm; // METHOD이벤트명
	private String dertIpNm; // 출발IP명
	private String dertPortNm; // 출발포트명
	private String dertPamtNm; // 출발파라미터명
	private String reqStDtm; // 요청시작일시
	private String arivlIpNm; // 도착IP명
	private String arivlPortNm; // 도착포트명
	private String reqRsltVal; // 요청결과값
	private String reqEndDtm; //요청종료일시
}
