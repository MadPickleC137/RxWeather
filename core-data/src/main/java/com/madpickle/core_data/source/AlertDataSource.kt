package com.madpickle.core_data.source

import com.madpickle.core_data.room_db.dao.AlertDao
import javax.inject.Inject


/**
 * Created by David Madilyan on 31.08.2022.
 */

interface IAlertDataSource{

}

class AlertDataSource@Inject constructor(private val dao: AlertDao): IAlertDataSource {
}
