package com.mmeg.glyphes.optimizer.config;

import com.mmeg.glyphes.optimizer.config.aspects.ApplicationModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class ContextKeeperDecorator implements TaskDecorator {

	private ApplicationModule applicationModule;
	private final Logger LOGGER = LogManager.getLogger("AsyncTechLogger");

	public ContextKeeperDecorator(final ApplicationModule anApplicationModule) {
		applicationModule = anApplicationModule;
	}

	@Override
	public Runnable decorate(Runnable runnable) {
		Map<String, String> contextMap = ThreadContext.getContext();
		return () -> {
			try {
				ThreadContext.putAll(contextMap);
				applicationModule.prepareExecute(LOGGER, "Async");
				runnable.run();
			} finally {
				applicationModule.completeExecute();
				ThreadContext.clearAll();
			}
		};
	}
}