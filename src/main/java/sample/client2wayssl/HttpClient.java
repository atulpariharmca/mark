package sample.client2wayssl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@SpringBootApplication
public class HttpClient implements CommandLineRunner {
	

	/**
	 * @args 0[keystore], 1[keystorePassword], 2[uri], 3[requestJson]
	 */
	@Override
	public void run(String... args) throws Exception {
		if (args.length < 4) {
			System.out.println("Invalid args, you have to set[0[keystore], 1[keystorePassword], 2[uri], 3[requestJson]]");
			System.exit(0);
		}
		
		String keystore =  args[0];
		System.out.println("[0] cert : ".concat(keystore));
		
		String keystorePassword =  args[1];
		System.out.println("[1] keystorePassword : ".concat(keystorePassword));
		
		String uri =  args[2];
		System.out.println("[2] uri : ".concat(uri));
		
		String requestJson =  args[3];
		System.out.println("[3] requestJson : ".concat(requestJson));
		
		System.setProperty("javax.net.ssl.trustStore", keystore);
		System.setProperty("javax.net.ssl.keyStore", keystore);
		System.setProperty("javax.net.ssl.trustStorePassword", keystorePassword);
		System.setProperty("javax.net.ssl.keyStorePassword", keystorePassword);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		String response = restTemplate.postForObject(uri, new HttpEntity<String>(requestJson, headers), String.class);
		System.out.println("*result:\n".concat(response));
	}
}
