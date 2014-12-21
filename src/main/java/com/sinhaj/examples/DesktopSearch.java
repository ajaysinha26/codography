package com.sinhaj.examples;


import java.io.File;
import java.util.concurrent.*;

/**
 * Created by ajaysinha on 1/25/14.
 */
public class DesktopSearch {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        File root = new File("/Users/ajaysinha/Downloads");
        BlockingQueue<File> fileQueue = new LinkedBlockingDeque<File>(100);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> crawlerFuture = executorService.submit(new FileCrawler(root, fileQueue));
        FileIndexer indexer = new FileIndexer(fileQueue);
        Future<?> indexFuture = executorService.submit(indexer);
        crawlerFuture.get();
        indexFuture.cancel(true);
        indexer.stop();
        executorService.shutdown();
    }

    static class FileCrawler implements Runnable {

        private File root;
        private BlockingQueue<File> fileQueue;

        FileCrawler(File root, BlockingQueue<File> fileQueue) {
            this.root = root;
            this.fileQueue = fileQueue;
        }

        @Override
        public void run() {
            crawl(root);
            System.out.println(Thread.currentThread().getName() + "Done crawling");
        }

        private void crawl(File root) {
            File[] files = root.listFiles();
            if(files != null) {
                for(File file : files) {
                    if(file != null) {
                        if(file.isDirectory()) {
                            crawl(file);
                        }
                        else {
                            try {
                                fileQueue.put(root);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + " putting in queue for indexing: " + file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }

    static class FileIndexer implements Runnable {

        private BlockingQueue<File> fileQueue;
        private volatile boolean runMe;

        FileIndexer(BlockingQueue<File> fileQueue) {
            this.fileQueue = fileQueue;
            runMe = true;
        }

        @Override
        public void run() {
            while (runMe) {
                try {
                    File file = fileQueue.take();
                    System.out.println(Thread.currentThread().getName() + " Indexed file " + file.getAbsolutePath());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        public void stop() {
            runMe = false;
        }
    }
}
