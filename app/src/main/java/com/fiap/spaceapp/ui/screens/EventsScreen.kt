package com.fiap.spaceapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fiap.spaceapp.data.model.SatelliteEvent
import com.fiap.spaceapp.ui.navigation.Screen
import com.fiap.spaceapp.ui.theme.*
import com.fiap.spaceapp.viewmodel.SpaceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(nav: NavController, vm: SpaceViewModel) {

    val continent by vm.continent.collectAsState()
    val severity  by vm.severity.collectAsState()
    val events     = vm.filteredEvents()

    Scaffold(
        containerColor = SpaceBlack,
        topBar = {
            TopAppBar(
                title = { Text("Eventos Monitorados", color = SpaceText, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null, tint = SpaceText)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SpaceDark)
            )
        }
    ) { pad ->
        Column(Modifier.fillMaxSize().padding(pad)) {

            // Filtro continente
            Text("Continente", fontSize = 12.sp, color = SpaceSubtext,
                modifier = Modifier.padding(start = 16.dp, top = 14.dp, bottom = 6.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(vm.continents) { c ->
                    FilterChip(
                        selected = continent == c,
                        onClick  = { vm.setContinent(c) },
                        label    = { Text(c, fontSize = 12.sp) },
                        colors   = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = SpacePrimary,
                            selectedLabelColor     = SpaceBlack,
                            containerColor         = SpaceCard,
                            labelColor             = SpaceSubtext
                        )
                    )
                }
            }

            // Filtro severidade
            Text("Severidade", fontSize = 12.sp, color = SpaceSubtext,
                modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 6.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(vm.severityItems) { s ->
                    FilterChip(
                        selected = severity == s,
                        onClick  = { vm.setSeverity(s) },
                        label    = { Text(s, fontSize = 12.sp) },
                        colors   = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = SpaceWarning,
                            selectedLabelColor     = SpaceBlack,
                            containerColor         = SpaceCard,
                            labelColor             = SpaceSubtext
                        )
                    )
                }
            }

            Text("${events.size} evento(s)", fontSize = 12.sp, color = SpaceSubtext,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp))

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(events) { e ->
                    EventCard(e, vm) { nav.navigate(Screen.EventDetail.go(e.id)) }
                }
                item { Spacer(Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun EventCard(event: SatelliteEvent, vm: SpaceViewModel, onClick: () -> Unit) {
    val sColor = vm.severityColor(event.severity)
    val sLabel = vm.severityLabel(event.severity)

    Card(
        onClick = onClick,
        shape  = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceCard),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                Column(Modifier.weight(1f)) {
                    Text(event.eventType, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = SpaceText)
                    Spacer(Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, null, tint = SpaceSubtext, modifier = Modifier.size(12.dp))
                        Spacer(Modifier.width(3.dp))
                        Text("${event.region} · ${event.continent}", fontSize = 11.sp, color = SpaceSubtext)
                    }
                }
                Surface(shape = RoundedCornerShape(8.dp), color = sColor.copy(alpha = 0.2f)) {
                    Text(sLabel, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = sColor,
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp))
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                SmallTag(Icons.Default.Satellite, event.satellite)
                SmallTag(Icons.Default.DateRange, event.date)
                SmallTag(Icons.Default.TrendingUp, "Imp: ${event.estimatedImpact}")
            }
            Spacer(Modifier.height(6.dp))
            Text(event.description, fontSize = 12.sp, color = SpaceSubtext, lineHeight = 18.sp, maxLines = 2)
        }
    }
}

@Composable
fun SmallTag(icon: ImageVector, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = SpacePrimary, modifier = Modifier.size(11.dp))
        Spacer(Modifier.width(3.dp))
        Text(label, fontSize = 10.sp, color = SpaceSubtext)
    }
}
