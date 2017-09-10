package cn.crap.service.mybatis.imp;

import cn.crap.dao.mybatis.ArticleMapper;
import cn.crap.enumeration.TableId;
import cn.crap.framework.IdGenerator;
import cn.crap.model.mybatis.Article;
import cn.crap.model.mybatis.ArticleCriteria;
import cn.crap.model.mybatis.ArticleWithBLOBs;
import cn.crap.utils.TableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Automatic generation by tools
 * service
 */
@Service
public class MybatisArticleService {
    @Autowired
    private ArticleMapper mapper;

    public List<Article> selectByExample(ArticleCriteria example) {
        return mapper.selectByExample(example);
    }

    public int countByExample(ArticleCriteria example) {
        return mapper.countByExample(example);
    }

    public Article selectByPrimaryKey(String id) {
        if (id == null){
            return null;
        }
        return mapper.selectByPrimaryKey(id);
    }

    public boolean insert(ArticleWithBLOBs model) {
        if (model == null) {
            return false;
        }
        model.setId(IdGenerator.getId(TableId.ARTICLE));
        if (model.getSequence() == null){
            ArticleCriteria example = new ArticleCriteria();
            example.setOrderByClause(TableField.SORT.SEQUENCE_DESC);
            example.setMaxResults(1);
            List<Article>  models = this.selectByExample(example);
            if (models.size() > 0){
                model.setSequence(models.get(0).getSequence() + 1);
            }else{
                model.setSequence(0);
            }
        }
        model.setCreateTime(new Date());
        return mapper.insertSelective(model) > 0;
    }

    public boolean update(ArticleWithBLOBs model) {
        if (model == null) {
            return false;
        }
        return mapper.updateByPrimaryKeySelective(model) > 0 ? true : false;
    }

    public boolean delete(String id) {
        Assert.notNull(id, "id can't be null");
        return mapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

}
