/*
 *    Copyright 2009-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.executor;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

/**
 * @author Clinton Begin
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    // 该方法用于执行更新操作（包括插入、更新和删除），它接受一个 `MappedStatement` 对象和更新参数，并返回受影响的行数
    int update(MappedStatement ms, Object parameter) throws SQLException;

    // 该方法用于执行查询操作，接受 `MappedStatement` 对象（包含 SQL 语句的映射信息）、查询参数、分页信息、结果处理器等，并返回查询结果的列表
    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
                      CacheKey cacheKey, BoundSql boundSql) throws SQLException;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
            throws SQLException;

    <E> Cursor<E> queryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds) throws SQLException;

    // 该方法用于刷新批处理语句并返回批处理结果
    List<BatchResult> flushStatements() throws SQLException;

    // 该方法用于提交事务，参数 `required` 表示是否必须提交事务
    void commit(boolean required) throws SQLException;

    // 该方法用于回滚事务。参数 `required` 表示是否必须回滚事务
    void rollback(boolean required) throws SQLException;

    // 该方法用于创建缓存键，缓存键用于标识缓存中的唯一查询结果
    CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);

    // 该方法用于检查某个查询结果是否已经缓存在本地
    boolean isCached(MappedStatement ms, CacheKey key);

    // 该方法用于清空本地缓存
    void clearLocalCache();

    // 该方法用于延迟加载属性
    void deferLoad(MappedStatement ms, MetaObject resultObject, String property, CacheKey key, Class<?> targetType);

    // 该方法用于获取当前的事务对象
    Transaction getTransaction();

    // 该方法用于关闭执行器。参数 `forceRollback` 表示是否在关闭时强制回滚事务
    void close(boolean forceRollback);

    boolean isClosed();

    // 该方法用于设置执行器的包装器
    void setExecutorWrapper(Executor executor);

}
