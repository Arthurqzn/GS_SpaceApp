package com.fiap.spaceapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fiap.spaceapp.data.model.MockData
import com.fiap.spaceapp.ui.theme.*
import com.fiap.spaceapp.viewmodel.SpaceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(nav: NavController, eventId: Int, vm: SpaceViewModel) {
    val event = MockData.events.firstOrNull { it.id == eventId }

    Scaffold(
        containerColor = SpaceBlack,
        topBar = {
            TopAppBar(
                title = { Text("Detalhe do Evento", color = SpaceText, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null, tint = SpaceText)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SpaceDark)
            )
        }
    ) { pad ->
        if (event == null) {
            Box(Modifier.fillMaxSize().padding(pad), contentAlignment = Alignment.Center) {
                Text("Evento não encontrado.", color = SpaceSubtext)
            }
            return@Scaffold
        }

        val sColor = vm.severityColor(event.severity)
        val sLabel = vm.severityLabel(event.severity)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Cabeçalho
            Card(shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCard)) {
                Column(Modifier.padding(18.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top) {
                        Text(event.eventType, fontSize = 20.sp, fontWeight = FontWeight.Bold,
                            color = SpaceText, modifier = Modifier.weight(1f))
                        Surface(shape = RoundedCornerShape(8.dp), color = sColor.copy(alpha = 0.2f)) {
                            Text(sLabel, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = sColor,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(event.description, fontSize = 13.sp, color = SpaceSubtext, lineHeight = 20.sp)
                }
            }

            // Detalhes
            Card(shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCard)) {
                Column(Modifier.padding(18.dp)) {
                    Text("Informações", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = SpaceText)
                    Spacer(Modifier.height(12.dp))
                    DetailRow(Icons.Default.LocationOn, "Região",       event.region)
                    DetailRow(Icons.Default.Public,     "Continente",   event.continent)
                    DetailRow(Icons.Default.Satellite,  "Satélite",     event.satellite)
                    DetailRow(Icons.Default.DateRange,  "Data",         event.date)
                    DetailRow(Icons.Default.Warning,    "Severidade",   "${event.severity}/10 — $sLabel")
                    DetailRow(Icons.Default.TrendingUp, "Impacto Est.", "${event.estimatedImpact}")
                }
            }

            // Alerta
            Card(shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = sColor.copy(alpha = 0.1f))) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Notifications, null, tint = sColor, modifier = Modifier.size(22.dp))
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text("Alerta Ativo", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = sColor)
                        Text("Monitoramento contínuo por satélites em operação.",
                            fontSize = 12.sp, color = SpaceSubtext, lineHeight = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = SpacePrimary, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(10.dp))
        Text(label, fontSize = 12.sp, color = SpaceSubtext, modifier = Modifier.width(110.dp))
        Text(value, fontSize = 12.sp, color = SpaceText, fontWeight = FontWeight.Medium)
    }
}
