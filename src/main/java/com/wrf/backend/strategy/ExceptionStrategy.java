package com.wrf.backend.strategy;

import com.wrf.backend.GlobalExceptionHandler;
import com.wrf.backend.model.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ExceptionStrategy {

    Logger LOG = LogManager.getLogger(GlobalExceptionHandler.class);

    Response process(Exception e);
}
