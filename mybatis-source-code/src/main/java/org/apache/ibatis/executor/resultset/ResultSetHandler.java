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
package org.apache.ibatis.executor.resultset;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;

/**
 * @author Clinton Begin
 */
public interface ResultSetHandler {

  /**
   * 处理 `Statement` 对象并返回结果对象
   *
   * @param stmt SQL 语句执行后返回的 `Statement` 对象
   * @return 映射后的结果对象列表
   */
  <E> List<E> handleResultSets(Statement stmt) throws SQLException;

  /**
   * 处理 `Statement` 对象并返回一个 `Cursor` 对象
   *
   * @param stmt SQL 语句执行后返回的 `Statement` 对象
   * @return 游标对象，用于迭代结果集
   */
  <E> Cursor<E> handleCursorResultSets(Statement stmt) throws SQLException;

  /**
   * 处理存储过程的输出参数
   *
   * @param cs 存储过程调用的 `CallableStatement` 对象
   */
  void handleOutputParameters(CallableStatement cs) throws SQLException;

}
