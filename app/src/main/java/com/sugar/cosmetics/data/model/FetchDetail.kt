package com.sugar.cosmetics.data.model

import java.io.Serializable

class FetchDetail: Serializable {
    var disclaimer : String? = null
    var license    : String? = null
    var timestamp  : Int?    = null
    var base       : String? = null
    var rates      :  Map<String,Double>?             = HashMap()
}

