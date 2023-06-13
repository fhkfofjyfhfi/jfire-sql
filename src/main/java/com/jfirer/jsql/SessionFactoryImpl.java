package com.jfirer.jsql;

import com.jfirer.baseutil.reflect.ReflectUtil;
import com.jfirer.jsql.dialect.Dialect;
import com.jfirer.jsql.executor.SqlExecutor;
import com.jfirer.jsql.session.SqlSession;
import com.jfirer.jsql.session.impl.SqlSessionImpl;

import javax.sql.DataSource;

public class SessionFactoryImpl implements SessionFactory
{
    private final SqlExecutor headSqlExecutor;
    private final DataSource  dataSource;
    private final Dialect     dialect;

    public SessionFactoryImpl(SqlExecutor headSqlExecutor, DataSource dataSource, Dialect dialect)
    {
        this.headSqlExecutor = headSqlExecutor;
        this.dataSource = dataSource;
        this.dialect = dialect;
    }

    @Override
    public SqlSession openSession()
    {
        try
        {
            return new SqlSessionImpl(dataSource.getConnection(), headSqlExecutor, dialect);
        }
        catch (Throwable e)
        {
            ReflectUtil.throwException(e);
            return null;
        }
    }
}
