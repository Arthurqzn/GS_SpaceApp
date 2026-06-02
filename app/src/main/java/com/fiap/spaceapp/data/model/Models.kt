package com.fiap.spaceapp.data.model

// ── Entidades ────────────────────────────────────────────────────────────────

data class SatelliteEvent(
    val id: Int,
    val region: String,
    val continent: String,
    val eventType: String,
    val severity: Int,           // 1-10
    val estimatedImpact: Double,
    val date: String,
    val satellite: String,
    val description: String
)

data class Satellite(
    val id: Int,
    val name: String,
    val agency: String,
    val launchYear: Int,
    val status: String,
    val purpose: String,
    val altitude: String
)

data class Region(
    val id: Int,
    val name: String,
    val continent: String,
    val areaType: String,
    val riskLevel: String,
    val monitoredSince: String
)

// ── Mock Data ────────────────────────────────────────────────────────────────

object MockData {

    val satellites = listOf(
        Satellite(1, "Jason-3",          "NASA/CNES",             2016, "ATIVO", "Altimetria oceânica",      "1 336 km"),
        Satellite(2, "Sentinel-1A",      "ESA",                   2014, "ATIVO", "Radar SAR",                "693 km"),
        Satellite(3, "GOES-16",          "NOAA/NASA",             2016, "ATIVO", "Meteorologia",             "35 786 km"),
        Satellite(4, "CBERS-4A",         "INPE/CNSA",             2019, "ATIVO", "Sensoriamento remoto",     "628 km"),
        Satellite(5, "Aqua",             "NASA",                  2002, "ATIVO", "Ciclo da água",            "705 km"),
        Satellite(6, "CryoSat-2",        "ESA",                   2010, "ATIVO", "Criosfera polar",          "717 km"),
        Satellite(7, "SMAP",             "NASA",                  2015, "ATIVO", "Umidade do solo",          "685 km"),
        Satellite(8, "RiskSat Climate",  "Agência Internacional", 2018, "ATIVO", "Riscos climáticos",        "550 km")
    )

    val regions = listOf(
        Region(1, "Amazônia",       "América do Sul", "Floresta tropical", "ALTO",    "1993"),
        Region(2, "Ártico",         "Ártico",         "Polar",             "CRÍTICO", "1993"),
        Region(3, "Bangladesh",     "Ásia",           "Delta fluvial",     "CRÍTICO", "2000"),
        Region(4, "Sahel",          "África",         "Semiárido",         "ALTO",    "1995"),
        Region(5, "Maldivas",       "Oceania",        "Insular",           "CRÍTICO", "1993"),
        Region(6, "Pantanal",       "América do Sul", "Zona úmida",        "ALTO",    "2005"),
        Region(7, "Mediterrâneo",   "Europa",         "Costeiro",          "MÉDIO",   "1998"),
        Region(8, "Grande Barreira","Oceania",         "Marinho",           "ALTO",    "1993")
    )

    val events = listOf(
        SatelliteEvent(1,  "Amazônia",       "América do Sul", "Desmatamento",            8, 6.8, "2025-03-15", "Sentinel-1A",     "Avanço do desmatamento detectado na região norte. Área: ~1 200 km²."),
        SatelliteEvent(2,  "Ártico",         "Ártico",         "Degelo de Alta Montanha", 9, 6.8, "2025-04-02", "CryoSat-2",       "Aceleração anormal do degelo da calota polar ártica registrada."),
        SatelliteEvent(3,  "Bangladesh",     "Ásia",           "Alagamento",              8, 6.9, "2025-05-10", "GOES-16",         "Enchentes severas afetando zona costeira densamente populosa."),
        SatelliteEvent(4,  "Sahel",          "África",         "Seca Agrícola",           7, 6.4, "2025-02-20", "SMAP",            "Déficit hídrico crítico comprometendo safra de milhões de hectares."),
        SatelliteEvent(5,  "Maldivas",       "Oceania",        "Aquecimento Oceânico",    9, 6.9, "2025-01-30", "Jason-3",         "Temperatura superficial 2,3 °C acima da média histórica."),
        SatelliteEvent(6,  "Pantanal",       "América do Sul", "Tempestade Severa",       7, 6.8, "2025-06-01", "GOES-16",         "Sistema de tempestades extremas com risco de inundação."),
        SatelliteEvent(7,  "Mediterrâneo",   "Europa",         "Risco de Deslizamento",   6, 6.4, "2025-04-18", "Sentinel-1A",     "Instabilidade geológica em encostas costeiras detectada."),
        SatelliteEvent(8,  "Grande Barreira","Oceania",        "Anomalia Oceânica",       8, 6.2, "2025-03-22", "Aqua",            "Branqueamento de coral em larga escala identificado."),
        SatelliteEvent(9,  "Amazônia",       "América do Sul", "Foco de Calor",           7, 6.5, "2025-05-25", "CBERS-4A",        "Múltiplos focos de incêndio ativo detectados na região central."),
        SatelliteEvent(10, "Ártico",         "Ártico",         "Redução de Geleira",      9, 6.9, "2025-02-14", "CryoSat-2",       "Retração acelerada da geleira Jakobshavn: −35 m em 30 dias."),
        SatelliteEvent(11, "Bangladesh",     "Ásia",           "Tempestade Costeira",     8, 6.8, "2025-05-30", "GOES-16",         "Ciclone tropical se formando no Golfo de Bengala."),
        SatelliteEvent(12, "Sahel",          "África",         "Anomalia Climática",      6, 6.2, "2025-03-05", "RiskSat Climate", "Padrões anômalos de chuva comprometem ciclo agrícola anual.")
    )
}
