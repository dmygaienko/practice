package async_rest_template;

import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRequestCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by enda1n on 16.09.2016.
 */
public class AsyncRestTemplateTest {

    @Test
    public void test() {
        AsyncRestTemplate template = new AsyncRestTemplate();
        //create request entity using HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> requestEntity = new HttpEntity<String>("params", headers);
        ListenableFuture<ResponseEntity<String>> future =
                template.exchange("http://google.com", HttpMethod.GET, requestEntity, String.class);
        waitForResponse(future);

    }

    @Test
    public void testWithResponseExtractor() {
        AsyncRestTemplate template = new AsyncRestTemplate();
        //create request entity using HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        AsyncRequestCallback requestCallback = new AsyncRequestCallback (){
            @Override
            public void doWithRequest(AsyncClientHttpRequest arg0)
                    throws IOException {
                System.out.println(arg0.getURI());
            }
        };

        ResponseExtractor<String> responseExtractor = new ResponseExtractor<String>(){
            @Override
            public String extractData(ClientHttpResponse arg0)
                    throws IOException {
                return "\nStatus: " + arg0.getStatusText() + " \n" +
                        "Headers: " + arg0.getHeaders() + " \n" +
                        "Body: " + arg0.getBody();
            }
        };
        Map<String,String> urlVariable = new HashMap<String, String>();
        urlVariable.put("q", "Concretepage");
        ListenableFuture<String> future = template.execute(
                "http://google.com", HttpMethod.GET, requestCallback, responseExtractor, urlVariable);
        try {
            //waits for the result
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testWithResponseCallback() {
        AsyncRestTemplate template = new AsyncRestTemplate();
        //create request entity using HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> requestEntity = new HttpEntity<String>("params", headers);
        ListenableFuture<ResponseEntity<String>> future = template.exchange(
                "http://google.com", HttpMethod.GET, requestEntity, String.class);

        future.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
                    @Override
                    public void onSuccess(ResponseEntity<String> result) {
                        System.out.println("\n onSuccess:\n");
                        System.out.println(result.getBody() + "\n");
                        // Need assertions
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        // Need assertions
                    }
                });
        waitForResponse(future);
    }

    private void waitForResponse(ListenableFuture<ResponseEntity<String>> future) {
        try {
            //waits for the result
            ResponseEntity<String> result = future.get();
            System.out.println(result.getBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
