package com.me.structure.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insert(list: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insert(item: T)
}