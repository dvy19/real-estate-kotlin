package com.example.realestate.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.realestate.user.AppointmentRequest
import com.example.realestate.user.Availability
import com.example.realestate.user.BrokerVm
import com.example.realestate.user.ScheduleData

@Composable
fun BrokerScheduleCard(
    schedule: ScheduleData,
    availability: Availability,
    modifier: Modifier = Modifier
) {

    var brokerViewModel:BrokerVm=viewModel()

    val createAppointmentState by brokerViewModel.createAppointmentState.collectAsState()


    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: Date & Day
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = schedule.date,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = schedule.day,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Middle: Start and End Time
            Text(
                text = "${schedule.startTime} - ${schedule.endTime}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Button(
                onClick = {

                    brokerViewModel.createBrokerAppoint(AppointmentRequest(
                        broker = availability.broker.toString(),
                        startTime = schedule.startTime,
                        endTime = schedule.endTime,
                        appointmentDate = schedule.date,
                        status = "pending"
                    )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Book your Appointment")
            }

            // Right: Availability Status Badge
            val (badgeText, badgeTextColor, badgeContainerColor) = if (schedule.isAvailable) {
                Triple(
                    "Available",
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.primaryContainer
                )
            } else {
                Triple(
                    "Unavailable",
                    MaterialTheme.colorScheme.error,
                    MaterialTheme.colorScheme.errorContainer
                )
            }

            Surface(
                shape = MaterialTheme.shapes.small,
                color = badgeContainerColor
            ) {
                Text(
                    text = badgeText,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = badgeTextColor
                )
            }
        }
    }
}