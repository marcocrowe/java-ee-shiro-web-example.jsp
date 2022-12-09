/*
 * Copyright (c) 2022 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */

package io.gitlab.markcrowe.services;

import org.apache.shiro.realm.jdbc.JdbcRealm;

public class JdbcSaltRealm extends JdbcRealm
{
    public JdbcSaltRealm() {
        setSaltStyle(SaltStyle.COLUMN);
    }
}