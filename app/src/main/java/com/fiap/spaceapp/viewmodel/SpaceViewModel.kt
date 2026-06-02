package com.fiap.spaceapp.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.fiap.spaceapp.data.model.MockData
import com.fiap.spaceapp.data.model.Region
import com.fiap.spaceapp.data.model.Satellite
import com.fiap.spaceapp.data.model.SatelliteEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SpaceViewModel : ViewModel() {

    // ── Filtros ──────────────────────────────────────────────────────────────
    private val _continent   = MutableStateFlow("Todos")
    val continent: StateFlow<String> = _continent.asStateFlow()

    private val _severity    = MutableStateFlow("Todos")
    val severity: StateFlow<String> = _severity.asStateFlow()

    val continents    = listOf("Todos", "América do Sul", "Ásia", "África", "Europa", "Oceania", "Ártico")
    val severityItems = listOf("Todos", "Crítico (≥9)", "Alto (7-8)", "Médio (≤6)")

    fun setContinent(v: String) { _continent.value = v }
    fun setSeverity(v: String)  { _severity.value  = v }

    // ── Listas ───────────────────────────────────────────────────────────────
    val satellites: List<Satellite> = MockData.satellites
    val regions:    List<Region>    = MockData.regions

    fun filteredEvents(): List<SatelliteEvent> = MockData.events.filter { e ->
        val cOk = _continent.value == "Todos" || e.continent == _continent.value
        val sOk = when (_severity.value) {
            "Crítico (≥9)" -> e.severity >= 9
            "Alto (7-8)"   -> e.severity in 7..8
            "Médio (≤6)"   -> e.severity <= 6
            else            -> true
        }
        cOk && sOk
    }

    // ── Helpers ──────────────────────────────────────────────────────────────
    fun severityColor(s: Int): Color = when {
        s >= 9 -> Color(0xFFEF5350)
        s >= 7 -> Color(0xFFFFB74D)
        else   -> Color(0xFF81C784)
    }
    fun severityLabel(s: Int): String = when {
        s >= 9 -> "CRÍTICO"
        s >= 7 -> "ALTO"
        else   -> "MÉDIO"
    }
}
