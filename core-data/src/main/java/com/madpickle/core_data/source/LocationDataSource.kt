package com.madpickle.core_data.source

import com.madpickle.core_data.room_db.dao.LocationDao
import javax.inject.Inject

/**
 * Created by David Madilyan on 31.08.2022.
 */

interface ILocationDataSource{

}

class LocationDataSource@Inject constructor(private val dao: LocationDao): ILocationDataSource {
}
