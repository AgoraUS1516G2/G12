package es.us.agoraus.counting.integration;

import org.springframework.stereotype.Component;

import retrofit.Call;

@Component
public class SyncCallExecutor {

	public <RESPONSE> RESPONSE exec(final Call<RESPONSE> call) {
		RESPONSE result = null;
		try {
			result = call.execute().body();
		} catch (Throwable oops) {
			
		}
		return result;
	}
	
}