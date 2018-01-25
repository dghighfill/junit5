package com.jug.junit5.sm.ex5;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IgnoreContainerNotFoundException implements TestExecutionExceptionHandler {

	@Override
	public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		if( throwable instanceof ContainerNotFoundException) {
			// Eat the exception and just return
			log.info("Discarding ContainerNotFoundException - " + throwable.getMessage() );
			return;
		}
	}
}
