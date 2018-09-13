package com.mmeg.glyphes.optimizer.config;

import com.mmeg.glyphes.optimizer.config.restConfig.consommes.MmegDbService;
import com.mmeg.glyphes.optimizer.config.restConfig.consommes.TimedMmegDbService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import static com.mmeg.glyphes.optimizer.config.cxf.RestClientBuilder.ofJsonType;
import static com.mmeg.glyphes.optimizer.config.cxf.RestClientConfigBuilder.aRestClient;

@Import({CXFConfiguration.class})
public class ServicesConsommesConfiguration {


	private static final Logger LOGGER = LogManager.getLogger(ServicesConsommesConfiguration.class);

	@Bean
	public MmegDbService getPrrService(@Value("${http.mmegdb.url}") final String serviceUrl,
									   @Value("${http.mmegdb.readtimeout}") final int readTimeoutInSeconds,
									   @Value("${http.mmegdb.log.activate}") final boolean activateLog) {
		MmegDbService prrRestService = aRestClient(ofJsonType(MmegDbService.class, serviceUrl)
				.threadSafe())
				.withReadTimeout(readTimeoutInSeconds)
				.withLog(activateLog)
				.build();

		return new TimedMmegDbService(prrRestService);
	}

}
