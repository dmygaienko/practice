package com.mygaienko.common.concurrency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *           List<CompletableFuture<Double>> relevanceFutures = topSites.stream().
             map(site -> CompletableFuture.supplyAsync(() -> downloadSite(site), executor)).
             map(contentFuture -> contentFuture.thenApply(this::parse)).
             map(docFuture -> docFuture.thenCompose(this::calculateRelevance)).
             collect(Collectors.<CompletableFuture<Double>>toList());
 *
 * Created by dmygaenko on 18/01/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CompletableFutureTest {

    @Mock
    private AsyncRestTemplate asyncRestTemplate;

    public void test() {
        ListenableFuture<ResponseEntity<Response>> future1 = asyncRestTemplate.postForEntity("url1", null, Response.class);
        ListenableFuture<ResponseEntity<Response>> future2 = asyncRestTemplate.postForEntity("url2", null, Response.class);

        CompletableFuture<ResponseEntity<Response>> uCompletableFuture1 = CompletableFuture.supplyAsync(getSupplier(future1));
        CompletableFuture<ResponseEntity<Response>> uCompletableFuture2 = CompletableFuture.supplyAsync(getSupplier(future2));

        uCompletableFuture1.thenApply(
                (responseEntity) -> {
                    System.out.println("complete");
                    return responseEntity.getBody();
                }
        );

        CompletableFuture<Void> allOf = CompletableFuture.allOf(uCompletableFuture1, uCompletableFuture2);
    }

    @Test
    public void testStaticSupplier() throws Exception {
        CompletableFuture<String> firstFuture = CompletableFuture.supplyAsync(getDelayedSupplier("test1"));
        CompletableFuture<String> secondFuture = CompletableFuture.supplyAsync(getDelayedSupplier("test2"));
        CompletableFuture<String> thirdFuture = CompletableFuture.completedFuture("test3");

        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(firstFuture, secondFuture, thirdFuture);
        assertThat(anyFuture.get(), is("test3"));
    }

    @Test
    public void testChainOfTransformations() throws Exception {
        CompletableFuture<Integer> fi = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<String> finalFuture = fi
                .thenApply(Integer::valueOf)
                .thenApply(str -> "final value: " + str);

        assertThat(finalFuture.get(), is("final value: 2"));
    }

    private Supplier<String> getDelayedSupplier(String result) {
        return () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        };
    }

    private <U> Supplier<ResponseEntity<U>> getSupplier(ListenableFuture<ResponseEntity<U>> future) {
        return () -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
             throw new RuntimeException();
            }
        };
    }


    public static class Response {
        private String content;

        public Response(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
