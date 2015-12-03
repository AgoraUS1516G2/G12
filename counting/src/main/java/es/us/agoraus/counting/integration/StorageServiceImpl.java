package es.us.agoraus.counting.integration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import es.us.agoraus.counting.domain.VotosCifrados;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Component
public class StorageServiceImpl {

	@Value("${storage.base.url}")
	private String storageBaseUrl;

	private StorageService service;

	@Autowired
	private SyncCallExecutor syncCallExec;

	@PostConstruct
	public void init() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(storageBaseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		service = retrofit.create(StorageService.class);
	}

	public VotosCifrados getVotesForPoll(String pollId) {
		final Call<VotosCifrados> call = getService().getVotesForPoll(pollId);
		return syncCallExec.exec(call);
	}

	public StorageService getService() {
		return service;
	}

}
