package com.elbertribeiro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.darkColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.FileDialog
import java.awt.Frame
import java.io.File


@Composable
fun AppUI() {
    var selectedFile by remember { mutableStateOf<File?>(null) }
    val uploadUrl by remember { mutableStateOf<String?>(null) }

    MaterialTheme(colors = darkColors()) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFF1C1C1E)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Drag and Drop Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color(0xFF29292E))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🚀 Drag files here...",
                        color = Color(0xFFBBBBBB),
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Menu Buttons
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = { selectedFile = selectFile() },
                        modifier = Modifier.height(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Select File",
                            tint = Color(0xFFBBBBBB)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Select file", color = Color(0xFFBBBBBB), fontSize = 16.sp)
                    }

                    TextButton(
                        onClick = {/* TODO: Add action for copying or opening URL */ },
                        modifier = Modifier.height(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Quit",
                            tint = Color(0xFFBBBBBB)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Quit", color = Color(0xFFBBBBBB), fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Show upload URL if available
                if (uploadUrl != null) {
                    ClickableText(
                        text = AnnotatedString(uploadUrl!!),
                        onClick = { /* TODO: Add action for copying or opening URL */ },
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.body2.copy(color = Color.Cyan)
                    )
                }
            }
        }
    }
}

fun selectFile(): File? {
    val dialog = FileDialog(Frame(), "Select File", FileDialog.LOAD)
    dialog.isVisible = true
    return dialog.files.firstOrNull()
}