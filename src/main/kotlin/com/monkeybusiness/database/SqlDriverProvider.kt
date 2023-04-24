package com.monkeybusiness.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

object SqlDriverProvider {
    val sqlDriver by lazy { JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY) }
}