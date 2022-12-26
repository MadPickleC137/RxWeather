package com.madpickle.feature_days_forecast.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.madpickle.core_android.*
import com.madpickle.core_android.screens.Screen
import com.madpickle.core_android.utils.AlertMessageEvent
import com.madpickle.core_android.utils.getPluralsDays
import com.madpickle.core_android.utils.toUrlIcon
import com.madpickle.core_android.view.BaseFragment
import com.madpickle.core_android.view.ItemInfoView
import com.madpickle.core_data.model.*
import com.madpickle.feature_days_forecast.databinding.FragmentRootForecastBinding
import com.madpickle.feature_days_forecast.di.DaggerFeatureForecastComponent
import com.madpickle.feature_days_forecast.di.FeatureForecastComponentProvider
import com.madpickle.feature_days_forecast.state.ForecastViewState
import com.madpickle.feature_days_forecast.viewmodel.ForecastViewModel

/**
 * Created by David Madilyan on 18.12.2022.
 */
class ForecastFragment: BaseFragment<FragmentRootForecastBinding>() {

    private var hoursAdapter: HoursAdapter? = null
    private var alertsAdapter: AlertsAdapter? = null
    private var daysAdapter: DaysAdapter? = null
    private val viewModel by createViewModel<ForecastViewModel>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRootForecastBinding
        get() = FragmentRootForecastBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (context.applicationContext as FeatureForecastComponentProvider)
            .getForecastComponentDependencies()
        val currentsComponent = DaggerFeatureForecastComponent.builder()
            .forecastComponentDependencies(dependencies).build()
        currentsComponent.injectRootFragment(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setArgs(arguments?.getString(Screen.ForecastDetail.ARGS) ?: "")
        alertsAdapter = AlertsAdapter()
        hoursAdapter = HoursAdapter {
            viewModel.onHourItemSelected(it)
        }
        daysAdapter = DaysAdapter {
            viewModel.onDayItemChanged(it)
        }
    }

    override fun start() {
        binding.alertsRecycler.layoutManager = object: LinearLayoutManager(context){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            onBack()
        }
        binding.alertsRecycler.adapter = alertsAdapter
        binding.hoursRecycler.adapter =  hoursAdapter
        binding.daysRecycler.adapter = daysAdapter
        observe(viewModel.state){
            when(it){
                ForecastViewState.Error -> showErrorView()
                ForecastViewState.Loading -> showLoadingView()
                is ForecastViewState.OnDayChange -> setDayView(it.dayModel)
                is ForecastViewState.OnHourChange -> setHourView(it.hourModel)
                is ForecastViewState.Response -> setInitialForecast(it.forecast)
            }
        }
        viewModel.getForecast()
    }

    private fun setInitialForecast(forecast: ForecastModel) {
        binding.progressLayout.isVisible = false
        binding.updateBtn.setOnClickListener {
            it.animate()
                .setInterpolator(LinearInterpolator())
                .setDuration(500L)
                .rotation(360F)
                .start()
            viewModel.getForecast()
        }
        initAlerts(forecast)
        initLocation(forecast)
        if(!forecast.daysForecast.isNullOrEmpty()){
            daysAdapter?.initItems(forecast.daysForecast!!)
            val daysSize = forecast.daysForecast?.size ?: 0
            binding.toolbar.title = "Прогноз на " + getPluralsDays(daysSize)
            setDayView(forecast.daysForecast?.toList()?.firstOrNull()!!)
        }else {
            showErrorView()
        }
    }

    private fun initAlerts(forecast: ForecastModel) {
        if(forecast.alerts.isNullOrEmpty()){
            binding.alertsRecycler.isVisible = false
        } else {
            alertsAdapter?.initAlerts(forecast.alerts!!)
        }
    }

    private fun initLocation(forecast: ForecastModel) {
        binding.regionTitle.text = forecast.location?.name + "/" +
                forecast.location?.country + ", " +
                forecast.location?.localtime
    }

