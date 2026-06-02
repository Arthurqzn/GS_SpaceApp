package com.fiap.spaceapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fiap.spaceapp.data.model.Region
import com.fiap.spaceapp.ui.theme.*
import com.fiap.spaceapp.viewmodel.SpaceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionsScreen(nav: NavController, vm: SpaceViewModel) {
    Scaffold(
        containerColor = SpaceBlack,
        topBar = {
            TopAppBar(
                title = { Text("Regiões de Risco", color = SpaceText, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null, tint = SpaceText)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SpaceDark)
            )
        }
    ) { pad ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(pad),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text("${vm.regions.size} regiões monitoradas",
                    fontSize = 12.sp, color = SpaceSubtext,
                    modifier = Modifier.padding(bottom = 4.dp))
            }
            items(vm.regions) { r -> RegionCard(r) }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun RegionCard(r: Region) {
    val rColor = when (r.riskLevel) {
        "CRÍTICO" -> Color(0xFFEF5350)
        "ALTO"    -> Color(0xFFFFB74D)
        else      -> Color(0xFF81C784)
    }
    Card(shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceCard),
        modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(14.dp)) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(r.name, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = SpaceText)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Public, null, tint = SpaceSubtext, modifier = Modifier.size(11.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(r.continent, fontSize = 11.sp, color = SpaceSubtext)
                    }
                }
                Surface(shape = RoundedCornerShape(8.dp), color = rColor.copy(alpha = 0.2f)) {
                    Text(r.riskLevel, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = rColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }
            Spacer(Modifier.height(10.dp))
            HorizontalDivider(color = SpaceSubtext.copy(alpha = 0.12f))
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                InfoPair("Tipo de Área",       r.areaType)
                InfoPair("Monitorado desde",   r.monitoredSince)
            }
        }
    }
}
