package com.interview.Interview.Interview2024.Arcesium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
Multithreading question to create a web crawler. The WC should not create more than 100 threads at a time,
It should only use max 5 threads per host and use some synchronization to calculate and store the max and
total words in the web page.

 */
public class WebCrawler {

    private final int MAX_TOTAL_THREADS = 100;
    private final int MAX_THREAD_PER_HOST = 5;

    //for managing threads, responsible for handling the creation, scheduling, and lifecycle of threads.
    // In this context, it's used to submit the crawling tasks asynchronously.
    private final ExecutorService executorService;
    private final Semaphore hostSemaphore;

    // host-specific semaphores are used to limit the number of threads per host to 5
    private final Map<String, Semaphore> hostSemaphoresMap;

    //synchronization lock used to guard critical section of code
    //in java, synchronization is often required when we are working with shared resources
    //synchronize access to shared resources
    private final Object lock = new Object();

    public WebCrawler() {
        executorService = Executors.newFixedThreadPool(MAX_TOTAL_THREADS);
        hostSemaphore = new Semaphore(MAX_THREAD_PER_HOST, true);
        hostSemaphoresMap = new HashMap<>();
    }

    public void crawl (String url) {
        executorService.submit(() -> {
            try {
                URL targetUrl = new URL(url);
                String host = targetUrl.getHost();

                //acquirePermitForhost
                acquirePermitForHost(host);

                //logic of crawling

                //releasePermitForHost
                releasePermitForHost(host);
                
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void acquirePermitForHost(String host) {
        hostSemaphoresMap.putIfAbsent(host, new Semaphore(MAX_THREAD_PER_HOST, true));

        try {
            hostSemaphore.acquire();
            hostSemaphoresMap.get(host).acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void releasePermitForHost(String host) {
        hostSemaphoresMap.get(host).release();
        hostSemaphore.release();
    }

    public void shutdown() {
        executorService.shutdown();
    }

    // Other methods for word counting and synchronization can be added as needed

    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler();

        // Example usage
        webCrawler.crawl("https://example.com/page1");
        webCrawler.crawl("https://example.com/page2");

        // Shutdown the crawler when done
        webCrawler.shutdown();
    }
}
