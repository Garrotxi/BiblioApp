package com.biblioApp.example;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.MySQLDialect;

public class MySQLCustomDialect extends MySQLDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";
    }
}
