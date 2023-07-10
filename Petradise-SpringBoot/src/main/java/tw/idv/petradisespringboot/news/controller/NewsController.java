package tw.idv.petradisespringboot.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.news.model.service.NewsService;
import tw.idv.petradisespringboot.news.model.vo.News;

import java.util.List;

@RestController
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping ("/news/add")
    public News insert(@RequestBody News news) {
        return newsService.insert(news);
    }

    @PutMapping ("/news/update/{newsId}")
    public News update(@PathVariable("newsId") Integer newsId, @RequestBody News news) {
        News existingNews = newsService.getOneById(newsId);
        if (existingNews != null) {
            news.setNewsId(newsId);
            newsService.update(news);
        }
        return news;
    }

    @GetMapping("/news/get/{newsId}")
    public News getNewsById(@PathVariable("newsId") Integer newsId) {
        return newsService.getOneById(newsId);
    }

    @GetMapping("/news/get/all")
    public List<News> getAll() {
        return newsService.getAll();
    }
}
