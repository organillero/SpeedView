package io.ferreyra.speedview.SpeedView

data class SpeedViewModel (val indicatorPositionPercentage: Float,  //set the desire degree on percent  [0 -100] %
                           val lowRangePercentage: Float,   //set the value with as a percentage [0 - 100]%
                           val highRangePercentage: Float,  //set the value with as a percentage [0 - 100]%
                           val lowColor: Int,
                           val mediumColor: Int,
                           val highColor: Int
                      ) {

    private val CONVERSION_RATE = 0.5f

//    val indicatorPositionPercentage : Float = _indicatorPositionPercentage
//    get() {
//        indicatorPositionPercentage / CONVERSION_RATE + DEGREES_OFFSET
//    }

    fun getindicatorPositionDegree() : Float{
        return indicatorPositionPercentage / CONVERSION_RATE
    }

    fun getLowRangeDegree() : Float{
        return lowRangePercentage / CONVERSION_RATE
    }

    fun getHighRangeDegree() : Float{
        return highRangePercentage / CONVERSION_RATE
    }
}
