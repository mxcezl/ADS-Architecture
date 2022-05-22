package com.ads.wsupdatedelete.loglake;

import java.util.Date;

import org.springframework.boot.logging.LogLevel;

import com.ads.wsupdatedelete.postgres.logdb.PostgresLogUtils;

public class LoggerLake {

	private static final String MODULE = "WS - Update & Delete";
	
	public static void log(LogLevel level, String message) throws Exception {
		
		message = message.replace("'", " ");
				
		String query = "INSERT INTO public.logs(log_date, module, log_message, log_level) VALUES ('?1', '?2', '?3', '?4');";
		
		query = query.replace("?1", new Date().toString());
		query = query.replace("?2", MODULE);
		query = query.replace("?3", message);
		query = query.replace("?4", level.toString());

		PostgresLogUtils.executeQuery(query);
	}
}
