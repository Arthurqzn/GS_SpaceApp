# 🛰️ SpaceApp — Monitoramento Climático via Satélite

**Global Solution 2026 · FIAP — Tema: Indústria Espacial**

Aplicativo Android desenvolvido em **Kotlin + Jetpack Compose** que simula uma
solução mobile de **monitoramento climático e prevenção de desastres** usando
dados de satélites de sensoriamento remoto e altimetria.

---

## 🎯 Objetivo

O SpaceApp representa uma solução conectada ao contexto da **economia espacial**.
A partir de dados captados por uma frota de satélites (Sentinel-1A, GOES-16,
CBERS-4A, Jason-3, entre outros), o app exibe **eventos climáticos críticos**
detectados em diferentes regiões do mundo — desmatamento, degelo, alagamentos,
secas, anomalias oceânicas — permitindo que o usuário filtre, explore e analise
cada ocorrência por região, continente e nível de severidade.

---

## 📱 Funcionalidades

- **Tela inicial** com identidade visual, nome da solução, descrição e indicadores gerais (satélites, eventos e regiões monitoradas).
- **Navegação entre 5 telas** com Navigation Compose (incluindo tela de detalhe com passagem de argumento).
- **Lista de eventos** monitorados por satélite, com **filtros interativos** por continente e severidade.
- **Lista de satélites** ativos da frota orbital.
- **Lista de regiões** de risco climático.
- **Tela de detalhe** completa de cada evento, com cor dinâmica por severidade.

---

## 🗺️ Fluxo de navegação (telas)

```
┌──────────────────────────────┐
│         HOME (Início)         │
│  Nome, descrição, stats e     │
│  3 cards de navegação         │
└───────────┬───────────────────┘
            │
   ┌────────┼────────────────────────┐
   │        │                         │
   ▼        ▼                         ▼
┌────────┐ ┌────────────┐ ┌────────────────┐
│ EVENTOS│ │ SATÉLITES  │ │    REGIÕES     │
│ (lista │ │ (lista de  │ │ (lista de áreas│
│ + filtros)│ frota)    │ │ monitoradas)   │
└───┬────┘ └────────────┘ └────────────────┘
    │
    │ toca em um evento
    ▼
┌──────────────────────────┐
│   DETALHE DO EVENTO       │
│ Info completa + alerta    │
└──────────────────────────┘
```

| # | Tela | Arquivo | Componentes principais |
|---|------|---------|------------------------|
| 1 | **Home** | `HomeScreen.kt` | Scaffold, Column, Row, Card, Brush gradient |
| 2 | **Eventos** | `EventsScreen.kt` | Scaffold, TopAppBar, LazyRow (filtros), LazyColumn, FilterChip |
| 3 | **Satélites** | `SatellitesScreen.kt` | Scaffold, LazyColumn, Card |
| 4 | **Regiões** | `RegionsScreen.kt` | Scaffold, LazyColumn, Card |
| 5 | **Detalhe do Evento** | `EventDetailScreen.kt` | Scaffold, Column scrollável, Card, navArgument |

---

## 📸 Telas do Aplicativo

### Tela Inicial (Home)
Nome da solução, descrição, indicadores e cards de navegação.

<img width="435" height="828" alt="image" src="https://github.com/user-attachments/assets/008b5194-976a-4aab-aa9f-e1e6621ad2b4" />

### Eventos Monitorados
Lista de eventos climáticos com filtros interativos por continente e severidade.

<img width="460" height="825" alt="image" src="https://github.com/user-attachments/assets/cb4bee2f-6234-4275-bfe5-7b0b054a0747" />

### Detalhe do Evento
Informações completas do evento selecionado, com cor dinâmica por severidade.

<img width="475" height="828" alt="image" src="https://github.com/user-attachments/assets/734cd37e-c642-47c0-94a8-9cc8342557a2" />

### Satélites Ativos
Frota orbital de monitoramento ambiental.

<img width="451" height="827" alt="image" src="https://github.com/user-attachments/assets/f2b29b90-20d6-466d-8be9-5cd236e0b638" />

### Regiões de Risco
Áreas monitoradas por vulnerabilidade climática.

<img width="466" height="836" alt="image" src="https://github.com/user-attachments/assets/a94c677b-b5ff-453b-97b4-072659e61a48" />

---

## 🧩 Como os requisitos foram atendidos

| Requisito | Onde está implementado |
|-----------|------------------------|
| **1. Tela inicial** (nome, descrição, identidade visual) | `HomeScreen.kt` — header com ícone, título "SpaceApp", subtítulo, badge da GS e card "Sobre o Projeto" |
| **2. Navegação Compose (mín. 3 telas)** | `NavGraph.kt` + `Screen.kt` — 5 destinos, com rota parametrizada `event/{id}` |
| **3. Componentes Compose** | Column, Row, Card, LazyColumn, LazyRow e Scaffold usados em todas as telas |
| **4. Exibição de dados do tema** | `Models.kt` — `MockData` com 8 satélites, 8 regiões e 12 eventos climáticos |
| **5. Interação com o usuário** | Filtros (FilterChip) por continente e severidade, cards clicáveis, navegação para detalhe |
| **6. Organização e boas práticas** | Separação por pacotes: `data/model`, `viewmodel`, `ui/navigation`, `ui/screens`, `ui/theme` + uso de `ViewModel` e `StateFlow` |

---

## 🏗️ Estrutura do projeto

```
app/src/main/java/com/fiap/spaceapp/
├── MainActivity.kt              # Ponto de entrada, configura NavGraph
├── data/model/
│   └── Models.kt                # Entidades + dados mockados (MockData)
├── viewmodel/
│   └── SpaceViewModel.kt        # Estado dos filtros, lógica de filtragem
└── ui/
    ├── navigation/
    │   ├── Screen.kt            # Rotas (sealed class)
    │   └── NavGraph.kt          # NavHost
    ├── screens/
    │   ├── HomeScreen.kt
    │   ├── EventsScreen.kt
    │   ├── EventDetailScreen.kt
    │   ├── SatellitesScreen.kt
    │   └── RegionsScreen.kt
    └── theme/
        └── Theme.kt             # Paleta de cores e tema escuro
```

---

## 🛠️ Tecnologias

- **Kotlin**
- **Jetpack Compose** (Material 3)
- **Navigation Compose**
- **ViewModel + StateFlow** (gerenciamento de estado)
- **compileSdk 34 · minSdk 26 · targetSdk 34**

---

## ▶️ Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/Arthurqzn/GS_SpaceApp.git
   ```
2. Abra o projeto no **Android Studio** (versão recente — Koala ou superior).
3. Aguarde o **Gradle Sync** finalizar.
4. Selecione um emulador (API 26+) ou dispositivo físico.
5. Clique em **Run ▶**.

> Dados 100% mockados — não requer internet nem backend.

---

## 👥 Integrantes

| Nome | RM |
|------|-----|
| Arthur Queiroz | 555205 |
| Gabriel de Lima | 99659 |
| Felipe Gasparetto Ohara Sato | 554315 |

---

## 👤 Autoria

Projeto desenvolvido para a **Global Solution 2026 — FIAP**
Tema: **Indústria Espacial** · Solução: Monitoramento climático via satélite
