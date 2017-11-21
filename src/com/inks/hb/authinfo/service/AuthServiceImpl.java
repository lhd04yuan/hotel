package com.inks.hb.authinfo.service;

import com.inks.hb.authinfo.dao.AuthInfoDaoImpl;
import com.inks.hb.authinfo.pojo.AuthInfo;
import com.inks.hb.common.CommonDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuthServiceImpl implements AuthService {

    private CommonDao dao = new AuthInfoDaoImpl();

    @Override
    public int queryAuthInfoNum() throws SQLException {

        return dao.queryDataNum();
    }

    @Override
    public AuthInfo query(int authId) throws SQLException {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setAuthId(authId);

        return (AuthInfo) dao.query(authInfo);
    }

    @Override
    public AuthInfo query(String authItem) throws SQLException {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setAuthItem(authItem);

        return (AuthInfo) dao.query(authInfo);
    }

    @Override
    public ArrayList query(int page, int limit) throws SQLException {

        int start = (page * limit) - limit + 1; //每一页的起始位置

        if (start < 1)
            start = 1;

        return dao.query(start, limit);
    }

    @Override
    public void updateAuthInfo(AuthInfo authInfo) throws SQLException {

        dao.updateData(authInfo);
    }
}
