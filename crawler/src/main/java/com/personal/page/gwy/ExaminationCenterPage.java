package com.personal.page.gwy;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Data
@Component
public class ExaminationCenterPage {

    private static final String URL = "https://www.chinagwy.org/html/stzx/202301/7_557715.html";

    List<ExaminationInfoPage> examinationInfoPages;

    public String load() throws Exception {
        return Jsoup.connect(URL).execute().body();
    }

    public void analysis(String html) {
        Document doc = Jsoup.parse(html);
        Elements tbodys = doc.select("tbody");
        if (tbodys.isEmpty()) {
            return;
        }
        Element tbody = tbodys.get(0);

        List<ExaminationInfoPage> infoPages = new ArrayList<>();
        for (Element tr : tbody.select("tr")) {
            for (Element a : tr.select("a")) {
                if (a != null) {
                    infoPages.add(new ExaminationInfoPage(a.attr("href"), new ArrayList<>()));
                }
            }
        }
        examinationInfoPages = infoPages;
    }

    public static void main(String[] args) throws Exception {
        ExaminationCenterPage page = new ExaminationCenterPage();
        page.analysis(page.load());

        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 60000L, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(1000));
        for (ExaminationInfoPage p : page.examinationInfoPages) {
            pool.execute(() -> {
                try {
                    p.load();
                } catch (IOException e) {
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(1000L, TimeUnit.SECONDS);

        for (ExaminationInfoPage p : page.examinationInfoPages) {
            for (ExaminationPdfDownloader d : p.getDownloaderList()) {
                try {
                    d.download();
                    System.out.println(d);
                    Thread.sleep(500L);
                } catch (Exception e) {
                    System.err.println(p.getUrl() + "下载失败");
                }
            }
        }

    }
}
