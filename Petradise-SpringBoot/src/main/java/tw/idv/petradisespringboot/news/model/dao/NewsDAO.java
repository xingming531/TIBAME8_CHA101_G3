package tw.idv.petradisespringboot.news.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.idv.petradisespringboot.news.model.vo.News;

public interface NewsDAO extends JpaRepository<News, Integer> {
}
