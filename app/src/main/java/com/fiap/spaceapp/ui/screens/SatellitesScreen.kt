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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fiap.spaceapp.data.model.Satellite
import com.fiap.spaceapp.ui.theme.*
import com.fiap.spaceapp.viewmodel.SpaceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SatellitesScreen(nav: NavController, vm: SpaceViewModel) {
    Scaffold(
        containerColor = SpaceBlack,
        topBar = {
            TopAppBar(
                title = { Text("Satélites Ativos", color = SpaceText, fontWeight = FontWeight.SemiBold) },
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
                Text("${vm.satellites.size} satélites em operação",
                    fontSize = 12.sp, color = SpaceSubtext,
                    modifier = Modifier.padding(bottom = 4.dp))
            }
            items(vm.satellites) { s -> SatCard(s) }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun SatCard(s: Satellite) {
    Card(shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceCard),
        modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(14.dp)) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Satellite, null, tint = SpacePrimary, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(s.name,   fontSize = 15.sp, fontWeight = FontWeight.Bold,   color = SpaceText)
                        Text(s.agency, fontSize = 11.sp, color = SpaceSubtext)
                    }
                }
                Surface(shape = RoundedCornerShape(8.dp), color = SpaceAccent.copy(alpha = 0.18f)) {
                    Text(s.status, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SpaceAccent,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }
            Spacer(Modifier.height(10.dp))
            HorizontalDivider(color = SpaceSubtext.copy(alpha = 0.12f))
            Spacer(Modifier.height(10.dp))
            Text(s.purpose, fontSize = 13.sp, color = SpaceText)
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                InfoPair("Lançamento", s.launchYear.toString())
                InfoPair("Altitude",   s.altitude)
            }
        }
    }
}

@Composable
fun InfoPair(label: String, value: String) {
    Column {
        Text(label, fontSize = 10.sp, color = SpaceSubtext)
        Text(value, fontSize = 12.sp, color = SpaceText, fontWeight = FontWeight.Medium)
    }
}
