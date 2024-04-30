package com.personal.page.gwy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Data
@AllArgsConstructor
public class ExaminationInfoPage {

    private String url;
    List<ExaminationPdfDownloader> downloaderList;

    public String load() throws IOException {
        Document html = Jsoup.connect(url).get();
        String title = html.title();
        String fileName = title.split("-")[0].trim();
        Elements urlList = html.select("a");
        for (Element a : urlList) {
            if (a.attr("href").endsWith(".pdf")) {
                downloaderList.add(new ExaminationPdfDownloader(fileName, a.attr("href")));
            }
        }
        if (CollectionUtils.isEmpty(downloaderList)) {
            // 进行二次解析
            for (Element a : urlList) {
                String href = a.attr("href");
                if (!href.startsWith("http:") || !href.endsWith(".html")) {
                    continue;
                }
                try {
                    Document subHtml = Jsoup.connect(href).get();
                    String subTitle = subHtml.title();
                    String fileName_0 = subTitle.split("-")[0].trim();
                    Elements urlList_0 = subHtml.select("a");
                    for (Element a_0 : urlList_0) {
                        if (a_0.attr("href").endsWith(".pdf")) {
                            downloaderList.add(new ExaminationPdfDownloader(fileName_0, a_0.attr("href")));
                        }
                    }
                } catch (Exception e) {}
            }
            if (CollectionUtils.isEmpty(downloaderList)) {
                System.out.println("未能下载文件：" + url);
            }
        }
        return "";
    }


}
