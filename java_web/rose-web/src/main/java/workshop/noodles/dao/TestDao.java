package workshop.noodles.dao;

import workshop.noodles.model.entities.TestModel;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

@DAO
public interface TestDao {
    @SQL("select id,msg from test limit 1")
    public TestModel getTest();
}