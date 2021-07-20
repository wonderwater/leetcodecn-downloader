package water.helper;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;
import water.mapper.SubmissionMapper;

public class SqliteHelper {

    private volatile static SqliteHelper sqliteHelper;
    private final SqlSessionFactory sqlSessionFactory;
    private SqliteHelper() {
        SQLiteConfig config = new SQLiteConfig();
        SQLiteDataSource dataSource = new SQLiteDataSource(config);
        dataSource.setUrl("jdbc:sqlite:db");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration(environment);
        configuration.setMapUnderscoreToCamelCase(false);
        configuration.addMappers(SubmissionMapper.class.getPackage().getName());
        sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(configuration);
    }

    public SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    public static SqliteHelper getInstance() {
        if (sqliteHelper != null){
            return sqliteHelper;
        }
        synchronized (SqliteHelper.class){
            if (sqliteHelper != null){
                return sqliteHelper;
            }
            return sqliteHelper = new SqliteHelper();
        }
    }

}
