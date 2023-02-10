package com.example.dogify.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BreedResponse(@SerialName("message") val breeds: List<String>)