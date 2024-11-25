/*
 *    Copyright 2009-2022 the original author or authors.
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
package org.apache.ibatis.scripting.xmltags;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

/**
 * @author Clinton Begin
 */
public class DynamicSqlSource implements SqlSource {

    private final Configuration configuration;
    private final SqlNode rootSqlNode;

    public DynamicSqlSource(Configuration configuration, SqlNode rootSqlNode) {
        this.configuration = configuration;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        // 创建动态 SQL 的上下文信息
        DynamicContext context = new DynamicContext(configuration, parameterObject);
        // 根据上下文信息拼接 SQL，处理 SQL 中的动态标签
        // 处理完成后 SQL 为不包含任何动态标签，为可能包含 #{} 占位符的 SQL 信息，SQL 会被封装到上下文的 sqlBuilder 对象中
        rootSqlNode.apply(context);

        // 处理拼接完成后 SQL 中的 #{} 占位符，将占位符替换为 ?
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        // 解析完成后的 SqlSource 均为 StaticSqlSource 类型，其中记录解析完成后的完整 SQL
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
        // StaticSqlSource 获取 BoundSql SQL 的方法就非常简单了：将 SQL 和参数信息记录下来
        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
        // 在 BoundSql 对象中 additionalParameters Map 中添加 key 为 _parameter，value 为入参 的附加参数信息
        context.getBindings().forEach(boundSql::setAdditionalParameter);
        return boundSql;
    }

}
