package com.internProject.SJ.api.service;

import org.springframework.stereotype.Service;

import com.internProject.SJ.api.entity.LogData;
import com.internProject.SJ.api.repository.LogDataRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LogDataServiceImpl implements LogDataService{
	private final LogDataRepository logDataRepository;
	@Override
	public void saveLog(LogData logData) {
		logDataRepository.save(logData);
	}

}