    private fun setHourView(hourModel: HourModel) {
        var title: String =
            if(hourModel.isDay == true) getString(R.string.forecast_day)
            else getString(R.string.forecast_night)
        title += String.format(getString(R.string.forecast_feelslike), hourModel.feelsLike.toCelsiusString())
        val cloudTitle = "Облачность ${hourModel.cloud}%"
        val humidityTitle = "Влажность ${hourModel.humidity}%"
        val windTitle = "Скорость ветра ${hourModel.windKmp} м/с"
        val uvTitle = "УФ-индекс: ${hourModel.getTextUv()}"
        val chanceSnowTitle = "Вероятность снега: ${hourModel.chanceSnow}%"
        val chanceRainTitle = "Вероятность дождя: ${hourModel.chanceRain}%"
        binding.isDay.initItemInfoView(ItemInfoView.PropertyView.Title(title),
            ItemInfoView.PropertyView.Divider(true))
        binding.cloud.initItemInfoView(ItemInfoView.PropertyView.Title(cloudTitle, R.drawable.cloud_d),
            ItemInfoView.PropertyView.Divider(true))
        binding.humidity.initItemInfoView(ItemInfoView.PropertyView.Title(humidityTitle, R.drawable.humidity_d),
            ItemInfoView.PropertyView.Divider(true))
        binding.wind.initItemInfoView(ItemInfoView.PropertyView.Title(windTitle, R.drawable.wind_d),
            ItemInfoView.PropertyView.Divider(true))
        binding.uv.initItemInfoView(ItemInfoView.PropertyView.Title(uvTitle, R.drawable.uf_d),
            ItemInfoView.PropertyView.Divider((hourModel.chanceSnow ?: 0) > 0 || (hourModel.chanceRain ?: 0) > 0))
        binding.chanceSnow.isVisible = (hourModel.chanceSnow ?: 0) > 0
        binding.chanceSnow.initItemInfoView(ItemInfoView.PropertyView.Title(chanceSnowTitle, R.drawable.snow_d),
            ItemInfoView.PropertyView.Divider((hourModel.chanceRain ?: 0) > 0))
        binding.chanceRain.isVisible = (hourModel.chanceRain ?: 0) > 0
        binding.chanceRain.initItemInfoView(ItemInfoView.PropertyView.Title(chanceRainTitle, R.drawable.rain_d),
            ItemInfoView.PropertyView.Divider(false))
    }

    private fun setDayView(dayModel: DayModel) {
        initAstronomy(dayModel.astro)
        binding.iconDay.load(dayModel.iconUrl.toUrlIcon()){
            placeholder(R.drawable.ic_clear_weather)
            transformations(CircleCropTransformation())
        }
        binding.dayTemp.text = dayModel.avgTemp?.toCelsiusString()
        if(dayModel.hours.isNullOrEmpty()){
            binding.hoursRecycler.isVisible = false
        } else {
            binding.hoursRecycler.isVisible = true
            hoursAdapter?.initHours(dayModel.hours!!)
        }
        dayModel.hours?.first()?.let { setHourView(it) }
    }

    private fun initAstronomy(astro: AstronomyModel?) {
        if(astro != null){
            binding.astronomy.root.isVisible = true
            binding.astronomy.moonrise.text = astro.moonrise
            binding.astronomy.moonset.text = astro.moonset
            binding.astronomy.sunrise.text = astro.sunrise
            binding.astronomy.sunset.text = astro.sunset
            binding.astronomy.moonPhaseText.text = astro.textMoonPhase.text
        } else {
            binding.astronomy.root.isVisible = false
        }
    }

    private fun showLoadingView() {
        binding.progressLayout.isVisible = true
    }

    private fun showErrorView() {
        showAlertDialog(
            AlertMessageEvent(
                getString(R.string.forecast_error_title),
                getString(R.string.forecast_error_message)
            )
        )
    }
}