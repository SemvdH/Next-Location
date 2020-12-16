package com.a1.nextlocation.data.db.repositories;

import android.content.Context;

import com.a1.nextlocation.data.Data;
import com.a1.nextlocation.data.db.Database;
import com.a1.nextlocation.data.db.dao.DataDao;

public class DataRepository {
    private DataDao mDataDao;
    private Data data;

    public DataRepository(Context context) {
        Database db = Database.getDatabase(context);
        mDataDao = db.dataDao();
        data = mDataDao.getData();
    }

    public Data getData() {
        return data;
    }


}
