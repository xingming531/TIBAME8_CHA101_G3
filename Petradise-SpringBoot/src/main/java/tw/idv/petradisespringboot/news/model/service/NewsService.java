package tw.idv.petradisespringboot.news.model.service;

import java.util.List;

import tw.idv.petradisespringboot.news.model.vo.News;

public interface NewsService {
    News insert(News newNews);
    News update(News news);
    News getOneById(Integer newsId);
    List<News> getAll();

}
