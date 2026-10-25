package com.example.realestate.ml

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HousePricePredictionScreen(
   mainNavController: NavController
) {
    // Form Inputs State
    var area by remember { mutableStateOf("1500") }
    var floors by remember { mutableStateOf("2") }
    var bedrooms by remember { mutableStateOf("3") }
    var bathrooms by remember { mutableStateOf("2") }
    var bhk by remember { mutableStateOf("3") }
    var city by remember { mutableStateOf("Delhi") }

    // Dropdown States
    var propertyType by remember { mutableStateOf("Apartment") }
    var furnished by remember { mutableStateOf("Semi-Furnished") }
    var parking by remember { mutableStateOf("Yes") }

    // Result State (Set to sample prediction value; null when unpredicted)
    var predictedPrice by remember { mutableStateOf<String?>(null) }

    // Dropdown Options
    val propertyTypes = listOf("Apartment", "Independent House", "Villa", "Penthouse", "Studio")
    val furnishedOptions = listOf("Unfurnished", "Semi-Furnished", "Fully Furnished")
    val parkingOptions = listOf("Yes", "No")

    val scrollState = rememberScrollState()

    val viewModel:MLVm=viewModel()

    val housePriceState by viewModel.housePriceState.collectAsState()





    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp)
        ) {
            // Header Section
            Text(
                text = "House Price Predictor",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Enter property details to estimate the market value",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Form Fields
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("City") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = area,
                onValueChange = { area = it },
                label = { Text("Area (sq ft)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Property Type Dropdown
            PredictionDropdownMenu(
                label = "Property Type",
                options = propertyTypes,
                selectedOption = propertyType,
                onOptionSelected = { propertyType = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Furnished Dropdown
            PredictionDropdownMenu(
                label = "Furnishing Status",
                options = furnishedOptions,
                selectedOption = furnished,
                onOptionSelected = { furnished = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Parking Dropdown
            PredictionDropdownMenu(
                label = "Parking Available",
                options = parkingOptions,
                selectedOption = parking,
                onOptionSelected = { parking = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Two-column numeric layout (BHK & Floors)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = bhk,
                    onValueChange = { bhk = it },
                    label = { Text("BHK") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium
                )

                OutlinedTextField(
                    value = floors,
                    onValueChange = { floors = it },
                    label = { Text("Floors") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Two-column numeric layout (Bedrooms & Bathrooms)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = bedrooms,
                    onValueChange = { bedrooms = it },
                    label = { Text("Bedrooms") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium
                )

                OutlinedTextField(
                    value = bathrooms,
                    onValueChange = { bathrooms = it },
                    label = { Text("Bathrooms") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Submit Button
            Button(
                onClick = {
                    viewModel.predictHousePrice(PricePredictReq(
                        area.toDouble(),
                        floors.toInt(),
                        bedrooms.toInt(),
                        bathrooms.toInt(),
                        bhk.toInt(),
                        propertyType,
                        furnished,
                        parking,
                        city
                    ))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Filled.Calculate,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                when(val state=housePriceState){
                    is HousePriceState.Empty -> {
                        Text(
                            text = "Predict Price",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    is HousePriceState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    }
                    is HousePriceState.Success -> {
                        Text(
                            text = "Success",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        val result=state.data.predictedPrice
                        predictedPrice=result.toString()
                    }
                    is HousePriceState.Error -> {
                        Text(
                            text = "Please Try Again",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    else -> {}
                }

                }
            }

            // Result Display Card
            predictedPrice?.let { price ->
                Spacer(modifier = Modifier.height(28.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.medium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Filled.HomeWork,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Estimated Market Price",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = price,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Based on $area sq. ft. $propertyType in $city",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }


// Reusable Material 3 Dropdown Component
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictionDropdownMenu(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}