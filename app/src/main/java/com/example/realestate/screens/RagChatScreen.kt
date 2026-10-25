package com.example.realestate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun RagChatScreen(

    mainNavController: NavController
) {
    var question by remember { mutableStateOf("") }

    //val state by viewModel.ragChatState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // Top Bar
        Text(
            text = "AI Real Estate Assistant",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        HorizontalDivider()

        // Chat Area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            /*
            when (val result = state) {

                is RagChatReq.Empty -> {
                    Text("Ask anything about real estate.")
                }

                is RagChatReq.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is RagChatReq.Success -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ChatBubble(
                            text = result.data.data.question,
                            isUser = true
                        )

                        ChatBubble(
                            text = result.data.data.answer,
                            isUser = false
                        )
                    }
                }

                is RagChatReq.Error -> {
                    Text(
                        text = result.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

             */
        }

        // Message Input
        HorizontalDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = question,
                onValueChange = { question = it },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Ask your question...")
                },
                maxLines = 4
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                //enabled = question.isNotBlank() && state !is RagChatReq.Loading,
                onClick = {

                }
            ) {
                Text("Send")
            }
        }
    }
}


@Composable
fun ChatBubble(
    text: String,
    isUser: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {
        Surface(
            color = if (isUser) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.widthIn(max = 300.dp)
        ) {
            Text(
                text = text,
                color = if (isUser) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}