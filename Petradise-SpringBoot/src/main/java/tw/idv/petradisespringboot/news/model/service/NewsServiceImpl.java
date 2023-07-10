package tw.idv.petradisespringboot.news.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.news.model.dao.NewsDAO;
import tw.idv.petradisespringboot.news.model.vo.News;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsDAO newsDAO;

    @Autowired
    public NewsServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
    public News insert(News newNews) {
        return newsDAO.save(newNews);
    }
    public News update(News news) {
        return newsDAO.save(news);
    }
    public News getOneById(Integer newsId) {
        System.out.println("........................................................newsId=" + newsId);
        return newsDAO.findById(newsId).orElse(null);
    }
    public List<News> getAll(){
        return newsDAO.findAll((Sort.by(Sort.Direction.DESC, "newsId")));
    }
}
