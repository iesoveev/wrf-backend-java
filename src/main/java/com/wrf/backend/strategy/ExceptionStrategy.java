package com.wrf.backend.strategy;

import com.wrf.backend.GlobalExceptionHandler;
import com.wrf.backend.model.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExceptionStrategy {

    Logger LOG = LogManager.getLogger(GlobalExceptionHandler.class);

    Response process(Exception e, HttpServletResponse response);
}
