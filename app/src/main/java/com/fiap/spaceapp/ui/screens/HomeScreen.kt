package com.fiap.spaceapp.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fiap.spaceapp.ui.navigation.Screen
import com.fiap.spaceapp.ui.theme.*

@Composable
fun HomeScreen(nav: NavController) {
    Scaffold(containerColor = SpaceBlack) { pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .verticalScroll(rememberScrollState())
        ) {

            // ── Header ────────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(listOf(Color(0xFF0D1B3E), SpaceBlack))
                    )
                    .padding(horizontal = 24.dp, vertical = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Satellite,
                        contentDescription = null,
                        tint = SpacePrimary,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text("SpaceApp", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = SpaceText)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Monitoramento Climático via Satélite",
                        fontSize = 14.sp,
                        color = SpaceSubtext,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = SpacePrimary.copy(alpha = 0.15f)
                    ) {
                        Text(
                            "Global Solution 2026 · FIAP",
                            fontSize = 12.sp,
                            color = SpacePrimary,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // ── Stats ────────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatCard(Modifier.weight(1f), "8",  "Satélites",  SpacePrimary)
                StatCard(Modifier.weight(1f), "12", "Eventos",    SpaceWarning)
                StatCard(Modifier.weight(1f), "8",  "Regiões",    SpaceAccent)
            }

            Spacer(Modifier.height(24.dp))

            Text(
                "Módulos",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = SpaceText,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(10.dp))

            // ── Nav Cards ─────────────────────────────────────────────────
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                NavCard(
                    icon     = Icons.Default.Warning,
                    title    = "Eventos Monitorados",
                    subtitle = "Desastres e anomalias detectados por satélites",
                    color    = SpaceWarning
                ) { nav.navigate(Screen.Events.route) }

                NavCard(
                    icon     = Icons.Default.Satellite,
                    title    = "Satélites Ativos",
                    subtitle = "Frota orbital de monitoramento ambiental",
                    color    = SpacePrimary
                ) { nav.navigate(Screen.Satellites.route) }

                NavCard(
                    icon     = Icons.Default.Public,
                    title    = "Regiões de Risco",
                    subtitle = "Áreas monitoradas por vulnerabilidade climática",
                    color    = SpaceAccent
                ) { nav.navigate(Screen.Regions.route) }
            }

            Spacer(Modifier.height(24.dp))

            // ── About ─────────────────────────────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCard)
            ) {
                Column(Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Info, null, tint = SpaceSubtext, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Sobre o Projeto", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = SpaceText)
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "O SpaceApp utiliza dados de satélites altimétricos e de sensoriamento remoto para monitorar eventos climáticos críticos ao redor do mundo. Desenvolvido para a Global Solution 2026 – FIAP, tema Indústria Espacial.",
                        fontSize = 12.sp,
                        color = SpaceSubtext,
                        lineHeight = 19.sp
                    )
                }
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}

// ── Componentes reutilizáveis ─────────────────────────────────────────────────

@Composable
fun StatCard(modifier: Modifier, value: String, label: String, color: Color) {
    Card(modifier = modifier, shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceCard)) {
        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = color)
            Text(label, fontSize = 11.sp, color = SpaceSubtext, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun NavCard(icon: ImageVector, title: String, subtitle: String, color: Color, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceCard),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = color, modifier = Modifier.size(24.dp))
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title,    fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = SpaceText)
                Text(subtitle, fontSize = 12.sp, color = SpaceSubtext, lineHeight = 17.sp)
            }
            Icon(Icons.Default.ChevronRight, null, tint = SpaceSubtext, modifier = Modifier.size(18.dp))
        }
    }
}
