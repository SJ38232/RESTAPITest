package com.internProject.SJ.api.config;

import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.io.*;
import com.internProject.SJ.api.entity.LogData;
import com.internProject.SJ.api.service.LogDataService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@RequiredArgsConstructor
public class LogInterceptor implements HandlerInterceptor{
	private LogData logData;
	private final LogDataService logDataServiceImpl;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception{
		if(request instanceof RequestWrapper) {
			logData = new LogData();
			Timestamp timestamp= new Timestamp(new Date().getTime());
			logData.setHttpEvntNm(request.getProtocol());
			logData.setUrlAddrNm(request.getRequestURI().toString());
			logData.setMthEvntNm(request.getMethod());
			logData.setDertIpNm(Inet4Address.getLocalHost().getHostAddress());
			logData.setDertPortNm(String.valueOf(request.getLocalPort()));	
			logData.setDertPamtNm(IOUtils.toString(request.getInputStream(), request.getCharacterEncoding()));
			if(logData.getDertPamtNm().equals("")) {
				Enumeration<String> params = request.getParameterNames();
				String strParam = "{";
				while(params.hasMoreElements()) {
					String name = (String)params.nextElement();
					String value = request.getParameter(name);
					strParam += "\"" + name + "\"" + ":\"" + value + "\",";
					}
				strParam = strParam.substring(0, strParam.length()-1);
				strParam += "}";
				logData.setDertPamtNm(strParam);
			}
			logData.setReqStDtm(timestamp.toString());
			
		}
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object Handler, ModelAndView modelAndView) throws Exception{
		if(response instanceof ResponseWrapper) {
			Timestamp timestamp= new Timestamp(new Date().getTime());
			logData.setArivlIpNm(getIp(request.getRequestURI().toString()));
			logData.setArivlPortNm(getIp(request.getRequestURI().toString()));
			logData.setReqEndDtm(timestamp.toString());
			logData.setReqRsltVal(IOUtils.toString(((ResponseWrapper)response).getContentInputStream(), request.getCharacterEncoding()));
			log.info(logData.getHttpEvntNm()+ " "+
			logData.getUrlAddrNm()+ " "+
			logData.getMthEvntNm()+ " "+
			logData.getDertIpNm()+ " "+
			logData.getDertPortNm()+ " "+
			logData.getDertPamtNm()+ " "+
			logData.getReqStDtm()+ " "+
			logData.getArivlIpNm()+ " "+
			logData.getArivlPortNm()+ " "+
			logData.getReqEndDtm()+ " "+
			logData.getReqRsltVal());
			logDataServiceImpl.saveLog(logData);
		}
	}
	private static String getIp(String pStrUri) {
		String ip = "";
		try {
			URI uri = new URI(pStrUri);
			ip = uri.getHost();
			InetAddress inetAddress	= InetAddress.getByName(ip);
			ip = inetAddress.getHostAddress();
		} catch (URISyntaxException e) {
			ip = pStrUri;
		} catch (UnknownHostException e) {
			
		}
		return ip;
	}
	
}
