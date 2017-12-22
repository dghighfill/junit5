package com.jug.junit5.sm.ex5;

import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContainerTestExecutionCallback implements BeforeTestExecutionCallback {

	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		log.info(context.getDisplayName() );
		log.info(context.getUniqueId() );
		log.info(context.getRequiredTestClass().getName());
		log.info(context.getRequiredTestMethod().getName() );
		log.info(context.getTags().toString());
	}

}
